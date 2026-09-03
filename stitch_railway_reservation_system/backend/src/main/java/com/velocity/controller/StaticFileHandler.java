package com.velocity.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticFileHandler implements HttpHandler {
    private final String baseDir;

    public StaticFileHandler(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/") || path.isEmpty()) {
            path = "/index.html";
        } else if (path.equals("/select-seats") || path.equals("/select-seats/")) {
            path = "/select-seats.html";
        } else if (path.equals("/passengers") || path.equals("/passengers/")) {
            path = "/manage-passengers.html";
        } else if (path.equals("/my-bookings") || path.equals("/my-bookings/") || path.equals("/my-tickets")) {
            path = "/my-tickets.html";
        }

        // Prevent directory traversal
        if (path.contains("..")) {
            send404(exchange, "Forbidden");
            return;
        }

        Path target = Paths.get(baseDir, path);
        if (!Files.exists(target) || Files.isDirectory(target)) {
            send404(exchange, "File not found: " + path);
            return;
        }

        String mime = getMimeType(target.toString());
        exchange.getResponseHeaders().set("Content-Type", mime);
        exchange.getResponseHeaders().set("Cache-Control", "no-cache");

        byte[] bytes = Files.readAllBytes(target);
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private void send404(HttpExchange exchange, String message) throws IOException {
        byte[] bytes = ("<h1>404 Not Found</h1><p>" + message + "</p>").getBytes();
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(404, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private String getMimeType(String filename) {
        String lower = filename.toLowerCase();
        if (lower.endsWith(".html") || lower.endsWith(".htm")) return "text/html; charset=UTF-8";
        if (lower.endsWith(".css")) return "text/css; charset=UTF-8";
        if (lower.endsWith(".js")) return "application/javascript; charset=UTF-8";
        if (lower.endsWith(".json")) return "application/json; charset=UTF-8";
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".svg")) return "image/svg+xml";
        if (lower.endsWith(".ico")) return "image/x-icon";
        return "application/octet-stream";
    }
}
