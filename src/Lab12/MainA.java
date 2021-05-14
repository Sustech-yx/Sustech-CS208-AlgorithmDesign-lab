package Lab12;

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
        solve();
        out.close();
    }
    static void ini () {

    }
    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] dp = new long[n + 1][m + 1];
        long[] beauty = new long[n+1];
        long[] money = new long[n+1];
        for (int i = 1; i <= n; i++) {
            beauty[i] = in.nextLong();
        }
        for (int i = 1; i <= n; i++) {
            money[i] = in.nextLong();
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }
        long max1, max2;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (money[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    max1 = dp[i-1][(int) (j - money[i])] + beauty[i];
                    max2 = dp[i-1][j];
                    dp[i][j] = Math.max(max1, max2);
                }
            }
        }
        out.println(dp[n][m]);
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
            // out.println("\n" + (endTime - startTime) + "ms");
        }
    }
}

