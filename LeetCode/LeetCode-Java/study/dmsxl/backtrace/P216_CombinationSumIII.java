package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己写的代码，并且通过了
 * 写代码之前，画了树形图，发现剪枝的范围（或者说for循环i的范围）和 树的高度有一定的关系
 * （我第一遍做题的时候没看到限制数字 1-9...
 * 我想保证path数组中的值是递增的，所以for遍历的i值 必须小于 (n-sum)/k
 * 为什么是小于，不是小于等于？这个是根据模拟过程得出的（这样写代码过了...不过其实也比较好理解，
 * i值是后续递归函数的起始值，等于的情况是当后续所有值都等于起始值的情况下和为n，但数字递增，所以肯定不成立）
 *
 * 另外这里要注意一下 (n-sum)/k 取天花板，这个也是在画树模拟的时候发现的。(9-2)/2 = 3.5，如果取3就被剪枝了
 *
 * 最开始没有看到数字限制范围，所以写的代码的思路是严格控制递增，然后通过 n-sum 得到最后一个 path 值
 * 然后为了实现数字限制，就加了一行 if (lastNum > 9) return;
 *
 */
public class P216_CombinationSumIII {
    // https://leetcode.cn/problems/combination-sum-iii/

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int sum = 0;


    void process(int k, int n, int startNum, int totk) {
        if (totk - path.size() == 1) {
            int lastNum = n - sum;
            if (lastNum > 9) return;
            path.add(lastNum);
//            System.out.println("path = " + path);
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        int to = (n - sum + k - 1) / k;
        for (int i = startNum; i < to; i++) {
            path.add(i);
            sum += i;

            process(k - 1 , n, i + 1, totk);

            sum -= i;
            path.remove(path.size() - 1);
        }

    }

    // k个数，加和为n
    public List<List<Integer>> combinationSum3(int k, int n) {
        process(k, n, 1, k);
        return result;
    }

    public static void main(String[] args) {
        P216_CombinationSumIII p = new P216_CombinationSumIII();
        System.out.println(p.combinationSum3(3,7));
    }

}
