package huffmanCoder;

import java.io.*;
import java.util.ArrayList;

public class FileOperations {

    public static void writeEncodedFile(String encodedstring, File file) {
        ArrayList<Byte> converted = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i < Math.ceil((float)encodedstring.length() / 8); i++) {
            sb = new StringBuilder();
            for (int j = 0; (j < 8) && (i * 8 + j < encodedstring.length()); j++) {
                sb.append(encodedstring.charAt(i * 8 + j));
            }
            Byte b = Utils.bits2bytesHelper(sb.toString());
            converted.add(b);
        }
        byte leadingEmptySpots = (byte) (((encodedstring.length() / 8) + 1) * 8 - encodedstring.length());
        converted.add(leadingEmptySpots);

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file.getAbsoluteFile()));


            for (int i = 0; i < converted.size(); i++) {
                dos.writeByte(converted.get(i));
            }

            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(File file) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder fileContents = new StringBuilder();
            String currentLine = br.readLine();
            while (currentLine != null) {
                fileContents.append(currentLine).append("\n");
                currentLine = br.readLine();
            }
            return fileContents.toString();


        } catch (FileNotFoundException ex) {
            System.err.println("Invalid Path");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void writeDecodedFile(File file, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static ArrayList<Boolean> readEncodedFile(File file) {

        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file.getAbsoluteFile()));

            ArrayList<Byte> bytes = new ArrayList<>();
            while (dis.available() > 0) {
                bytes.add(dis.readByte());
            }
            return Utils.bytes2bit(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
