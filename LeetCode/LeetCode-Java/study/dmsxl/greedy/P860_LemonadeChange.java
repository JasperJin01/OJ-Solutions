package study.dmsxl.greedy;

// https://leetcode.cn/problems/lemonade-change/
public class P860_LemonadeChange {

    // date: 2024-11-06
    // 这道题思路很简单，贪心的思想就是：找零要先找大面值，再找小面值的
    // 写的有点复杂，可以尝试把判false逻辑统一在for循环最后写
    public static boolean lemonadeChange(int[] bills) {
        int[] count = new int[2]; // 分别表示5元，10元，20元的数量
        for (int bill: bills) {
            if (bill == 5) count[0]++;
            else if (bill == 10) {
                if (count[0] > 0) {
                    count[0]--;
                    count[1]++;
                    continue;
                }
                return false;
            }
            else {
                if (count[1] > 0) { // 找零10+5
                    count[1]--;
                    if (count[0] > 0) {
                        count[0]--;
                        continue;
                    }
                    return false;
                } else if (count[0] - 3 >= 0) {
                    count[0] -= 3;
                    continue;
                }
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] bills = {5,5,10,10,20};
        System.out.println(lemonadeChange(bills));

    }
}