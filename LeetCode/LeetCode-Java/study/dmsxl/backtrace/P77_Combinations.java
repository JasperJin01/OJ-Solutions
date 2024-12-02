package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;


/**
 *     // 传递的参数：[front,rear]范围 k个数的组合
 *     // List path记录过程组合列表
 *     // 在process处理过程中，带一个for循环，i从front到rear，i是作为放入path中的元素
 *     // 放入后在进行process递归
 *
 *     // PS：所有的递归都可以抽象成一个树的结构。在思考这道题的时候，process中如何处理有两种想法
 *     // 想法1是用一个for循环进行遍历（就是上述的过程）
 *     // 想法2是分为把f放进path和不把f放进path两种思路，然后递归
 *     // 两种想法对应的是画两个递归树，但很显然想法1的递归树更简洁，也更适合处理这样的组合问题
 *     // 想法2更适合处理DP、树状DP
 *     // 而组合问题和DP问题一般来讲返回的内容也不太一样。组合问题会返回列表，而DP问题会返回值
 *
 *     // 剪枝的操作也可以放到for循环中进行
 */
public class P77_Combinations {
    // https://leetcode.cn/problems/combinations/

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    // [front,rear]范围 k个数的组合 [3,4] k=2
    public void process(int front, int rear, int k) {
        if (k == path.size()) {
            // 将path数组复制一份，保存到result中
            result.add(new ArrayList<>(path));
            return;
        }
        // 剪枝
//        if (rear - front + 1 + path.size() < k) return;

        // NOTE 这个剪枝for循环的值要注意！
        for (int i = front; i <= rear - (k - path.size()) + 1; i++) {
//        for (int i = front; i <= rear; i++) {
            path.add(i);
            process(i + 1, rear, k);
            // 这里注意，回溯需要弹出！
            path.remove(path.size() - 1);
        }

    }

    public List<List<Integer>> combine(int n, int k) {
        process(1, n, k);
        return result;
    }

    public static void main(String[] args) {
        P77_Combinations p = new P77_Combinations();
//        List<List<Integer>> result = p.combine(4, 2);
        List<List<Integer>> result = p.combine(4, 2);
        System.out.println("result = " + result);
    }

}
