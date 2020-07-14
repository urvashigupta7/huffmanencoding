package huffmanCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffmancode {

    private class Huffmannode implements Comparable<Huffmannode> {
        Character ch;
        Integer freq;
        Huffmannode left;
        Huffmannode right;

        public Huffmannode() {
            this.ch = '\0';
            this.left = null;
            this.right = null;
            this.freq = 0;
        }

        @Override
        public int compareTo(Huffmannode node) {
            return freq - node.freq;
        }
    }

    private Huffmannode root;
    private HashMap<Character, String> encoder;
    private HashMap<String, Character> decoder;

    public Huffmancode() {
        encoder = new HashMap<>();
        decoder = new HashMap<>();
    }

    private Huffmannode buildtree(HashMap<Character, Integer> map) {
        PriorityQueue<Huffmannode> p = new PriorityQueue<>();
        Set<Character> keys = map.keySet();
        for (Character key : keys) {
            Huffmannode n = new Huffmannode();
            n.ch = key;
            n.freq = map.get(key);
            n.left = null;
            n.right = null;
            p.offer(n);
        }
        while (p.size() > 1) {
            Huffmannode p1 = p.peek();
            p.poll();
            Huffmannode p2 = p.peek();
            p.poll();
            Huffmannode newp = new Huffmannode();
            newp.freq = p1.freq + p2.freq;
            newp.ch = '-';
            newp.left = p1;
            newp.right = p2;
            root = newp;
            p.offer(newp);

        }
        return p.poll();
    }

    public String encode(String str) {
        HashMap<Character, Integer> map = Createhashmap(str);
        buildtree(map);
        setcode(root, "");
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
           sb.append(encoder.get(str.charAt(i)));
        }
        return sb.toString();
    }
    public void printEncoder(){
        Set<Character>keys=encoder.keySet();
        for(Character key:keys){
            System.out.println(key+"=>"+encoder.get(key));
        }
    }
    public void printDecoder(){
        Set<String>keys=decoder.keySet();
        for(String key:keys){
            System.out.println(key+"=>"+decoder.get(key));
        }

    }

public String decode(ArrayList<Boolean> arr) {
    StringBuilder sb = new StringBuilder();
    StringBuilder key = new StringBuilder();
    for (int i = 0; i < arr.size(); i++) {
        if (arr.get(i)) {
            key.append("1");
        } else {
            key.append("0");
        }

        if (decoder.containsKey(key.toString())) {
            sb.append(decoder.get(key.toString()));
            key = new StringBuilder();
        }
    }

    return sb.toString();
}

    private void setcode(Huffmannode node, String ans) {
        if (node.left == null && node.right == null) {
            encoder.put(node.ch, ans);
            decoder.put(ans,node.ch);
            return;
        }
        setcode(node.left, ans + '0');
        setcode(node.right, ans + '1');
    }

    private HashMap<Character, Integer> Createhashmap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), 0);
        }
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
        }
        return map;
    }

}
