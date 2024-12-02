package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己写的
 */
public class P39_CombinationSum {
    // https://leetcode.cn/problems/combination-sum/


    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    void process(int[] candidates, int index, int target, int curSum) {
        if (curSum > target) return;
        if (curSum == target) {
//            System.out.println("path = " + path);
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int candi = candidates[i];
            path.add(candi);
            curSum += candi;

            process(candidates, i, target, curSum);

            curSum -= candi;
            path.remove(path.size() - 1);
        }

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        process(candidates,0, target, 0);
        return result;
    }

    public static void main(String[] args) {
        P39_CombinationSum p = new P39_CombinationSum();
        System.out.println(p.combinationSum(new int[]{2}, 1));
    }

}
