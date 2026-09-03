# --- Stage 1: Build Java Backend ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy backend source files
COPY backend ./backend
RUN mkdir -p backend/bin && \
    javac -encoding UTF-8 -d backend/bin $(find backend/src/main/java -name "*.java")

# --- Stage 2: Production Runtime ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy compiled classes, backend data and static frontend assets
COPY --from=builder /app/backend/bin ./backend/bin
COPY backend/data ./backend/data
COPY public ./public

# Expose standard port
ENV PORT=8080
EXPOSE 8080

# Run VelocityServer
CMD ["java", "-cp", "backend/bin", "com.velocity.VelocityServer", "8080"]
