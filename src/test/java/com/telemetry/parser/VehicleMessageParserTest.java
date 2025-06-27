package com.telemetry.parser;

import com.telemetry.constant.ProtocolConstants;
import com.telemetry.model.VehicleMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class VehicleMessageParserTest {

    private VehicleMessageParser parser;

    @BeforeEach
    void setUp() {
        parser = new VehicleMessageParser();
    }

    @Test
    @DisplayName("Should parse a valid hex string and extract correct values")
    void testParseSingleMessage() {
        // This TimeStamp: 0000016B40D9AD80 is 2019-06-10 10:05:36
        String hexString = "00000000000000000000016B40D9AD80000BFE72811AE09C2200890089110065000D05150301010201516952360442378C5402B2550A4973039804F1000055F3480000015F571BA76A146B0002C459000100004232";

        VehicleMessage message = parser.parse(hexString);

        assertNotNull(message, "Parsed message should not be null");

        // Check TimeStamp is within expected range (2019-01-01 to now)
        LocalDateTime minDate = LocalDateTime.of(2019, 1, 1, 0, 0);
        LocalDateTime maxDate = LocalDateTime.now();
        LocalDateTime actual = message.getTimestamp();
        assertTrue(actual.isAfter(minDate), "Timestamp should be between 2019 and now: was " + actual);
        assertTrue(actual.isBefore(maxDate), "Timestamp should be between 2019 and now: was " + actual);
        assertTrue(message.getLongitude() > 0, "Longitude should be positive");
        assertTrue(message.getLatitude() > 0, "Latitude should be positive");
        assertTrue(message.getSpeed() >= 0, "Speed should be non-negative");
        assertNotNull(message.getIoElements(), "IO elements map should not be null");
        assertFalse(message.getIoElements().isEmpty(), "IO elements should not be empty");
    }
    
    @Test
    @DisplayName("Should handle zero or negative longitude/latitude/speed")
    void testZeroOrNegativeValues() {
        StringBuilder hexStringWithZeroCoordsAndSpeed = new StringBuilder();
        hexStringWithZeroCoordsAndSpeed.append("00000000000000000000016B40D9AD8000000000000000000000000000000000000000000000");
        while (hexStringWithZeroCoordsAndSpeed.length() < ProtocolConstants.MINIMUM_VALID_HEX_LENGTH) {
        	hexStringWithZeroCoordsAndSpeed.append("0");
        }

        VehicleMessage message = parser.parse(hexStringWithZeroCoordsAndSpeed.toString());

        assertEquals(0.0, message.getLongitude(), "Longitude should be zero");
        assertEquals(0.0, message.getLatitude(), "Latitude should be zero");
        assertEquals(0, message.getSpeed(), "Speed should be zero");
    }

    @Test
    @DisplayName("Should throw exception for invalid hex string")
    void testParseInvalidHex() {
        String invalidHex = "1234ABCD";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(invalidHex));
    }

    @Test
    @DisplayName("Should parse multiple messages from DebugRawData.txt")
    void testParseMessagesFromFile() throws Exception {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream("DebugRawData.txt"))
                ))) {
            lines = reader.lines().toList();
        }

        assertFalse(lines.isEmpty(), "DebugRawData.txt should not be empty");

        for (String line : lines) {
            VehicleMessage message = parser.parse(line);
            assertNotNull(message.getTimestamp());
            assertTrue(message.getLongitude() > 0);
            assertTrue(message.getLatitude() > 0);
            assertTrue(message.getSpeed() >= 0);
            assertNotNull(message.getIoElements());
            assertTrue(message.getIoElements().containsKey("ExampleIO"), "IO elements should contain ExampleIO");
        }
    }
    
    @Test
    @DisplayName("Should handle null input string")
    void testParseNullHex() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }

    @Test
    @DisplayName("Should handle hex string shorter than IO element end position")
    void testParseShortHexForIoElements() {
        String shortHexString = "000000000000004B080100000197A71097E0000BFE72811AE09C2200890089110065000D051503"; 
        VehicleMessage message = parser.parse(shortHexString + "00000000000000000000");  // total length < 100
        assertEquals("00", message.getIoElements().get("ExampleIO"));
    }
}