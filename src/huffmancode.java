import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class huffmancode {

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

    public huffmancode() {
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

    public void encode(String str) {
        HashMap<Character, Integer> map = Createhashmap(str);
        buildtree(map);
        setcode(root, "");
        System.out.println(encoder);

        for (int i = 0; i < str.length(); i++) {
            System.out.print(encoder.get(str.charAt(i)));
        }
    }

    public void decode(String str) {
        StringBuilder sb = new StringBuilder();
        decode(this.encoder);
        int count=1;
        for (int i = 0; i <str.length(); i++) {
            sb.append(str.charAt(i));
            if (decoder.containsKey(sb.toString())) {
                System.out.print(decoder.get(sb.toString()));
                sb.delete(0,count);
                count=1;
            } else {
                count++;
            }
        }
        System.out.println();
        System.out.println(decoder);
    }

    private void decode(HashMap<Character, String> map) {
        Set<Character> keys = map.keySet();
        for (Character key : keys) {
            decoder.put(map.get(key), key);
        }
    }


    private void setcode(Huffmannode node, String ans) {
        if (node.left == null && node.right == null) {
            encoder.put(node.ch, ans);
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
