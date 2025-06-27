package com.telemetry.parser;

import com.telemetry.constant.ProtocolConstants;
import com.telemetry.model.VehicleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Component
public class VehicleMessageParser {

    private static final Logger logger = LoggerFactory.getLogger(VehicleMessageParser.class);

    public VehicleMessage parse(String hexString) {
        if (hexString == null || hexString.length() < ProtocolConstants.MINIMUM_VALID_HEX_LENGTH) {
            throw new IllegalArgumentException("Invalid hex string input.");
        }

        VehicleMessage message = new VehicleMessage();
        message.setTimestamp(parseTimestamp(hexString));
        message.setLongitude(parseCoordinate(hexString, ProtocolConstants.LONGITUDE_START, ProtocolConstants.LONGITUDE_END));
        message.setLatitude(parseCoordinate(hexString, ProtocolConstants.LATITUDE_START, ProtocolConstants.LATITUDE_END));
        message.setEventId(parseEventId(hexString));
        message.setSpeed(parseSpeed(hexString));
        message.setIoElements(parseIoElements(hexString));

        logger.debug("Parsed telemetry message: {}", message);
        return message;
    }

    private LocalDateTime parseTimestamp(String hexString) {
        String timestampHex = hexString.substring(ProtocolConstants.TIMESTAMP_START, ProtocolConstants.TIMESTAMP_END);
        long timestampMillis = Long.parseLong(timestampHex, ProtocolConstants.TIMESTAMP_START);
        return Instant.ofEpochMilli(timestampMillis).atZone(ZoneOffset.UTC).toLocalDateTime();
    }
    
    private double parseCoordinate(String hexString, int start, int end) {
        String valueHex = hexString.substring(start, end);
        long valueLong = Long.parseLong(valueHex, ProtocolConstants.TIMESTAMP_START);
        return valueLong / 10000000.0;
    }

    private int parseEventId(String hexString) {
        String eventIdHex = hexString.substring(ProtocolConstants.EVENT_ID_START, ProtocolConstants.EVENT_ID_END);
        return Integer.parseInt(eventIdHex, ProtocolConstants.TIMESTAMP_START);
    }

    private int parseSpeed(String hexString) {
        String speedHex = hexString.substring(ProtocolConstants.SPEED_START, ProtocolConstants.SPEED_END);
        return Integer.parseInt(speedHex, ProtocolConstants.TIMESTAMP_START);
    }

    private Map<String, String> parseIoElements(String hexString) {
        Map<String, String> ioElements = new HashMap<>();

        if (hexString.length() >= ProtocolConstants.IO_ELEMENT_END) {
            ioElements.put("ExampleIO", hexString.substring(ProtocolConstants.IO_ELEMENT_START, ProtocolConstants.IO_ELEMENT_END));
        } else {
            ioElements.put("ExampleIO", "00");
        }

        return ioElements;
    }
}
