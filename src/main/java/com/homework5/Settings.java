package com.homework5;

import java.util.List;
import java.util.stream.Collectors;

public class Settings{
    public static final String toFile = "inputPlantFile.txt";
    public static final String fromFile = "outputPlantFile.txt";
    public static final String wrongFormatFile = "wrongInFormat.txt";
    public static String delimiter = "; ";
    public static String parser(String str) {
        return str.replace(';',' ').trim();
    }
    public static String parser(List<String> str) {
//        String collect=str.stream().map(Settings::parser).collect(Collectors.joining(", "));
//        return str.toString().replace('[',' ').replace(']',',').trim();
        return str.stream().map(Settings::parser).collect(Collectors.joining(", "));
    }
}
