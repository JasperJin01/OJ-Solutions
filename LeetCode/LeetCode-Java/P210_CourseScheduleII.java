import java.util.*;

public class P210_CourseScheduleII {

    // https://leetcode.cn/problems/course-schedule-ii/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int retp = 0;
        int totStudy = 0; // 用来统计总共学过的课程
        // 统计入度
        int[] inDegree = new int[numCourses];
        for (int[] edge: prerequisites) {
            inDegree[edge[0]]++;
        }
        // 创建图
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) g.add(new ArrayList<>());
        for (int[] edge: prerequisites) {
            // 由edge[1]指向edge[0]
            g.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>(); // 保存index
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[retp++] = i;
            }
        // 无法完成课程
        if (queue.isEmpty()) return new int[]{};

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            totStudy++;
            for (int to: g.get(cur)) {
                inDegree[to]--;
                if (inDegree[to] == 0) {
                    queue.offer(to);
                    result[retp++] = to;
                }
            }
        }

        if (totStudy < numCourses) return new int[]{};

        return result;
    }

    public static void main(String[] args) {
        P210_CourseScheduleII p = new P210_CourseScheduleII();
//        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
//        int numCourses = 4;
        int[][] prerequisites = {{1,0},{1,2},{0,1}};
        int numCourses = 3;
        int[] ret = p.findOrder(numCourses, prerequisites);
        System.out.println("ret = " + Arrays.toString(ret));
    }

}
