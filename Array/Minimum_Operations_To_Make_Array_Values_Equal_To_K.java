import java.util.Map;
import java.util.TreeMap;

public class Minimum_Operations_To_Make_Array_Values_Equal_To_K {
    public static int minOperations(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if (map.firstKey() < k)
            return -1;

        // System.out.println("MAP = " + map.toString());
        // System.out.println(map.higherKey(4));
        // System.out.println(map.get(map.lastKey()));

        int result = 0;
        while (map.size() > 1) {

            // if (map.get(k) == nums.length) {
            // if (map.firstEntry().getKey() > k) {
            // return -1;
            // }
            // // System.out.println(map.toString());
            // return result;
            // }

            Map.Entry<Integer, Integer> lastEntry = map.pollLastEntry();
            Map.Entry<Integer, Integer> secondLastEntry = map.lastEntry();            
            int max_count = lastEntry.getValue();
            int secondMax = secondLastEntry.getKey();
            int secondMax_count = secondLastEntry.getValue();

            map.put(secondMax, secondMax_count + max_count);
            result++;
        }

        if (map.lastKey() > k) {
            return ++result;
        } else if(map.firstKey() == k  && map.get(k) == nums.length) {
            return result;
        }
        else {
            return -1;
        }

        // System.out.println(map.toString());
        // return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 5, 2, 5, 4, 5 };
        int k = 2;

        System.out.println("OUTPUT : " + minOperations(nums, k));

    }
}
