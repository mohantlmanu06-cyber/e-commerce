package com.velocity.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.velocity.dto.ApiResponse;
import com.velocity.dto.CancellationPreviewResponse;
import com.velocity.dto.CreateBookingRequest;
import com.velocity.dto.PriceCalculationRequest;
import com.velocity.dto.PriceCalculationResponse;
import com.velocity.model.*;
import com.velocity.service.BookingService;
import com.velocity.service.PricingService;
import com.velocity.service.TrainService;
import com.velocity.util.JsonUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ApiController implements HttpHandler {
    private final TrainService trainService;
    private final PricingService pricingService;
    private final BookingService bookingService;

    public ApiController(TrainService trainService, PricingService pricingService, BookingService bookingService) {
        this.trainService = trainService;
        this.pricingService = pricingService;
        this.bookingService = bookingService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Set CORS headers
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String method = exchange.getRequestMethod();
        Map<String, String> queryParams = parseQueryParams(uri.getQuery());

        try {
            if (path.equals("/api/health") && method.equals("GET")) {
                Map<String, Object> health = new HashMap<>();
                health.put("status", "UP");
                health.put("service", "RailOne - Indian Railways Reservation Backend");
                health.put("version", "2.0.0");
                health.put("timestamp", System.currentTimeMillis());
                sendJsonResponse(exchange, 200, ApiResponse.ok(health));
            }
            // STATIONS
            else if (path.equals("/api/stations") && method.equals("GET")) {
                sendJsonResponse(exchange, 200, ApiResponse.ok(trainService.getAllStations()));
            }
            else if (path.startsWith("/api/stations/") && method.equals("GET")) {
                String code = path.substring("/api/stations/".length());
                Station s = trainService.getStation(code);
                if (s != null) {
                    sendJsonResponse(exchange, 200, ApiResponse.ok(s));
                } else {
                    sendJsonResponse(exchange, 404, ApiResponse.error("Station not found: " + code));
                }
            }
            // TRAINS SEARCH
            else if (path.equals("/api/trains/search") && method.equals("GET")) {
                String from = queryParams.get("from");
                String to = queryParams.get("to");
                String date = queryParams.get("date");
                List<Train> results = trainService.searchTrains(from, to, date);
                sendJsonResponse(exchange, 200, ApiResponse.ok(results));
            }
            else if (path.equals("/api/trains") && method.equals("GET")) {
                sendJsonResponse(exchange, 200, ApiResponse.ok(trainService.getAllTrains()));
            }
            else if (path.matches("^/api/trains/[^/]+/seats$") && method.equals("GET")) {
                String[] parts = path.split("/");
                String trainNumber = URLDecoder.decode(parts[3], StandardCharsets.UTF_8);
                Train train = trainService.getTrain(trainNumber);
                if (train != null) {
                    sendJsonResponse(exchange, 200, ApiResponse.ok(train.getSeats()));
                } else {
                    sendJsonResponse(exchange, 404, ApiResponse.error("Train not found: " + trainNumber));
                }
            }
            else if (path.startsWith("/api/trains/") && method.equals("GET")) {
                String trainNumber = URLDecoder.decode(path.substring("/api/trains/".length()), StandardCharsets.UTF_8);
                Train train = trainService.getTrain(trainNumber);
                if (train != null) {
                    sendJsonResponse(exchange, 200, ApiResponse.ok(train));
                } else {
                    sendJsonResponse(exchange, 404, ApiResponse.error("Train not found: " + trainNumber));
                }
            }
            // RECENT SEARCHES
            else if (path.equals("/api/recent-searches") && method.equals("GET")) {
                sendJsonResponse(exchange, 200, ApiResponse.ok(trainService.getRecentSearches()));
            }
            // PRICING CALCULATION
            else if (path.equals("/api/pricing/calculate") && method.equals("POST")) {
                String body = readRequestBody(exchange);
                PriceCalculationRequest req = parsePriceCalculationRequest(body);
                PriceCalculationResponse res = pricingService.calculatePrice(req);
                sendJsonResponse(exchange, 200, ApiResponse.ok(res));
            }
            // BOOKINGS
            else if (path.equals("/api/bookings") && method.equals("POST")) {
                String body = readRequestBody(exchange);
                CreateBookingRequest req = parseCreateBookingRequest(body);
                Booking booking = bookingService.createBooking(req);
                sendJsonResponse(exchange, 201, ApiResponse.ok("Booking confirmed successfully on RailOne", booking));
            }
            else if (path.equals("/api/bookings") && method.equals("GET")) {
                String filter = queryParams.get("filter");
                List<Booking> bookings = bookingService.getAllBookings(filter);
                sendJsonResponse(exchange, 200, ApiResponse.ok(bookings));
            }
            // CANCELLATION PREVIEW & REFUND CALCULATION
            else if (path.matches("^/api/bookings/[^/]+/cancel-preview$") && method.equals("GET")) {
                String[] parts = path.split("/");
                String pnr = URLDecoder.decode(parts[3], StandardCharsets.UTF_8);
                CancellationPreviewResponse preview = bookingService.previewCancellation(pnr);
                sendJsonResponse(exchange, 200, ApiResponse.ok(preview));
            }
            // CANCEL TICKET
            else if (path.matches("^/api/bookings/[^/]+/cancel$") && (method.equals("POST") || method.equals("PUT"))) {
                String[] parts = path.split("/");
                String pnr = URLDecoder.decode(parts[3], StandardCharsets.UTF_8);
                boolean cancelled = bookingService.cancelBooking(pnr);
                if (cancelled) {
                    Booking updated = bookingService.getBookingByPnr(pnr);
                    sendJsonResponse(exchange, 200, ApiResponse.ok("Booking " + pnr + " successfully cancelled. Refund initiated.", updated));
                } else {
                    sendJsonResponse(exchange, 400, ApiResponse.error("Failed to cancel booking. It may not exist or is already cancelled."));
                }
            }
            else if (path.startsWith("/api/bookings/") && method.equals("GET")) {
                String pnr = URLDecoder.decode(path.substring("/api/bookings/".length()), StandardCharsets.UTF_8);
                Booking booking = bookingService.getBookingByPnr(pnr);
                if (booking != null) {
                    sendJsonResponse(exchange, 200, ApiResponse.ok(booking));
                } else {
                    sendJsonResponse(exchange, 404, ApiResponse.error("Booking with PNR " + pnr + " not found"));
                }
            }
            else {
                sendJsonResponse(exchange, 404, ApiResponse.error("Endpoint not found: " + method + " " + path));
            }
        } catch (IllegalArgumentException e) {
            sendJsonResponse(exchange, 400, ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            sendJsonResponse(exchange, 500, ApiResponse.error("Internal Server Error: " + e.getMessage()));
        }
    }

    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isEmpty()) return map;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0) {
                String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                String val = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                map.put(key, val);
            }
        }
        return map;
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        return baos.toString(StandardCharsets.UTF_8);
    }

    @SuppressWarnings("unchecked")
    private PriceCalculationRequest parsePriceCalculationRequest(String json) {
        Map<String, Object> map = JsonUtils.parseObject(json);
        PriceCalculationRequest req = new PriceCalculationRequest();
        req.setTrainNumber((String) map.get("trainNumber"));
        req.setClassId((String) map.get("classId"));

        List<Object> passList = (List<Object>) map.get("passengers");
        if (passList != null) {
            List<PriceCalculationRequest.PassengerItem> items = new ArrayList<>();
            for (Object obj : passList) {
                if (obj instanceof Map) {
                    Map<String, Object> pMap = (Map<String, Object>) obj;
                    items.add(new PriceCalculationRequest.PassengerItem(
                            (String) pMap.get("passengerType"),
                            (String) pMap.get("seatNumber")
                    ));
                }
            }
            req.setPassengers(items);
        }
        return req;
    }

    @SuppressWarnings("unchecked")
    private CreateBookingRequest parseCreateBookingRequest(String json) {
        Map<String, Object> map = JsonUtils.parseObject(json);
        CreateBookingRequest req = new CreateBookingRequest();
        req.setTrainNumber((String) map.get("trainNumber"));
        req.setJourneyDate((String) map.get("journeyDate"));
        req.setClassId((String) map.get("classId"));
        req.setCar((String) map.get("car"));
        req.setPaymentMode((String) map.get("paymentMode"));
        req.setContactEmail((String) map.get("contactEmail"));
        req.setContactPhone((String) map.get("contactPhone"));

        List<Object> passList = (List<Object>) map.get("passengers");
        if (passList != null) {
            List<Passenger> passengers = new ArrayList<>();
            for (Object obj : passList) {
                if (obj instanceof Map) {
                    Map<String, Object> pMap = (Map<String, Object>) obj;
                    Passenger p = new Passenger();
                    p.setId((String) pMap.get("id"));
                    p.setFirstName((String) pMap.get("firstName"));
                    p.setLastName((String) pMap.get("lastName"));
                    if (pMap.get("age") != null) {
                        try {
                            p.setAge(((Number) pMap.get("age")).intValue());
                        } catch (Exception ignored) {}
                    }
                    p.setGender((String) pMap.get("gender"));
                    p.setBerthPreference((String) pMap.get("berthPreference"));
                    p.setMealPreference((String) pMap.get("mealPreference"));
                    p.setDateOfBirth((String) pMap.get("dateOfBirth"));
                    p.setPassengerType((String) pMap.get("passengerType"));
                    p.setSeatNumber((String) pMap.get("seatNumber"));
                    p.setCar((String) pMap.get("car"));
                    passengers.add(p);
                }
            }
            req.setPassengers(passengers);
        }
        return req;
    }

    private void sendJsonResponse(HttpExchange exchange, int statusCode, Object responseObj) throws IOException {
        String json = JsonUtils.toJson(responseObj);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
