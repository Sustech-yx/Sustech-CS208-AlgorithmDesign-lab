package Lab10;

import Utils.SampleGenerationDevice;

import java.io.IOException;

public class testA {
    public static void main(String[] args) throws IOException {
        SampleGenerationDevice spg = new SampleGenerationDevice();
        int n = 100;
        spg.write(n + "\n");
        int p, q, tmp;
        for (int i = 0; i < n; i++) {
            p = spg.random(100_000_000);
            q = spg.random(100_00);
            if (p > q) {
                tmp = p;
                p = q;
                q = tmp;
            }
            spg.write(p + " " + q);
            spg.line();
        }
        spg.work();
    }
}
