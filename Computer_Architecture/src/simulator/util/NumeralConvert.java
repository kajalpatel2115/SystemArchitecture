package simulator.util;
public class NumeralConvert {
    public static String OctalToBinary(String octalValue, int binaryLength)
    {
        String binaryValue = Integer.toBinaryString(Integer.parseInt(octalValue, 8));;
        return String.format("%0"+String.valueOf(binaryLength)+"d", Integer.parseInt(binaryValue));
    }

    public static int BinaryToDecimal(String binaryValue)
    {
        return Integer.parseInt(binaryValue, 2);
    }

    public static void main (String[] args){
        int decimalStr = Integer.parseInt("101", 2);
        System.out.println(decimalStr);
        ;
    }

}
