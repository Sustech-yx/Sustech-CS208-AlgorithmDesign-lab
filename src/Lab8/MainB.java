package Lab8;

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
        // Run r = new Run();
        // r.start();
        solve();
        // r.end();
        out.close();
    }
    static final int MAGIC_INT = 33;
    static long[] pow = new long[MAGIC_INT];
    static boolean[][] result = new boolean[MAGIC_INT][2];
    static void ini () {
        pow[0] = 1;
        for (int i = 1; i < MAGIC_INT; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }
    static void solve() {
        int n = in.nextInt();
        long m = in.nextInt();
        ini();
        char[] caoZuo = new char[n];
        long[] caoZuoNum = new long[n];
        for (int i = 0; i < n; i++) {
            caoZuo[i]       = in.next().charAt(0);
            caoZuoNum[i]    = in.nextInt();
        }
        long test0, test1;
        for (int i = MAGIC_INT - 1; i >= 0; i--) {
            test0 = 0;
            test1 = m;
            for (int j = 0; j < n; j++) { // implement the n's operation for n steps. For each digital.
                switch (caoZuo[j]) {
                    case 'A':
                        test0 &= caoZuoNum[j];
                        test1 &= caoZuoNum[j];
                        break;
                    case 'O':
                        test0 |= caoZuoNum[j];
                        test1 |= caoZuoNum[j];
                        break;
                    case 'X':
                        test0 ^= caoZuoNum[j];
                        test1 ^= caoZuoNum[j];
                        break;
                    default:
                }
            }
            // Judge that whether 0 or 1 will make this digital is 1. If only 0 can make this digital is 1, then leave 0 along.
            // If both 1 and 0 can make this digital 1. Leave 0, because there may be other case we need to change 0 to 1;
            // If only 1 can make this digital 1. Then check that whether we can change this digital 1.
            result[i][0] = (test0&pow[i]) > 0;
            result[i][1] = (test1&pow[i]) > 0;
        }
        // We must keep the final answer is not greater than m. In other words, keep m > 0.
        int x = 0;
        for (int i = MAGIC_INT - 1; i >= 0; i --) {
            if (!result[i][0] && result[i][1]) {
                if (m >= pow[i]) {
                    m -= pow[i];
                    x += pow[i];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            switch (caoZuo[i]) {
                case 'A':
                    x &= caoZuoNum[i];
                    break;
                case 'O':
                    x |= caoZuoNum[i];
                    break;
                case 'X':
                    x ^= caoZuoNum[i];
                    break;
                default:
            }
        }
        out.println(x);
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

