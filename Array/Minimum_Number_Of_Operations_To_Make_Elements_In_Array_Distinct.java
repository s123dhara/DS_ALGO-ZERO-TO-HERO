import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Minimum_Number_Of_Operations_To_Make_Elements_In_Array_Distinct {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        if (map.size() == n) {
            return 0;
        }

        int totalElements = map.size();
        int totalRemovedElements = 0;

        int result = 0;
        int i = 0;
        for (i = 0; i <= n - 3; i += 3) {
            System.out.println("CURRENT i Index = " + i);
            System.out.println("BEFORE OPERATION MAP = " + map.toString());
            System.out.println("Array = " + Arrays.toString(nums));
            for (int j = 0; j < 3; j++) {
                map.put(nums[j + i], map.get(nums[j + i]) - 1);
                if (map.get(nums[j + i]) == 0) {
                    totalRemovedElements++;
                    map.remove(nums[j + i]);
                }
            }
            result++;
            System.out.println("AFTER OPERATION MAP = " + map.toString());
            System.out.println("result = " + result);

            System.out.println("LEFT ELEMENTS = " + (n - i - 3));
            System.out.println("TOTAL ELEMENTS = " + totalElements);
            System.out.println("TOTAL REMOVED ELEMENTS = " + totalRemovedElements);
            if (totalElements - totalRemovedElements == n - i - 3) {
                return result;
            }

            // if(map.size() == n - i + 1) {
            // return result;
            // }
            // boolean unique = true;
            // for (int key : map.keySet()) {
            // if (map.get(key) > 1) {
            // unique = false;
            // break;
            // }
            // }

            // if (unique)
            // return result;
        }

        System.out.println(n - i);
        return (n - i < 3) ? ++result : result;
    }

    public static void main(String[] args) {
        // int[] nums = { 1, 2, 3, 4, 2, 3, 3, 5, 7 };
        // int[] nums = { 4, 5, 6, 4, 4 };
        int[] nums = { 6, 7, 8, 9 };

        Minimum_Number_Of_Operations_To_Make_Elements_In_Array_Distinct ob = new Minimum_Number_Of_Operations_To_Make_Elements_In_Array_Distinct();
        int result = ob.minimumOperations(nums);
        System.out.println("OUPTUT : " + result);
    }
}
