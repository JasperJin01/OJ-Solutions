package contest.w426;

import java.util.*;

// https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/
public class Q3 {

    public static List<List<Integer>> buildGraph(int size, int[][] edges) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < size; i++) g.add(new ArrayList<>());
        for (int[] edge : edges) {
            g.get(edge[0]).add(edge[1]);
            g.get(edge[1]).add(edge[0]);
        }
        return g;
    }

    // 计算树所有节点为根，离该节点长度为 k 的节点的数量
    public static int[] countDistNode(int size, List<List<Integer>> g, int k) {
        if (k == 0) {
            int[] res = new int[size];
            for (int i = 0; i < size; i++) res[i] = 1;
            return res;
        }

        int[] distCnt = new int[size];
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[size];
        // 当前节点node i, 对所有节点求解的for循环
        for (int i = 0; i < size; i++) {
            // 内部是一个bfs

            int cnt = 1; // 自己也算一个节点
            Arrays.fill(dist, -1);
            dist[i] = 0;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int fromNode = queue.poll(); // from节点
                for (int toNode : g.get(fromNode)) {
                    if (dist[toNode] == -1) { // 没有访问过这个节点
                        dist[toNode] = dist[fromNode] + 1;
                        if (dist[toNode] < k) { // 把当前节点添加到queue中
                            queue.offer(toNode);
                        }
                            cnt++;
                    }
                }
            }
            distCnt[i] = cnt;
        }
        return distCnt;
    }

    // 首先求g1中节点i距离为k的节点数量
    // （每个节点i都要求，因为返回数组）

    // 然后求g2中，以哪个节点为root，距离k-1的节点的数量最多

    // 然后把这个结果加到g1的那个数组上
    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {

        int n = -1;
        int m = -1;
        for (int[] edge: edges1) n = Math.max(n, Math.max(edge[0], edge[1]));
        for (int[] edge: edges2) m = Math.max(m, Math.max(edge[0], edge[1]));
        n++;
        m++;
        if (k == 0) {
            int[] res = new int[n];
            for (int i = 0; i < n; i++) res[i] = 1;
            return res;
        }

//        System.out.println("n = " + n);
//        System.out.println("m = " + m);

        List<List<Integer>> t1 = buildGraph(n, edges1);
        List<List<Integer>> t2 = buildGraph(m, edges2);

        int[] dist1 = countDistNode(n, t1, k);
//        System.out.println("Arrays.toString(dist1) = " + Arrays.toString(dist1));
        int[] dist2 = countDistNode(m, t2, k - 1);
//        System.out.println("Arrays.toString(dist2) = " + Arrays.toString(dist2));
        int maxDist = dist2[0];
        for (int i = 1; i < dist2.length; i++) maxDist = Math.max(dist2[i], maxDist);
        for (int i = 0; i < dist1.length; i++) dist1[i] += maxDist;

        return dist1;
    }

    public static void test() {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        List<List<Integer>> tree = buildGraph(8, edges);
        System.out.println(Arrays.toString(countDistNode(8, tree, 2)));

    }

    public static void main(String[] args) {
//        test();

        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        int k = 2;
        int n = 5; // 第一棵树的节点数
        int m = 8; // 第二棵树的节点数
        // 运行函数
        int[] result = maxTargetNodes(edges1, edges2, k);
        System.out.println(Arrays.toString(result));


    }


}
