package Lab9;

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
        // Run r = new Run();
        // r.start();
        ini();
        solve();
        // r.end();
        out.close();
    }
    static void ini () {

    }
    static long count;

    static void solve() {
        count = 0;
        int length;
        int[] array;
        length = in.nextInt();
        array = new int[length];
        for (int i = 0; i < length; i ++) {
            array[i] = in.nextInt();
        }
        merge(array, length);
        out.println(count);
    }
    private static int[] combine(int[] a, int[] b, int n, int m) {
        int t = n + m;
        int[] result = new int[t];
        int i = 0;
        int j = 0;
        for (int k = 0; k < t; k++) {
            if (i < n && (j >= m || a[i] <= b[j])) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
                count += n - i;
            }
        }
        return result;
    }
    private static int[] merge (int[] n, int length) {
        if (length > 1) {
            int p = length / 2;
            int[] b = new int[p];
            int[] c = new int[length - p];
            System.arraycopy(n, 0, b, 0, p);
            System.arraycopy(n, p, c, 0, length - p);
            b = merge(b, p);
            c = merge(c, length - p);
            n = combine(b, c, p, length - p);
        }
        return n;
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

