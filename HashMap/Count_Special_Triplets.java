import java.util.*;

public class Count_Special_Triplets {

    public int specialTriplets(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> prevIndexMap[] = new HashMap[n];
        HashMap<Integer, Integer> nextIndexMap[] = new HashMap[n];
        for (int i = 0; i < n; i++) {
            prevIndexMap[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            nextIndexMap[i] = new HashMap<>();
        }

        for (int i = 1; i < n; i++) {
            prevIndexMap[i].putAll(prevIndexMap[i - 1]);
            prevIndexMap[i].put(nums[i - 1], prevIndexMap[i].getOrDefault(nums[i - 1], 0) + 1);
        }

        for (int i = n - 2; i >= 0; i--) {
            nextIndexMap[i].putAll(nextIndexMap[i + 1]);
            nextIndexMap[i].put(nums[i + 1], nextIndexMap[i].getOrDefault(nums[i + 1], 0) + 1);
        }

        int count = 0;
        for (int j = 1; j < n - 1; j++) {
            // nums[j] is the middle element
            // it's twice exist in prev and next
            if (prevIndexMap[j].containsKey(nums[j] * 2) && nextIndexMap[j].containsKey(nums[j] * 2)) {
                count += prevIndexMap[j].get(nums[j] * 2) * nextIndexMap[j].get(nums[j] * 2);
            }
        }

        for (int i = 0; i < n; i++) {
            if (prevIndexMap[i] == null) {
                System.out.println("Index : " + i + " Prev Map : null");
            } else {
                System.out.println("Index : " + i + " Prev Map : " + prevIndexMap[i]);
            }

            if (nextIndexMap[i] == null) {
                System.out.println("Index : " + i + " Next Map : null");
            } else {
                System.out.println("Index : " + i + " Next Map : " + nextIndexMap[i]);
            }
            // System.out.println("Index : " + i + " Prev Map : " + prevIndexMap[i] + " Next
            // Map : " + nextIndexMap[i]);
        }
        return count;
    }

    public int specialTriplets_v2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> currFreqMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int twice = nums[i] * 2;
            freqMap.put(nums[i], freqMap.get(nums[i]) - 1); // right map update

            if (freqMap.get(nums[i]) == 0) {
                freqMap.remove(nums[i]);
            }
            if (currFreqMap.containsKey(twice) && freqMap.containsKey(twice)) {
                int rightCount = freqMap.get(twice);
                int leftCount = currFreqMap.get(twice);
                count += leftCount * rightCount;
            }
            currFreqMap.put(nums[i], currFreqMap.getOrDefault(nums[i], 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        // int[] nums = {6,3,6};
        int[] nums = { 8, 4, 2, 8, 4 };
        System.out.println("OUTPUT : " + new Count_Special_Triplets().specialTriplets_v2(nums));
    }
}
