package contest.w426;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.cn/problems/identify-the-largest-outlier-in-an-array/
public class Q2 {
    // -1 5 4

    // 全加和：（一堆数） + 和 = 和 x 2
    // 2 + 3 + 5 + 10 = 20
    // 1,1,1,1,1,5,5

    public static int getLargestOutlier(int[] nums) {

        int allSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            allSum += num;
            if (!map.containsKey(num)) map.put(num, 1);
            else map.put(num, map.get(num) + 1);
        }
        int N = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int curNum = nums[i];
            map.put(curNum, map.get(curNum) - 1);
            int sum = allSum - curNum;
            int mapvalue = map.getOrDefault(sum / 2, -1);
            if (sum % 2 == 0 && mapvalue > 0) {
                res = Math.max(curNum, res);
            }
            map.put(curNum, map.get(curNum) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {6,-31,50,-35,41,37,-42,13};
        int sum = 0;
        for (int num: nums) sum+=num;
        System.out.println(sum);

//        int[] nums = {874,159,-838,-375,658};
//        System.out.println(
//                getLargestOutlier(nums)
//        );
    }

}
