package com.telemetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class SocketServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private final TelemetryService telemetryService;

    public SocketServer(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5050)) {
                logger.info("Socket server started on port {}", 5050);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                logger.error("Failed to start socket server on port {}: {}", 5050, e.getMessage(), e);
            }
        }, "TelemetrySocketServerThread").start();
    }

    private void handleClient(Socket clientSocket) {
        logger.info("Accepted connection from {}", clientSocket.getInetAddress());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String hexMessage;
            while ((hexMessage = in.readLine()) != null) {
                logger.debug("Received message: {}", hexMessage);
                processMessage(hexMessage);
            }
        } catch (IOException e) {
            logger.error("Error while handling client {}: {}", clientSocket.getInetAddress(), e.getMessage(), e);
        } finally {
            try {
                clientSocket.close();
                logger.info("Closed connection to {}", clientSocket.getInetAddress());
            } catch (IOException e) {
                logger.warn("Error closing client socket: {}", e.getMessage(), e);
            }
        }
    }

    private void processMessage(String hexMessage) {
        try {
            telemetryService.processMessage(hexMessage);
        } catch (Exception e) {
            logger.error("Failed to process telemetry message: {}", e.getMessage(), e);
        }
    }
}