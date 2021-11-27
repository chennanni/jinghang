package com.max.core.enums;

import java.util.ArrayList;

public class DatasetStatus {

    public static final String FTP_START = "f";
    public static final String FTP_END = "F";
    public static final String RECEIVER_START = "r";
    public static final String RECEIVER_END = "R";
    public static final String DISPATCHER_START = "d";
    public static final String DISPATCHER_END = "D";
    public static final String PARSER_START = "p";
    public static final String PARSER_END = "P";
    public static final String LOADER_START = "l";
    public static final String LOADER_END = "L";
    public static final ArrayList<String> PROCESS_SEQUENCE = new ArrayList<String>();

    static {
        PROCESS_SEQUENCE.add(FTP_START);
        PROCESS_SEQUENCE.add(FTP_END);
        PROCESS_SEQUENCE.add(RECEIVER_START);
        PROCESS_SEQUENCE.add(RECEIVER_END);
        PROCESS_SEQUENCE.add(DISPATCHER_START);
        PROCESS_SEQUENCE.add(DISPATCHER_END);
        PROCESS_SEQUENCE.add(PARSER_START);
        PROCESS_SEQUENCE.add(PARSER_END);
        PROCESS_SEQUENCE.add(LOADER_START);
        PROCESS_SEQUENCE.add(LOADER_END);
    }

    public static String getPreviousStatus(String status) {
        if (PROCESS_SEQUENCE.contains(status)) {
            int previousIndex = PROCESS_SEQUENCE.lastIndexOf(status)-1;
            if (previousIndex >= 0) {
                return PROCESS_SEQUENCE.get(previousIndex);
            }
        }
        return null;
    }

}
