package modbus;

public class CRC {
    char HiByte = 0xFF;
    char LoByte = 0xFF;
    char Index;

    public static void main(String[] args){
        char[] in = new char[]{0x01,0x10,0x00,0x11,0x00,0x03,0x06,0x1A,0xC4,0xBA,0xD0 };
        System.out.println(new CRC().calculate(in));
    }

    public byte[] calculate(char[] data) {
        for(int i = 0; i < data.length; i++) {
            Index = (char)(HiByte ^ data[i]);
            HiByte = (char)(LoByte ^ LookupTables.aCRCHi[Index]);
            LoByte = (char)LookupTables.aCRCLo[Index];
        }

        return new byte[] { (byte)HiByte, (byte)LoByte };
    }
}
