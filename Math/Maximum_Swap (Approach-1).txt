import java.util.Arrays;
/**
 * Maximum_Swap
 */
public class Maximum_Swap {

    /*
     * Approach-1 : T.C : O(N) and S.C : O(N) 
     */

    public static void reverse(char[] str, int n) {
        int i = 0, j = n - 1;
        while(i < j) {
            char c = str[i];
            str[i++] = str[j];
            str[j--] = c;
        }
    }

    public static int maximumSwap(int num) {
        String str_num = Integer.toString(num);
        int n = str_num.length();

        char num_array[] = str_num.toCharArray(); // convert it to character Array 
        char[] str_num_copied_array = Arrays.copyOf(num_array, n); // took a copy of Original array

        Arrays.sort(num_array); // sort this array 
        
        reverse(num_array, n);
        String str_sorted_num = new String(num_array); // make it String after sorting the array 


        if(str_num.equals(str_sorted_num)) {
            return Integer.parseInt(str_sorted_num); // if Equals then return it , i.e no swap required 
        }


        //check which which Maximum elemnet is not equal with its position
        int maxi_index = -1;
        for(int i = 0; i < n; i++) {
            if(num_array[i] != str_num_copied_array[i]) {
                maxi_index = i; 
                break;
                // now then find where is str_num_copied_array[i] in num_array;
            }
        }

        int t = num_array[maxi_index];

        int t_index = -1;

        for(int i = maxi_index + 1; i < n; i++) {
            if(t == str_num_copied_array[i]){
                t_index = i;
            }
        }


        // swap
        char c = str_num_copied_array[maxi_index];
        str_num_copied_array[maxi_index] = str_num_copied_array[t_index];
        str_num_copied_array[t_index] = c;


        return Integer.parseInt(new String(str_num_copied_array));
    }

    public static void main(String[] args) {
        int num = 2736;

        System.out.println("OUTPUT : "+maximumSwap(num));
    }
}