// https://leetcode.cn/problems/merge-intervals/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P56_MergeIntervals {
    // date: 2024-11-06
    // 题目思路:
    public static int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        // FIXME 这里的intervals怎么排序呢？如果只按照index0的排序，如果index0相等是否需要考虑下？
        // (在合并的过程中判断了一下)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        list.add(intervals[0]);
        // 1 3  1 6
        int p = 0;
        // TODO 可以不用下标p，list有getLast()方法
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= list.get(p)[1]) { // 可以合并
                list.get(p)[1] = Math.max(list.get(p)[1], intervals[i][1]);
            } else { // 不能合并，需要把新元素添加到list中
                list.add(intervals[i]);
                p++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }
    // NOTE
    // 将给定的二维数组按照第一个元素的大小排序
    // e.g. [[8,10],[2,6],[1,3],[15,18]] -> [[1,3],[2,6],[8,10],[15,18]]
    // Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
    // arr是待排序的数组，a和b是arr中两个待比较的元素
    // (a, b) -> Integer.compare(a[0], b[0]) 得到的是从小到大的顺序
    // list.sort((a, b) -> Integer.compare(a[0], b[0]));
    // list.sort((a, b) -> { // 如果使用大括号，需要return
    // return Integer.compare(a[0], b[0])});
    // Arrays.sort(arr, Comparator,comparing())

    // toArray(...)方法:
    // List接口方法，将List转换为数组
    // toArray(): 返回一个Object[]数组
    // toArray(T[] a): 返回指定类型的数组


    public static void main(String[] args) {
        int[][] intervals = {
            {8, 10},
            {1, 3},
            {2, 6},
            {15, 18}
        };
        int[][] arr = merge(intervals);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + "  " + arr[i][1]);
        }


    }

}
