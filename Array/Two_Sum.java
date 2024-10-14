import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Two_Sum
 */
public class Two_Sum {

    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int comp = target - nums[i];

            if (map.containsKey(comp)) {

                int idx = map.get(comp);
                return new int[] { idx, i };
            }
            // normal add krte thakbo
            map.put(nums[i], i);
        }

        return null;

    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] ans = twoSum(nums, target);
        System.out.println("OUTPUT : "+Arrays.toString(ans));
    }
}