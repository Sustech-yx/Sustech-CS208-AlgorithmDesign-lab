package Lab7;

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
    static long[] fibList = new long[30];
    static void ini () {
        fibList[0] = 1;
        fibList[1] = 1;
        for (int i = 2; i < 30; i++) {
            fibList[i] = fibList[i-1] + fibList[i-2];
        }
    }
    static int[] find;
    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int cnt = 0;
        int max, min;
        int from, to;
        int temp1, temp2;
        boolean hasQuan;
        Edge[] edges = new Edge[m];
        find = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            find[i] = i;
        }
        for (int i = 0; i < m; i++) {
            from = in.nextInt();
            to = in.nextInt();
            hasQuan = in.nextInt() == 1;
            edges[i] = new Edge(from, to, hasQuan);
            temp1 = findT(from); temp2 = findT(to);
            if (temp1 != temp2) {
                find[temp1] = temp2;
                cnt ++;
            }
        }
        if (cnt < n - 1) {
            out.println("No");
            return;
        }
        cnt = 0;
        for (int i = 1; i <= n; i++) {
            find[i] = i;
        }
        for (int i = 0; i < m; i++) {
            from = edges[i].from; to = edges[i].to; hasQuan = edges[i].hasQuan;
            temp1 = findT(from); temp2 = findT(to);
            if (!hasQuan) continue;
            if (temp1 != temp2) {
                find[temp1] = temp2;
                cnt ++;
            }
        }
        max = cnt;
        cnt = 0;
        for (int i = 1; i <= n; i++) {
            find[i] = i;
        }
        for (int i = 0; i < m; i++) {
            from = edges[i].from; to = edges[i].to; hasQuan = edges[i].hasQuan;
            temp1 = findT(from); temp2 = findT(to);
            if (hasQuan) continue;
            if (temp1 != temp2) {
                find[temp1] = temp2;
                cnt ++;
            }
        }
        min = n - 1 - cnt;
        // out.println("max=" + max + "min=" + min);
        for (int i = 0; i < 30; i++) {
            if (min <= fibList[i] && max >= fibList[i]) {
                out.println("Yes");
                return;
            }
        }
        out.println("No");
    }
    static int findT (int index) {
        if (index == find[index]) return index;
        else {
            find[index] = findT(find[index]);
            return find[index];
        }
    }
    static class Edge {
        int from;
        int to;
        boolean hasQuan;
        public Edge (int from, int to, boolean hasQuan) {
            this.from = from;
            this.to = to;
            this.hasQuan = hasQuan;
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
            out.println((endTime - startTime) + "ms");
        }
    }
}
