import java.lang.reflect.Array;
import java.util.*;

public class Main{
    public static void main(String [] args) {
        System.out.println(Solutions.generateParenthesis(4));

        String res = Solutions.addBinary("1001", "010");
        System.out.println(res);

        String res1 = "funksion";
        System.out.println(Solutions.frequencySort(res1));
        System.out.println("----------------------------------------");
        System.out.println(Solutions.reverseString(res1));
        System.out.println("----------------------------------------");
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Solutions.maxNumInArray(arr);
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


    /**
     * Given an array nums of distinct integers, return all the possible
     * permutations. You can return the answer in any order.
     */

    public static List<List<Integer>> permute (int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack1(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }
    public static void backtrack1(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length;i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(nums[i]);
                backtrack1(result,temp,nums,used);
                used[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }

    /**
     * Given two binary strings a and b, return their sum as a binary string.
     */
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        while (i>=0 || j>= 0 || carry > 0) {
            int aBit = (i>=0) ? a.charAt(i--) - '0' : 0;
            int bBit = (j>= 0 ) ? b.charAt(j--) - '0' : 0;
            int sum = aBit+ bBit + carry;
            sb.append(sum%2);
            carry = sum/2;
        }
        return sb.reverse().toString();
    }

    /**
     Given a string s,
     sort it in decreasing order based on the frequency of the characters.
     The frequency of a character is the number of times it appears in the string.
     Return the sorted string. If there are multiple answers, return any of them.
     */

    public static String frequencySort(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c,0) + 1);
        }
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(characters, (a,b) -> {
            int freqCompare = Integer.compare(frequencyMap.get(b), frequencyMap.get(a));
            if (freqCompare != 0) {
                return freqCompare;
            }else{
                return Character.compare(b,a);
            }
        });

        StringBuilder result = new StringBuilder();
        for (Character c: characters) {
            int count = frequencyMap.get(c);
            for (int i = 0; i < count;i++) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = str.length()-1;
        while (left<right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;

        }
        return new String(charArray);
    }
    public static void maxNumInArray(int[] arr) {
        int min = arr[0];
        int max =arr[0];
        for (int i = 0; i< arr.length; i++) {
            if (arr[i] < max) {
                max = arr[i];
            }
            if (arr[i] > min) {
                min = arr[i];
            }
        }

        System.out.println("Max value in array is: " + max);
        System.out.println("Min value in array is: " + min);
    }


    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static ListNode2 mergeTwoLists(ListNode2 list1, ListNode2 list2) {
        ListNode2 dummy = new ListNode2(-1);
        ListNode2 current = dummy;

        while(list1 != null && list2!=null) {
            if (list1.val<=list2.val){
                current.next = list1;
                list1 = list1.next;
            }else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        }else if(list2!=null) {
            current.next = list2;
        }

        return dummy.next;
    }
    public static void printList(ListNode2 head) {
        ListNode2 current = head;
        while (current != null) {
            System.out.println(current.val + " ");
            current = current.next;
        }
        System.out.println();
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
class ListNode2{
    int val;
    ListNode2 next;
    ListNode2(){}
    ListNode2(int val) {
        this.val = val;
    }
    ListNode2(int val, ListNode2 next) {
        this.val = val;
        this.next = next;
    }
}