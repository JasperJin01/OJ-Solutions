package study.dmsxl.backtrace;

import java.util.ArrayList;
import java.util.List;

public class P131_PalindromePartitioning {
    // https://leetcode.cn/problems/palindrome-partitioning/

    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    boolean isPalindrome(String ss, int frontIndex, int endIndex) {
        while (frontIndex < endIndex) {
            if (ss.charAt(frontIndex) != ss.charAt(endIndex)) return false;
            frontIndex++;
            endIndex--;
        }
        return true;
    }

    void process(String ss, int startIndex) {
        if (startIndex == ss.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < ss.length(); i++) {
            if (isPalindrome(ss, startIndex, i)) {
                path.add(ss.substring(startIndex, i+1));
                process(ss, i+1);
                path.remove(path.size()-1);
            }
        }
    }


    public List<List<String>> partition(String s) {
        process(s, 0);
        return result;
    }

    public static void main(String[] args) {
        P131_PalindromePartitioning p = new P131_PalindromePartitioning();
        System.out.println(p.partition("aaabaacbcab"));
//        String substring = "abcde".substring(1, 3); // [1,3)
//        System.out.println(substring);
//        System.out.println(p.isPalindrome("ab", 0,1));
    }

}
