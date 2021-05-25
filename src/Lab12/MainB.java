package Lab12;

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

    static long[] slimes;

    static void solve() {
        int n = in.nextInt();
        if (n == 0) {
            out.println(0);
            return;
        }
        slimes = new long[n];
        long[][] dp = new long[n+1][n+1];
        for (int i = 0; i < n; i++) {
            slimes[i] = in.nextInt();
        }
        int left, right;
        long min;
        for (int le = 2; le <= n; le ++) {
            for (left = 1; left + le - 1 <= n; left ++) {
                right = le + left - 1;
                min = Long.MAX_VALUE;
                for (int mid = left; mid < right; mid++) {
                    min = Math.min(min, dp[left][mid] + dp[mid+1][right] + cal(left-1, right-1));
                }
                dp[left][right] = min;
            }
        }
        out.println(dp[1][n]);
    }
    static long cal (int left, int right) {
        long result = 0;
        for (int i = left; i <= right; i ++) {
            result += slimes[i];
        }
        return result;
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

