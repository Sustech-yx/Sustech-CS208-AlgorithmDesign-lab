package Lab9;

import java.io.*;
import java.util.*;

public class MainB {
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
        ini();
        solve();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        // Run r = new Run();
        int n = in.nextInt();
        // r.start();
        int[] u = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        String[] temp  = new String[5];
        Arrays.sort(temp);
        int[] answer = new int[n];
        int cnt;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (u[i] >= u[j]) {
                    cnt ++;
                }
            }
            answer[cnt] ++;
        }
        // int count = 0;
        for (int i = 0; i < n; i++) {
            out.println(answer[i]);
            // count += answer[i];
        }
//        out.println(count==n);
        // r.end();
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
            out.println((endTime - startTime) + "ms");
        }
    }
}

