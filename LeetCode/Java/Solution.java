import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/partition-labels/
class partitionLabels {
    // date: 241104
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

// https://leetcode.cn/problems/binary-tree-cameras/
class BinaryTreeCameras {
    public static int res = 0;
    public static int minCameraCover(TreeNode root) {
        // NOTE
        // LeetCode会多次调用函数方法！
        // 所以，如果使用静态变量，一定要记得初始化
        res = 0;
        int status = process(root);
        // System.out.println("status: " + status);
        if (status == 0) res++;
        return res;
    }
    
    // 每个节点的状态：无覆盖 0， 有摄像头 1， 无监控被摄像头覆盖 2
    // 需要从孩子节点得到的状态判断当前节点的状态，然后向上返回
    public static int process(TreeNode node) {
        // 先不考虑空节点的问题，假设进来的一定是有节点的

        // 如果是叶节点：骗叶子节点一下 为了让叶节点不占用摄像头的位置（这样可以尽可能少放摄像头）设置空节点状态为有摄像头
        if (node.left == null && node.right == null) return 0;
        // 只有一个孩子
        if (node.right == null || node.left == null) {
            int status = (node.left == null) ? process(node.right) : process(node.left);
            if (status == 0) {
                res++;
                // System.out.println("放置摄像头：" + node.val);
                return 1;
            }
            if (status == 1) {
                return 2;
            }
            return 0;
        }

        // 两个孩子
        int left = process(node.left);
        int right = process(node.right);
        // 这里注意一下处理的顺序
        if (left == 0 || right == 0) {
            res++;
            return 1;
        } else if (left == 1 || right == 1) { // 11 12 21 22
            return 2;
        }
        // 不是当前节点无覆盖就加摄像头，而是孩子节点无覆盖要添加摄像头
        return 0;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode root = new TreeNode(5);
        root.left = node4;
        node4.left = node3;
        node3.left = node2;
        node2.right = node1;
        System.out.println("minCC: " + minCameraCover(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
}

// https://leetcode.cn/problems/monotone-increasing-digits
class MonotoneIncreasingDigits {
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




