package Lab9;

import Utils.SampleGenerationDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class testB {
    public static void main(String[] args) throws IOException {
        SampleGenerationDevice sgd = new SampleGenerationDevice();
        int n = sgd.random(15000);
        sgd.write(n);
        sgd.line();
        ArrayList<Pattern> list = new ArrayList<>(n);
        int u, v;
        Pattern p;
        for (int i = 0; i < n; i++) {
            u = sgd.random(30_000);
            v = sgd.random(30_000);
            p = new Pattern(u, v);
            if (list.contains(p)) {
                i --;
                continue;
            }
            list.add(p);
        }
        list.sort(Pattern::compareTo);
        for (int i = 0; i < n; i++) {
            sgd.write(list.get(i).toString());
            sgd.line();
        }
        sgd.work();
    }
    static class Pattern implements Comparable<Pattern>{
        int u, v;
        public Pattern (int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pattern pattern = (Pattern) o;
            return u == pattern.u && v == pattern.v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }

        @Override
        public int compareTo(Pattern o) {
            if (this.v > o.v) return 1;
            else if (this.v < o.v) return -1;
            else return Integer.compare(this.u, o.u);
        }

        @Override
        public String toString() {
            return u + " " + v;
        }
    }
}
