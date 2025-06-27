package com.telemetry.constant;

public class ProtocolConstants {

    public static final int TIMESTAMP_START = 16;
    public static final int TIMESTAMP_END = 32;

    public static final int LONGITUDE_START = 48;
    public static final int LONGITUDE_END = 56;

    public static final int LATITUDE_START = 56;
    public static final int LATITUDE_END = 64;

    public static final int EVENT_ID_START = 80;
    public static final int EVENT_ID_END = 84;

    public static final int SPEED_START = 84;
    public static final int SPEED_END = 88;

    public static final int IO_ELEMENT_START = 100;
    public static final int IO_ELEMENT_END = 102;
    
    public static final int MINIMUM_VALID_HEX_LENGTH = 88;

    public static final double POSITION_SCALING_FACTOR = 10_000_000.0;

    public static final int HEX_BYTE_LENGTH = 2;
    
    private ProtocolConstants() {
	}
}
