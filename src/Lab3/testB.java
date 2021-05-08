package Lab3;

import java.util.*;
import java.io.*;

public class testB {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        BufferedWriter bw = new BufferedWriter(new FileWriter("in.txt"));
        char[] c_list = new char[26];
        for (int i = 0; i < 26; i++) {
            c_list[i] = (char)(i + 'a'); // 默认26个字母都能出现。
        }

        int n = 10; // 测试样例个数
        bw.write(n + "\n");
        for (int i = 0; i < n; i++) {
            int len = 100000; // 数据规模
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append(c_list[random.nextInt(26)]);
            }
            sb.append("\n");
            bw.write(sb.toString());
        }
        bw.close();
    }
}
