package Lab10;

import java.io.*;
import java.util.*;

public class MainB{
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
    static long[] answer = new long[200_005];
    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int minW, maxW;
        int x, y, z, x1, y1;
        minW = Integer.MAX_VALUE;
        maxW = Integer.MIN_VALUE;
        ArrayList<Edge> edgeArrayList = new ArrayList<>(n - 1);
        for (int i = 0; i < n - 1; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            edgeArrayList.add(new Edge(x, y, z));
            minW = Math.min(minW, z);
            maxW = Math.max(maxW, z);
        }
        edgeArrayList.sort(Edge::compareTo);
        find = new int[n + 1];
        long[] size = new long[n+1];
        size[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            find[i] = i;
            size[i] = 1;
        }
        Edge temp;
        for (int i = 0; i < n-1; i++) {
            temp = edgeArrayList.get(i);
            x = temp.u;
            y = temp.v;
            z = temp.w;
            x1 = findT(x);
            y1 = findT(y);
            if (x1 != y1) { // Actually, it is always true.
                find[x1] = y1;
                answer[z] += size[y1] * size[x1];
                size[y1] += size[x1];
            }
        }
        for (int i = minW + 1; i <= maxW; i++) {
            answer[i] += answer[i-1];
        }
        for (int i = maxW; i < 200_005; i++) {
            answer[i] = answer[maxW];
        }
        for (int i = 0; i < m - 1; i++) {
            out.print(answer[in.nextInt()] + " ");
        }
        out.print(answer[in.nextInt()]);
    }
    static int[] find;
    static int findT (int index) {
        if (index == find[index]) return index;
        else {
            find[index] = findT(find[index]);
            return find[index];
        }
    }
    static class Edge implements Comparable<Edge>{
        int u;
        int v;
        int w;
        public Edge (int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }

        public int getW() {
            return w;
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

