import java.io.*;

public class Assembler2 {

    public static int index = 0;

    public static void loc(String[] arr, FileWriter output) throws IOException {
        index = Integer.parseInt(arr[2]);
        String numstr = Integer.toOctalString(index);
        output.write(index + "         000000;\n");
        System.out.println(numstr + "         000000;");
    }

    public static void data(String[] arr, FileWriter output) throws IOException {
        //need to fix
        if(arr[2].equals("End"))
        {
            return;
        }

        int numstr = Integer.parseInt(arr[2]);
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        output.write("000000       " + value + "\n");
        System.out.println(indexstr + "      " + value);
    }

    public static void hlt(FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void TRAP(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void LDR(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void LDX(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void SETCCE(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void JZ(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void LDA(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void main(String[] args) throws IOException {
        // File paths for input and output files respectively
        File inFile = new File("./input.txt");
        File outFile = new File("output.txt");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(inFile));

        // Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);

        output.write("000000 000000;\n");
        System.out.println("000000 000000;");

        String str;

        while ((str = br.readLine()) != null) {
            // manual checks
           // System.out.println(str);
           // System.out.println(str.split("\\s+"));

            String[] arr = str.split("\\s+");
            System.out.println(arr.length);
            switch (arr[1]) {
                case "LOC":
                    loc(arr, output);
                    index = index + 1;
                    break;
                case "Data":
                    data(arr, output);
                    index = index + 1;
                    break;
                case "TRAP":
                    System.out.println("TRAP");
                    TRAP(arr, output);
                    break;
                case "HLT":
                    System.out.println("halt");
                    break;
                case "LDX":
                    System.out.println("LDX");
                    LDX(arr, output);
                    break;
                case "LDR":
                    System.out.println("LDR");
                    LDR(arr, output);
                    break;
                case "JZ":
                    System.out.println("JZ");
                    JZ(arr, output);
                    break;
                case "LDA":
                    System.out.println("LDA");
                    LDA(arr, output);
                    break;
                case "SETCCE":
                    System.out.println("SETCCE");
                    SETCCE(arr, output);
                    break;
                default:
                    System.out.println("Unknown instruction: " + arr[1]);
                    break;
            }
        }

        // Closing 
        br.close();
        output.close();
    }
}
