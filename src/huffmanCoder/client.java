package huffmanCoder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        Scanner scrn = new Scanner(System.in);
        System.out.println("Enter the path of the file to be compressed");
        String filePath = scrn.nextLine();
        File file = new File(filePath);
        File decodedOutputFile=new File("C:\\Users\\Bhoomik\\Desktop\\textdecoded.txt");
        String fileContents = FileOperations.readFile(file);
        Huffmancode h = new Huffmancode();
        String res = "";
        res = h.encode(fileContents);
        FileOperations.writeEncodedFile(res, file);
        ArrayList<Boolean> encodedfile = FileOperations.readEncodedFile(file);
        String decodedoutput=h.decode(encodedfile);
        FileOperations.writeDecodedFile(decodedOutputFile,decodedoutput);
    }
}

