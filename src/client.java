public class client {
    public static void main(String[] args) {
        String str="mississippi";
        huffmancode h=new huffmancode();
        h.encode(str);
        System.out.println();
        h.decode("100110011001110110111");

    }
}
