import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main{
    public static void main(String [] args) {

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

class Solutions{
    public int romanToInt(String s) {
        int total = 0;
        int i = 0;
        while (i < s.length()) {
            int current = getValue(s.charAt(i));
            if (i+1 < s.length()) {
                int next = getValue(s.charAt(i+1));
                if (current<next) {
                    total += (next- current);
                    i = i+2;
                }else{
                    total = total + current;
                    i= i+1;
                }
            }else{
                total+=current;
                i+=1;
            }
        }return total;
    }
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M' : return 1000;
            default: return 0;
        }
    }
}
