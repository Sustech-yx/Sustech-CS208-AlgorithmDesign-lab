package Lab3;

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
        int t = in.nextInt();
        while (t -- > 0) {
            solve();
        }
        // r.end();
        out.close();
    }
    static void ini () {
        hash = new int[3];
        hash[0] = 1;
        hash[1] = 101;
        hash[2] = 101 * 101;
    }
    static void solve() {
        s = in.nextInt();
        n = in.nextInt();
        m = in.nextInt();

        if (m == n) {
            out.println(1);
            return;
        }
        if (s % 2 == 1) {
            out.println("impossible");
            return;
        }
        if (m > n) {
            int temp = n;
            n = m;
            m = temp;
        }
        Graph graph = new Graph(s, n, m);
        graph.work();
        out.println(graph.answer == -1 ? "impossible" : graph.answer);
    }
    static int s, n, m; // s > n >= m
    static class Graph {
        int answer = 0;
        Map<Integer, Vertex> map = new HashMap<>();
        Vertex start;
        Vertex end;
        LinkedList<Vertex> pro = new LinkedList<>();
        public Graph (int a, int b, int c) {
            start = new Vertex(a, 0, 0);
            map.put(toHash(start), start);
            for (Vertex v : start.findAdjTo()) {
                pro.addLast(v);
                start.to.add(v);
                map.put(toHash(v), v);
            }
        }
        public void work () {
            if (map.get(toHash(new Vertex(s/2, s/2, 0))) != null) {
                answer = 1;
                return;
            }
            Vertex ver;
            while (!pro.isEmpty()) {
                ver = pro.getFirst();
                Vertex temp;
                for (Vertex v : ver.findAdjTo()) {
                    temp = map.get(toHash(v));
                    if (temp == null) {
                        map.put(toHash(v), v);
                        ver.to.add(v);
                        pro.addLast(v);
                    } else {
                        ver.to.add(temp);
                    }
                }
                pro.removeFirst();
            }
            end = map.get(toHash(new Vertex(s/2, s/2, 0)));
            if (end == null) {
                answer = -1;
                return;
            }
            bfs();
        }
        public void bfs () {
            LinkedList<Vertex> q = new LinkedList<>();
            q.addLast(start);
            start.distance = 0;
            Vertex vertex;
            while (!q.isEmpty()) {
                vertex = q.getFirst();
                if (vertex.visited) {
                    q.removeFirst();
                    continue;
                }
                vertex.visited = true;
                for (Vertex v:vertex.to) {
                    if (!v.visited) {
                        q.addLast(v);
                    }
                    v.distance = Math.min(v.distance, vertex.distance + 1);
                }
                q.removeFirst();
            }
            answer = end.distance == MAGIC_NUMBER ? -1 : end.distance;
        }
    }
    static final int MAGIC_NUMBER = Integer.MAX_VALUE;
    static class Vertex {
        int a;
        int b;
        int c;
        int distance = MAGIC_NUMBER;
        boolean visited;
        ArrayList<Vertex> to;
        public Vertex (int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            to = new ArrayList<>();
            visited = false;
        }
        public ArrayList<Vertex> findAdjTo () {
            ArrayList<Vertex> temp = new ArrayList<>(6);
            int o, p, q;
            if (a != 0 && b != n) {
                p = Math.min(n, a+b);
                o = a - (p - b);
                q = c;
                temp.add(new Vertex(o, p, q));
            }
            if (a != 0 && c != m) {
                q = Math.min(m, a+c);
                o = a - (q - c);
                p = b;
                temp.add(new Vertex(o, p, q));
            }
            if (b != 0 && a != s) {
                o = Math.min(s, a+b);
                p = b - (o - a);
                q = c;
                temp.add(new Vertex(o, p, q));
            }
            if (b != 0 && c != n) {
                q = Math.min(m, b+c);
                p = b - (q - c);
                o = a;
                temp.add(new Vertex(o, p, q));
            }
            if (c != 0 && a != s) {
                o = Math.min(s, a+c);
                q = c - (o - a);
                p = b;
                temp.add(new Vertex(o, p, q));
            }
            if (c != 0 && b != n) {
                p = Math.min(n, b+c);
                q = c - (p - b);
                o = a;
                temp.add(new Vertex(o, p, q));
            }
            return temp;

        }
    }
    static int[] hash;
    static int toHash (Vertex v) {
        int a, b, c;
        a = v.a; b = v.b; c = v.c;
        return a * hash[0] + b * hash[1] + c * hash[2];
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

