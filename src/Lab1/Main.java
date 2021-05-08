package Lab1;

import java.io.*;
import java.util.*;

public class Main {
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
        r.start();
        int t = 1;
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static void solve() {
        int n = in.nextInt();
        Map<String, Integer> i_boy = new HashMap<>();
        Map<String, Integer> i_girl = new HashMap<>();
        String[] boyName = new String[n];
        String[] girlName = new String[n];
        for (int i = 0; i < n; i++) {
            boyName[i] = in.next();
            i_boy.put(boyName[i], i);
        }
        for (int i = 0; i < n; i++) {
            girlName[i] = in.next();
            i_girl.put(girlName[i], i);
        }

        int[][] boyList = new int[n][n];
        int[][] girlList = new int[n][n];
        int[][] girlMua = new int[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                boyList[i][j] = i_girl.get(in.next());
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                girlList[i][j] = i_boy.get(in.next());
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                girlMua[i][girlList[i][j]] = j;
            }
        }
        Map<Integer, Integer> result = solution(n, boyList, girlMua);
        for (int i = 0; i < n; i ++) {
            out.print(boyName[i] + " ");
            out.println(girlName[result.get(i)]);
        }
    }
    static Map<Integer, Integer> solution (int n, int[][] boyList, int[][] girlList) {
        Map<Integer, Integer> result = new HashMap<>();
        LinkedList<Integer> f_boy = new LinkedList<>();
        int[] manPointer = new int[n];
        int[] laoGong = new int[n];
        for (int i = 0; i < n; i++) {
            f_boy.add(i);
            laoGong[i] = -1;
        }
        int nowBoy, nowGirl;
        while (!f_boy.isEmpty()) {
            nowBoy = f_boy.getFirst();
            nowGirl = boyList[nowBoy][manPointer[nowBoy]];
            manPointer[nowBoy] ++;

            if (laoGong[nowGirl] == -1) {
                result.put(nowBoy, nowGirl);
                f_boy.removeFirst();
                laoGong[nowGirl] = nowBoy;
            } else if (girlList[nowGirl][nowBoy]
                    < girlList[nowGirl][laoGong[nowGirl]]) {
                result.remove(laoGong[nowGirl]);
                result.put(nowBoy, nowGirl);
                f_boy.removeFirst();
                f_boy.addLast(laoGong[nowGirl]);
                laoGong[nowGirl] = nowBoy;
            }
        }
        return result;
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

