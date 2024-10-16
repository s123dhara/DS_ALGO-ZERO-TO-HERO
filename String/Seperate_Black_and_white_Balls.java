/**
 * Seperate_Black_and_white_Balls
 */
public class Seperate_Black_and_white_Balls {

    public static long minimumSteps(String s) {

        int n = s.length();
        long ans = 0;
        long ones_count = 0;

        for(int i = 0; i < n; i++) {

            char c = s.charAt(i);
            if(c == '1') ones_count += 1;

            if(c == '0') {
                ans += ones_count;
            }

        }
        return ans;


    }


    public static void main(String[] args) {
        // String s = "100";
        String s = "0111";
        System.out.println("OUPUT : "+minimumSteps(s));
    }
}
