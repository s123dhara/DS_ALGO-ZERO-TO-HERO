class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private HashMap<Integer, Integer> map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        map = new HashMap<>();

        for(int num : nums2) {
            map.merge(num, 1, Integer::sum);
        }
    }
    
    public void add(int index, int val) {
        int value = map.get(nums2[index]);
        map.put(nums2[index], value - 1);
        if(value == 1) {
            map.remove(nums2[index]);
        }
        nums2[index] += val;
        // map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
        map.merge(nums2[index], 1, Integer::sum);
    }
    
    public int count(int tot) {
        int count = 0;
        for(int num : nums1) {
            int comp = tot - num;
            if(map.containsKey(comp)) {
                count += map.get(comp);
            }
        }
        
        return count;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */