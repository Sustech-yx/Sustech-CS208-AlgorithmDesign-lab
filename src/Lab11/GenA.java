package Lab11;

import java.io.*;

public class GenA {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./gen.txt"));
        String txt = "LR";
        for (int i = 0; i < 18; i++) {
            txt = swi(txt);
        }
        bufferedWriter.write(txt);
        bufferedWriter.close();
    }
    private static String swi (String in) {
        StringBuilder out;
        out = new StringBuilder(in);
        int n = in.length();
        for (int i = 0; i < n; i++) {
            switch (in.charAt(i)) {
                case 'L':
                    out.append('R');
                    break;
                case 'R':
                    out.append('N');
                    break;
                case 'N':
                    out.append('L');
                    break;
            }
        }
        return out.toString();
    }
}
