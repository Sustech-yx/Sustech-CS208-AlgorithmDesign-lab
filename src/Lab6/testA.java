package Lab6;

import Utils.SampleGenerationDevice;

import java.io.IOException;

public class testA {
    public static void main(String[] args) throws IOException {
        SampleGenerationDevice spg = new SampleGenerationDevice();
        int n = spg.random(500_000);
        spg.write(n);
        spg.line();
        spg.gen(n, 100_000_000, true);
        spg.work();
    }
}
