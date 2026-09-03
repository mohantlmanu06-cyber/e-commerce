package com.velocity;

import com.sun.net.httpserver.HttpServer;
import com.velocity.controller.ApiController;
import com.velocity.controller.StaticFileHandler;
import com.velocity.service.BookingService;
import com.velocity.service.PricingService;
import com.velocity.service.TrainService;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class VelocityServer {
    public static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }
        String envPort = System.getenv("PORT");
        if (envPort != null && !envPort.isEmpty()) {
            try {
                port = Integer.parseInt(envPort);
            } catch (NumberFormatException ignored) {}
        }

        try {
            // Instantiate Services
            TrainService trainService = new TrainService();
            PricingService pricingService = new PricingService(trainService);
            BookingService bookingService = new BookingService(trainService, pricingService);

            // Locate Public / UI Directory
            String publicDir = findPublicDir();

            // Create HTTP Server
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Register API Handlers
            ApiController apiController = new ApiController(trainService, pricingService, bookingService);
            server.createContext("/api", apiController);

            // Register Static File Handler
            StaticFileHandler staticHandler = new StaticFileHandler(publicDir);
            server.createContext("/", staticHandler);

            // Configure multi-threaded executor
            server.setExecutor(Executors.newFixedThreadPool(16));
            server.start();

            printBanner(port, publicDir);

        } catch (Exception e) {
            System.err.println("Failed to start RailOne Server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static String findPublicDir() {
        String[] candidates = {
            "public",
            "../public",
            "stitch_railway_reservation_system",
            "."
        };
        for (String c : candidates) {
            File f = new File(c);
            if (f.exists() && f.isDirectory()) {
                return f.getAbsolutePath();
            }
        }
        return new File(".").getAbsolutePath();
    }

    private static void printBanner(int port, String publicDir) {
        System.out.println("================================================================");
        System.out.println("   ____             _  _      ____                              ");
        System.out.println("  |  _ \\   __ _    (_)| |    / __ \\  _ __    ___               ");
        System.out.println("  | |_) | / _` |   | || |   / / / / | '_ \\  / _ \\              ");
        System.out.println("  |  _ < | (_| |   | || |  / /_/ /  | | | ||  __/              ");
        System.out.println("  |_| \\_\\ \\__,_|   |_||_|  \\____/   |_| |_| \\___|              ");
        System.out.println("================================================================");
        System.out.println(" [SUCCESS] RailOne - Indian Railway Reservation Platform RUNNING");
        System.out.println(" [PORT]    http://localhost:" + port);
        System.out.println(" [THEME]   Emerald Green Palette | Light (Default) & Dark Mode");
        System.out.println(" [DATA]    60+ Real Stations • 15+ Real High-Speed Trains");
        System.out.println(" [STORE]   Persistent Disk Storage at backend/data/bookings_db.json");
        System.out.println("----------------------------------------------------------------");
        System.out.println(" REST API Endpoints:");
        System.out.println("   * GET  http://localhost:" + port + "/api/health");
        System.out.println("   * GET  http://localhost:" + port + "/api/stations");
        System.out.println("   * GET  http://localhost:" + port + "/api/trains/search?from=NDLS&to=BSB");
        System.out.println("   * GET  http://localhost:" + port + "/api/trains/{trainNumber}/seats");
        System.out.println("   * POST http://localhost:" + port + "/api/pricing/calculate");
        System.out.println("   * POST http://localhost:" + port + "/api/bookings");
        System.out.println("   * GET  http://localhost:" + port + "/api/bookings");
        System.out.println("   * GET  http://localhost:" + port + "/api/bookings/{pnr}");
        System.out.println("   * GET  http://localhost:" + port + "/api/bookings/{pnr}/cancel-preview");
        System.out.println("   * POST http://localhost:" + port + "/api/bookings/{pnr}/cancel");
        System.out.println(" Web UI Pages:");
        System.out.println("   * Search & Home:         http://localhost:" + port + "/");
        System.out.println("   * Carriage Schematic:    http://localhost:" + port + "/select-seats.html");
        System.out.println("   * Passenger Details:     http://localhost:" + port + "/manage-passengers.html");
        System.out.println("   * Upcoming & Cancelled:  http://localhost:" + port + "/my-tickets.html");
        System.out.println("================================================================");
    }
}
