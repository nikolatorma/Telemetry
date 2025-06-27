package com.telemetry.service;

import com.telemetry.model.VehicleMessage;
import com.telemetry.parser.VehicleMessageParser;
import com.telemetry.repository.VehicleMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelemetryService {

    private static final Logger logger = LoggerFactory.getLogger(TelemetryService.class);

    private final VehicleMessageParser messageParser;
    private final VehicleMessageRepository messageRepository;

    public TelemetryService(VehicleMessageParser messageParser, VehicleMessageRepository messageRepository) {
        this.messageParser = messageParser;
        this.messageRepository = messageRepository;
    }

    public void processMessage(String hexMessage) {
        try {
            logger.debug("Processing incoming telemetry message: {}", hexMessage);

            VehicleMessage message = messageParser.parse(hexMessage);
            logger.info("Parsed message: {}", message);

            messageRepository.save(message);
            logger.info("Telemetry message saved to database successfully");

        } catch (Exception e) {
            logger.error("Failed to process telemetry message: {}", e.getMessage(), e);
        }
    }
}
