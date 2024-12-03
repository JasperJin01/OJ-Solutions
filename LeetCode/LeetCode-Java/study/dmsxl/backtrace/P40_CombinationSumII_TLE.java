package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己写的，超时的错误做法！
 * 这道题目主要关注「去重」的逻辑！
 */
public class P40_CombinationSumII_TLE {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    // 依然会有重复！是因为candidates中可能存在重复数字引起的
    // 感觉除了检查result中是否有这个数组之外，不知道有什么更好的方法
    private boolean hasExist() { // 检查result中这个数组是否已经存在
        for (List<Integer> list : result) {
            if (list.size() != path.size()) continue;
            boolean tmpFlag = true;
            // 将path复制一份
            List<Integer> tempPath = new ArrayList<>(path);
            List<Integer> tempList = new ArrayList<>(list);
            // 对两个数组排序
            tempPath.sort((a, b) -> a - b);
            tempList.sort((a, b) -> a - b);
            for (int i = 0; i < tempPath.size(); i++) {
                // FIXME 为什么推荐我不用 != 比较？
                if (tempPath.get(i) != tempList.get(i)) tmpFlag = false;
            }
            if (tmpFlag) return true;
        }
        return false;
    }


    // index作为for循环的起始下标
    private void process(int[] candidates, int target, int index, int sum) {
        if (sum == target) {
            if (!hasExist()) result.add(new ArrayList<>(path));
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
        }

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        process(candidates, target, 0, 0);
        return result;
    }

    public static void main(String[] args) {
        P40_CombinationSumII_TLE p = new P40_CombinationSumII_TLE();
        System.out.println(p.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }

}
