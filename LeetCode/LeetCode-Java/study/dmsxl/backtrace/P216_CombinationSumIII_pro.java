package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯的递归函数其实是一个通过递归函数控制的for循环
 * 所以拿到这道题没有思路的时候，可以先考虑一下，如果参数简单（比如k=2、k=3），用循环如何实现
 *
 */
public class P216_CombinationSumIII_pro {
    // https://leetcode.cn/problems/combination-sum-iii/

    // https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    // k个数的组合，加和为n，从startInt到9循环，path当前加和为sum
    public void process(int k, int n, int sum, int startInt) {
        if (sum == n && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 剪枝: 加和已经大于n，或者加和等于n
        // 上面判断过加和等于n,所以到这里的sum==n一定是不满足 path.size条件
        // 即用 <k 的 size 加到了 n，这种也肯定剪枝
        if (sum >= n) return;

        // NOTE 代码随想录的代码依旧是在for循环的范围这里加的剪枝
        for (int i = startInt; i <= 9; i++) {
            path.add(i);
            sum += i;

            process(k, n, sum, i+1);

            sum -= i;
            path.remove(path.size() - 1);
        }


    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        process(k,n,0,1);
        return result;
    }

    public static void main(String[] args) {
        P216_CombinationSumIII_pro p = new P216_CombinationSumIII_pro();
        System.out.println(p.combinationSum3(3,1));
    }


}
