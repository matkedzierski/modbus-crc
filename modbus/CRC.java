package modbus;

import java.util.Arrays;

public class CRC {
    int HiByte = 0xFF;
    int LoByte = 0xFF;
    int Index;

    public int[] calculate(int[] data) {
        for (int b : data) {
            Index = (HiByte ^ b);
            HiByte = (LoByte ^ LookupTables.aCRCHi[Index]);
            LoByte = LookupTables.aCRCLo[Index];
        }

        return new int[] { (byte) HiByte, (byte) LoByte };
    }

    public static int[] hexStringToByteArray(String s) {
        int len = s.length();
        int[] data = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (int) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(int[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
