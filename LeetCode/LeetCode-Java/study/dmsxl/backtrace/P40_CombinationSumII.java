package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这道题目主要关注「去重」的逻辑！
 */
public class P40_CombinationSumII {
    // https://leetcode.cn/problems/combination-sum-ii/

    // https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html
    // NOTE 代码随想录中用到的 used 数组并没有太懂

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    // 依然会有重复！是因为candidates中可能存在重复数字引起的
    // 感觉除了检查result中是否有这个数组之外，不知道有什么更好的方法



    // index作为for循环的起始下标
    private void process(int[] candidates, int target, int index, int sum) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) return;

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            // process的下标，这里是i+1,如果允许集合重复(P39)，下标是i
            process(candidates, target, i+1, sum);

            sum -= candidates[i];
            path.remove(path.size() - 1);

            while (i <= candidates.length - 2 && candidates[i] == candidates[i+1]) i++;
        }

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先排序，方便去重
        Arrays.sort(candidates);
        process(candidates, target, 0, 0);
        return result;
    }

    public static void main(String[] args) {
        P40_CombinationSumII p = new P40_CombinationSumII();
        System.out.println(p.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

}
