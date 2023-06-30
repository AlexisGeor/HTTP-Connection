package com.oem.oem;

public class AsciiToHexConverter {              //Clase Utils


    private AsciiToHexConverter() {
    }

    public static String asciiToHex(String asciiString) {           // El static me permite llamar a esta funcion de manera directa sin instanciar la clase
        StringBuilder hexBuilder = new StringBuilder();

        for (int i = 0; i < asciiString.length(); i++) {
            int asciiValue = (int) asciiString.charAt(i); // Obtener el valor ASCII de cada carácter

            while (asciiValue > 0) {
                int remainder = asciiValue % 16;
                char hexDigit = (remainder < 10) ? (char) (remainder + '0') : (char) (remainder + 'A' - 10);
                hexBuilder.insert(0, hexDigit);
                asciiValue = asciiValue / 16;
            }
        }

        return hexBuilder.toString();
    }

}
