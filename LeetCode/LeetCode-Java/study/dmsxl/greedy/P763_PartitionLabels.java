package study.dmsxl.greedy;

import java.util.ArrayList;
import java.util.List;


public class P763_PartitionLabels {
    // https://leetcode.cn/problems/partition-labels/

    // date: 2024-11-04
    // 这个题目，从前往后遍历，遍历到当前字符c，那么后续的所有c字符就必须包含到当前子串中
    // 所以一定需要知道每个字符最后出现在字符串的哪里（从后向前遍历一遍就可）
    // 如何能够得到一个划分后的子串？
    // 从一个位置开始遍历，只有当遍历到的位置必须包含了当前子串包含字符的最远下标才可以（这个过程模拟一遍实现即可）
    public static List<Integer> partitionLabels(String s) {
        char[] ss = s.toCharArray();
        int[] end = new int[30];
        for (int i = 0; i < end.length; i++)
            end[i] = -1;
        int N = ss.length;
        for (int i = N - 1; i >= 0; i--) {
            int cur = ss[i] - 'a';
            if (end[cur] == -1)
                end[cur] = i;
        }
        List<Integer> list = new ArrayList<>();
        // 从头开始循环
        int f = 0;
        int r = 0; // r是当前子串必须包含的最远位置下标
        for (int i = 0; i < N; i++) {

            int cur = ss[i] - 'a';
            // 把 end[cur]和r比较，r = max(r,end[cur])
            r = Math.max(r, end[cur]);
            // System.out.println("r: " + r);

            // 如果i==r，说明这一个列表已经满足了，list.push(i-f)，f++,r++
            if (i == r) {
                // System.out.println("满足条件！");
                // System.out.println("i: " + i);
                // System.out.println("f: " + f);
                // System.out.println("r: " + r);
                list.add(i - f + 1);
                f = i + 1;
                r = i + 1;
            }

            // 查看字符s[f]出现的最后位置，
        }
        return list;
    }
}
