package contest.w426;

// https://leetcode.cn/problems/smallest-number-with-all-set-bits/description/
public class Q1 {

    // 输入二进制串，输出对应的十进制数
    public static int binToInt(String s) {
        int res = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        // 110 4+2 = 1*2^2
        for (int i = 0; i < N; i++) {
            int base = (1 << (N - i - 1));
            if (str[i] == '1') res += base;
        }
        return res;
    }

    public static int smallestNumber(int num) {
        int i = 0;
        while (binOne[i] < num) i++;
        return binOne[i];
    }

    static int[] binOne = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023};

    public static void main(String[] args) {
        System.out.println(smallestNumber(7));
//        String ss = "";
//        for (int i = 0; i < 10; i++) {
//            ss += "1";
//            System.out.println(binToInt(ss));
//        }
    }

}
