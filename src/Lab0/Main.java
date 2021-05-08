package Lab0;

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


    static final long MOD = 998244353;
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
    static void solve() {
        int n = in.nextInt();
        int L = in.nextInt();

        long[] dp = new long[n + 5];
        dp[0] = 1;
        long temp = 0;
        for (int i = 1; i <= n; i++) {
            if (i <= L) {
                temp = (dp[i-1] + temp) % MOD;
            } else {
                temp = (temp - dp[i-L-1] + dp[i-1]) % MOD;
            }
            temp = temp < 0 ? temp + MOD : temp;
            dp[i] = temp;
        }
        out.println(dp[n]);
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
/*
1
5000 80
288642688

2
2 1
4 2
 */