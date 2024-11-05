// https://leetcode.cn/problems/monotone-increasing-digits/
public class P738_MonotoneIncreasingDigits {
    // date: 2024-11-05
    // 感觉这个题就记下来思路就可以了，这是一个典型的贪心想法
    // coding本身没有任何难度，但是需要考虑到正确的贪心算法是个问题
    // 1）从后向前遍历，且每发现不满足递增的情况就要考虑将当前位-1，然后后面的位设9
    // 2）在coding中并不能直接把后面的位设9，而是要用一个p记录最后一个应该为9的位置，遍历后再统一修改值
    // 为了解决的情况例如 1000，如果直接设为9，最终得到的是900.
    public static int monotoneIncreasingDigits(int num) {
        String s = Integer.toString(num);
        char[] ss = s.toCharArray();
        int N = ss.length;
        int p = N - 1;
        // 3 3 2
        // p
        // 2
        for (int i = N - 2; i >= 0; i--) {
            if (ss[i] > ss[i + 1]) {
                p = i;
                ss[i] -= 1;
            }
        }
        for (int i = p + 1; i < N; i++)
            ss[i] = '9';
        return Integer.valueOf(new String(ss));
    }
    // Java 将int转换成String: Integer.toString(123); String.valueOf(123);
    // Java 将String转换成int: Integer.valueOf("299"); Integer.parseInt("299");
    // Java 将String转换成char[]: String ss = "helloworld"; char[] str =
    // ss.toCharArray();
    // Java 将char[]转换成String: String ss = new String(str);

}
