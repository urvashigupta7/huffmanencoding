package huffmanCoder;

import java.util.ArrayList;

public class Utils {
    public static byte bits2bytesHelper(String current) {
        int retVal = 0;
        for (int i = 0; i < current.length(); i++) {
            int bit = 0;
            if (current.charAt(i) == '1') {
                bit = 1;
            }
            retVal |= bit << i;
        }

        return (byte) retVal;
    }
    public static ArrayList<Boolean> bytes2bit(ArrayList<Byte> arr) {
        ArrayList<Boolean> retVal = new ArrayList<>();

        for (int i = 0; i < arr.size() - 2; i++) {
            Byte current = arr.get(i);
            ArrayList<Boolean> currBool = byte2bitesHelper(current);
            for (int j = 0; j < currBool.size(); j++) {
                retVal.add(currBool.get(j));
            }
        }


        ArrayList<Boolean> lastBool = byte2bitesHelper(arr.get(arr.size() - 2));
        byte leadingEmptySpots = arr.get(arr.size() - 1);
        for (int i = 0; i < (8-leadingEmptySpots); i++) {
            retVal.add(lastBool.get(i));
        }

        return retVal;
    }
    public static ArrayList<Boolean> byte2bitesHelper(Byte characterbyte) {
        boolean[] array = new boolean[8];
        array[7] = ((characterbyte & 0x80) != 0); //Leftmost
        array[6] = ((characterbyte & 0x40) != 0);
        array[5] = ((characterbyte & 0x20) != 0);
        array[4] = ((characterbyte & 0x10) != 0);
        array[3] = ((characterbyte & 0x08) != 0);
        array[2] = ((characterbyte & 0x04) != 0);
        array[1] = ((characterbyte & 0x02) != 0);
        array[0] = ((characterbyte & 0x01) != 0); //Rightmost

        ArrayList<Boolean> retVal = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            retVal.add(array[i]);
        }

        return retVal;
    }
}
