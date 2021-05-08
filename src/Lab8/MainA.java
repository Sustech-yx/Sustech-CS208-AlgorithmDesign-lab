package Lab8;

import java.io.*;
import java.util.*;

public class MainA {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }
    }
    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        Run r = new Run();
        int t = in.nextInt();
        r.start();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static Comparator<Node> cmp = Comparator.comparingInt(o -> o.cnt);
    static void solve() {
        char[] elements = in.next().toCharArray();
        int[] count = new int[26];
        int n = elements.length;
        for (int i = 0; i < n; i++) {
            count[elements[i] - 'a'] ++;
        }
        ArrayList<Node> list = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;
            list.add(new Node((char)(i + 'a'), count[i]));
        }
        list.sort(cmp);
        Node temp1, temp2;
        ArrayList<Node> tempList;
        while (list.size() != 1) {
            temp1 = list.get(0);
            temp2 = list.get(1);
            list.remove(temp1);
            list.remove(temp2);
            tempList = new ArrayList<>(2);
            tempList.add(temp1);
            tempList.add(temp2);
            list.add(new Node(tempList));
            list.sort(cmp);
        }
        root = list.get(0);
        calValue();
        out.println(value);
    }
    static long value;
    static Node root;
    static void calValue () {
        value = 0;
        calValue(root, 0);
    }
    static void calValue (Node node, int height) {
        if (node.isLeaf) {
            value += (long) height * node.cnt;
        } else {
            for (Node node1 : node.children) {
                calValue(node1, height + 1);
            }
        }
    }
    static class Node {
        char c;
        int cnt;
        ArrayList<Node> children;
        boolean isLeaf;
        public Node (char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
            isLeaf = true;
        }

        public Node (ArrayList<Node> nodes) {
            isLeaf = false;
            children = nodes;
            cnt = 0;
            for (Node node : nodes) {
                cnt += node.cnt;
            }
        }
    }
    static class Run {
        long startTime;
        long endTime;
        public Run () {}

        public void start () {
            startTime = System.currentTimeMillis();
        }
        public void end () {
            endTime = System.currentTimeMillis();
            // out.println((endTime - startTime) + "ms");
        }
    }
}

