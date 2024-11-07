// https://leetcode.cn/problems/candy/

import java.util.ArrayList;
import java.util.List;

public class P135_Candy {

    
    public static int candy_error(int[] ratings) {
        int N = ratings.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int[] arr = {ratings[i], i};
            list.add(arr);
        }
        list.sort((a,b)->Integer.compare(a[0],b[0]));
        int[] candys = new int[N];
        for (int i = 0; i < N; i++) {
            int index = list.get(i)[1];
            candys[index] = maxlr(index, candys) + 1;
        }
        int res = 0;
        for (int i = 0; i < N; i++) System.out.print(candys[i]);
        System.out.println();
        for (int candy: candys) res += candy;
        return res;
    }

    public static int maxlr(int index, int[] candys) {
        if (index == 0) return candys[1];
        if (index == candys.length - 1) return candys[candys.length - 2];
        return Math.max(candys[index-1], candys[index+1]);
    }

    // 1 2 2 2 2 2 2 2 2 2 2 怎么发糖果

    // 1 2 2 5 4 3 2


    public static void main(String[] args) {
        int[] ratings = {1,2,2};

    }

}