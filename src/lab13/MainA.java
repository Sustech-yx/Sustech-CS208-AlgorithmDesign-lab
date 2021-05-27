package lab13;

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
        int t = 1;
        r.start();
        ini();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n-1];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = in.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = a[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-2] + b[i-2], dp[i-1] + a[i-1]);
        }
        out.println(dp[n]);
    }
    static class Run {
        long startTime;
        long endTime;
        public Run () {
            start();
        }

        public void start () {
            startTime = System.currentTimeMillis();
        }
        public void end () {
            endTime = System.currentTimeMillis();
            //out.println("\n" + (endTime - startTime) + "ms");
        }
    }
}

