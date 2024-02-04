import java.io.*;

public class Assembler {

    public static int index = 0;
    public static void loc(String[] arr, FileWriter output){
        index = Integer.parseInt(arr[1]);
        String numstr = Integer.toOctalString(index);
        //output.write(index+"         000000;%n");
        System.out.println(numstr+"         000000;");

    }

     public static void data(String[] arr, FileWriter output){
        int numstr = Integer.parseInt(arr[1]);
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        //output.write("000000       "+value+"%n");
        System.out.println(indexstr+"      "+value);
    }

    public void hlt(FileWriter output){
        //output.write("000000 000000;%n");
        System.out.println("000000 000000;");
    }

    public static void main(String [] args) throws Exception
    {
        //File paths for input and output files respectively 
        File inFile = new File("input.txt");
        File outFile = new File("output.txt");

        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(inFile));

        //Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);

        //output.write("000000 000000;%n");
        System.out.println("000000 000000;");

        String str;


        while ((str = br.readLine()) != null){
            
            String [] arr = str.split("\\s+");
            switch(arr[0]){
                case "LOC":
                    loc(arr, output);
                    index = index + 1;
                case "Data":
                    data(arr,output);
                    index = index + 1;
                case "TRAP":
                    //Add code
            }
        }
    }
}