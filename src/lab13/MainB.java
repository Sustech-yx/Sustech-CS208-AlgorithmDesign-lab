package lab13;

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
        int t = in.nextInt();
        r.start();
        ini();
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static long[] pow;
    static void ini () {
        pow = new long[55];
        pow[0] = 1;
        for (int i = 1; i < 55; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }
    static int n;
    static int k;
    static long[] arr;
    static long[] preSum;
    static void solve() {
        n = in.nextInt();
        k = in.nextInt();
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        preSum = new long[n];
        preSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i-1] + arr[i];
        }
        // mei ju mei yi wei (pow[i]) qu ji suan zhe yi wei zui hou zai result zhong neng bu neng chu xian
        long answer = 0;
        for (int i = 51; i >= 0; i++) {
            if (check(pow[i])) {
                answer += i;
            }
        }
        out.println(answer);
    }
    static boolean check(long cha) {
        return false;
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
            out.println("\n" + (endTime - startTime) + "ms");
        }
    }
}

