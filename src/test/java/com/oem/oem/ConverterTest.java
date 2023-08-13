package com.oem.oem;
import org.junit.jupiter.api.Test;

import static com.oem.oem.Converter.hexToAscii;
import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest {
    @Test
    public void testAsciiToHex() {
        String asciiString = "01K51D";
        String expectedHex = "30314b353144";

        String actualHex = Converter.asciiToHex(asciiString);
        assertEquals(expectedHex, actualHex);
        System.out.println(actualHex);
    }

        @Test
        public void testHexToAscii() {
            String hexString = "30314B353144";
            String expectedAscii = "01K51D";
            String actualAscii = hexToAscii(hexString);
            assertEquals(expectedAscii, actualAscii);
            System.out.println(hexString);
            System.out.println(actualAscii);
        }
}
