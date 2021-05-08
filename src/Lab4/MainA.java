package Lab4;

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
        int n = in.nextInt();
        int m = in.nextInt();
        LinkedList answer = new LinkedList();
        vertex[] vertices = new vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new vertex(i + 1);
        }
        int u, v;
        for (int i = 0 ; i < m; i ++) {
            u = in.nextInt();
            v = in.nextInt();
            vertices[u-1].adjacentTo.add(vertices[v-1]);
        }

        for (vertex ver : vertices) {
            for (vertex tex : ver.adjacentTo) {
                tex.inDegree ++;
            }
        }
        MyPriorityQueue q = new MyPriorityQueue();
        for (vertex ver : vertices) {
            if (ver.inDegree == 0)
                q.push(ver);
        }
        while (!q.isEmpty()) {
            vertex vertex = q.poll();
            for (vertex tex : vertex.adjacentTo) {
                if (-- tex.inDegree == 0) {
                    q.push(tex);
                }
            }
            answer.add(vertex);
        }
        answer.returnResult();
        out.close();
    }
    static class vertex {
        int inDegree;
        int value;
        vertex next;
        ArrayList<vertex> adjacentTo;
        public vertex (int value) {
            inDegree = 0;
            this.value = value;
            adjacentTo = new ArrayList<>(3);
        }
    }
    static class LinkedList {
        vertex head;
        vertex nowPointer;
        public LinkedList () {}
        public void add (vertex v) {
            if (head == null) {
                head = v;
                nowPointer = head;
            }
            else {
                nowPointer.next = v;
                nowPointer = nowPointer.next;
            }
        }
        public void returnResult () {
            vertex v = head;
            if (v != null) {
                do {
                    out.print(v.value + " ");
                    v = v.next;
                } while (v != null);
            }
        }
    }
    static class MyPriorityQueue {
        private vertex[] queue;
        private int size = 0;
        private Comparator<vertex> comparator;
        private static final int MAX_SIZE = Integer.MAX_VALUE - 1024;
        public MyPriorityQueue(Comparator<vertex> comparator, int capacity) {
            this.comparator = comparator;
            queue = new vertex[capacity];
        }
        public MyPriorityQueue() {
            this(new Comparator<vertex>() {
                @Override
                public int compare(vertex o1, vertex o2) {
                    return o1.value - o2.value;
                }
            }, 11);
        }
        public boolean isEmpty () {
            return size == 0;
        }
        public void reSize () {
            int newSize = (int)(size * 1.5) + 3;
            vertex[] copy = new vertex[newSize];
            System.arraycopy(queue, 0, copy, 0, queue.length);
            queue = copy;
        }
        private void siftUp (int k, vertex E) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                if (comparator.compare(E, queue[parent]) >= 0) {
                    break;
                }
                queue[k] = queue[parent];
                k = parent;
            }
            queue[k] = E;
        }
        private void siftDown (int k, vertex E) {
            int half = size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                int right = child + 1;
                if (right < size && comparator.compare(queue[child], queue[right]) > 0) {
                    child = right;
                }
                if (comparator.compare(E, queue[child]) <= 0) {
                    break;
                }
                queue[k] = queue[child];
                k = child;
            }
            queue[k] = E;
        }
        public vertex peek () {
            if (size == 0) {
                return null;
            }
            return queue[0];
        }
        public vertex poll () {
            if (size == 0) {
                return null;
            }
            vertex result = queue[0];
            vertex x = queue[--size];
            queue[size] = null;
            if (size != 0) {
                siftDown(0, x);
            }
            return result;
        }
        public void push (vertex x) {
            if (size >= queue.length) {
                reSize();
            }
            if (size == 0) {
                queue[size++] = x;
            } else {
                siftUp(size++, x);
            }
        }
    }
}
