import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String [] args) {
        System.out.println(Solutions.generateParenthesis(4));
    }

    public static ArrayList<String> fizzbuzz(int nums[]) {
        ArrayList<String> varguFinal = new ArrayList<>();
        int n = nums.length;

        for (int i =0; i<n; i++) {
            if (nums[i] %5 ==0 && nums[i] %3==0) {
                varguFinal.add("FizzBuzz");
            }
            else if (nums[i] %3 ==0) {
                varguFinal.add("Fizz");
            }
            else if (nums[i] %5==0) {
                varguFinal.add("Buzz");
            }else {
                varguFinal.add(Integer.toString(nums[i]));
            }
        }

        return varguFinal;
    }
}

class Solutions {
    public int romanToInt(String s) {
        int total = 0;
        int i = 0;
        while (i < s.length()) {
            int current = getValue(s.charAt(i));
            if (i + 1 < s.length()) {
                int next = getValue(s.charAt(i + 1));
                if (current < next) {
                    total += (next - current);
                    i = i + 2;
                } else {
                    total = total + current;
                    i = i + 1;
                }
            } else {
                total += current;
                i += 1;
            }
        }
        return total;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }
    public List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String [] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");

        for (int i =0; i< digits.length();i++) {
            char digit = digits.charAt(i);
            String letters = mapping[digit -'0'];
            List<String> temp = new ArrayList<>();

            for (String c : result) {
                for (char letter : letters.toCharArray()) {
                    temp.add(c+letter);
                }
            }
            result = temp;
        }
        return result;
    }
    public static ListNode mergeTwoLinkedLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val<list2.val) {
            list1.next = mergeTwoLinkedLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLinkedLists(list1, list2.next);
            return list2;
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0,0,n);
        return result;
    }
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max *2) {
            result.add(current);
            return;
        }
        if (open < max) {
            backtrack(result, current + "(", open +1, close, max);
        }
        if (max> open) {
            backtrack(result, current + ")",open,  close+ 1, max);
        }
    }

    /**
     *
     *Given two sorted arrays nums1 and nums2 of size m and n respectively,
     * return the median of the two sorted arrays.
     *The overall run time complexity should be O(log (m+n)).
     *
     */
    public static double findMedianSortedArray(int[] nums1, int[] nums2) {
        // Per faktin se kompleksiteti kohor duhet me kan O(LOG(M+N)) i bjen qe smunemi me i bo merge
        // Nashta me kerkim binar

        if(nums1.length > nums2.length) {
            return findMedianSortedArray(nums2,nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;

        while (low <= high) {
            int partitionX = (low+high) /2;
            int partitionY = (m+n+1) /2 - partitionX;

            int maxLeftX = (partitionX ==0) ? Integer.MIN_VALUE : nums1[partitionX-1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums2[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY-1];
            int minRightY = (partitionY == m) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m+n)%2==0) {
                    return (Math.max(maxLeftX,maxLeftY) + Math.min(minRightX,minRightY))/ 2;
                }else{
                 return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftY> minRightY) {
                high = partitionX-1;
            }else{
                low = partitionY+1;
            }
        }
        throw new IllegalArgumentException();
    }

}
class ListNode{
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

}