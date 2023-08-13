package com.oem.oem;

public class Converter {              //Clase Utils

    public static String asciiToHex(String asciiString) {
        StringBuilder hexBuilder = new StringBuilder();

        for (int i = 0; i < asciiString.length(); i++) {
            char c = asciiString.charAt(i);
            String hex = Integer.toHexString((int) c);
            hexBuilder.append(hex);
        }

        return hexBuilder.toString();
    }

    public static String hexToAscii(String hexString) {
        StringBuilder asciiBuilder = new StringBuilder();

        for (int i = 0; i < hexString.length() - 1; i += 2) {
            String hex = hexString.substring(i, i + 2);
            int asciiValue = Integer.parseInt(hex, 16);
            char asciiChar = (char) asciiValue;
            asciiBuilder.append(asciiChar);
        }

        return asciiBuilder.toString();
    }

    public static byte[] communicarionBoard(String valor) {
        char startChar = 2;
        String middleString = Converter.asciiToHex(valor); // '01K51D' "30314B353144"  "30314F393135"'010915' Conversion de String a cadena Ascci
        char endChar = 3;

        // Convertir la secuencia completa en un arreglo de bytes
        byte[] sequenceBytes = new byte[middleString.length() / 2 + 2];
        sequenceBytes[0] = (byte) startChar;

        for (int i = 0; i < middleString.length(); i += 2) {
            String byteString = middleString.substring(i, i + 2);
            byte sequenceByte = (byte) Integer.parseInt(byteString, 16);
            sequenceBytes[i / 2 + 1] = sequenceByte;
        }

        sequenceBytes[sequenceBytes.length - 1] = (byte) endChar;
        return sequenceBytes;
    }

}
