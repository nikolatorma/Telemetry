package com.telemetry.service;

import org.junit.jupiter.api.Test;

import com.telemetry.model.VehicleMessage;
import com.telemetry.parser.VehicleMessageParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SocketServerTest {

    private final VehicleMessageParser parser = new VehicleMessageParser();

    @Test
    void testSocketServerReceiveAndParse() throws Exception {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5555)) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String receivedHex = in.readLine();
                assertNotNull(receivedHex);

                VehicleMessage message = parser.parse(receivedHex);

                assertNotNull(message);
                assertTrue(message.getLongitude() > 0);
                assertTrue(message.getLatitude() > 0);

                clientSocket.close();
            } catch (IOException e) {
                fail("Socket error: " + e.getMessage());
            }
        }).start();

        try (Socket socket = new Socket("localhost", 5555);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String exampleHex = "000000000000004B080100000197A71097E0000BFE72811AE09C2200890089110065000D05150301010201516952360442378C5402B2550A4973039804F1000055F3480000015F571BA76A146B0002C459000100004232";
            out.println(exampleHex);
        }
    }
}
