package Lab4;

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
        r.start();
        ini();
        int t = 1;
        while (t -- > 0) {
            solve();
        }
        r.end();
        out.close();
    }
    static long answer;
    static long temp;
    static class Graph {
        ArrayList<Integer>[] adjTo;
        int[] weight;
        long[] subAnswerList;

        public Graph () {
            int n = in.nextInt();
            weight = new int[n];
            adjTo = new ArrayList[n];
            subAnswerList = new long[n];
            for (int i = 0; i < n; i++) {
                weight[i] = in.nextInt();
                adjTo[i] = new ArrayList<>();
            }
            int a, b;
            for (int i = 1; i < n; i++) {
                a = in.nextInt() - 1;
                b = in.nextInt() - 1;
                adjTo[a].add(b);
                adjTo[b].add(a);
            }
            dfs1(0, -1, 0);
            answer = temp;
            // We obtain the answer when we choose node 0 as the root.
            // Then try using other node as root to update the answer.
            dfs2(0, -1);
        }

        private void dfs1 (int now, int from, int height) {
            temp = (long) height * weight[now] + temp;
            subAnswerList[now] = weight[now];
            for (int to :
                    adjTo[now]) {
                if (from == to) continue;
                dfs1(to, now, height + 1);
                subAnswerList[now] += subAnswerList[to];
            }
        }
        private void dfs2 (int now, int from) {
            update();

            for (int to :
                    adjTo[now]) {
                if (from == to) continue;
                subAnswerList[now] = subAnswerList[now] - subAnswerList[to];
                temp = temp - subAnswerList[to];

                temp = temp + subAnswerList[now];
                subAnswerList[to] = subAnswerList[to] + subAnswerList[now];


                dfs2(to, now);
                // Restore the scene.
                temp = temp - subAnswerList[now];
                subAnswerList[to] = subAnswerList[to] - subAnswerList[now];

                subAnswerList[now] = subAnswerList[now] + subAnswerList[to];
                temp = temp + subAnswerList[to];
            }
        }
        private void update () {
            answer = Math.max(answer, temp);
        }
    }
    static void ini () {
        answer = 0;
        temp = 0;
    }
    static void solve() {
        Graph graph = new Graph();
        out.println(answer);
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

