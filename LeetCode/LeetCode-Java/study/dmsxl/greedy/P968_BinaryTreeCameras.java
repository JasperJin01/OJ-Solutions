package study.dmsxl.greedy;
// https://leetcode.cn/problems/binary-tree-cameras/

public class P968_BinaryTreeCameras {
    // date: 2024-11-05
    public static int res = 0;
    public static int minCameraCover(TreeNode root) {
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
