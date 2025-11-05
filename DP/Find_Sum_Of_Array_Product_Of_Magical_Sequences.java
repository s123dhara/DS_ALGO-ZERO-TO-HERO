public class Find_Sum_Of_Array_Product_Of_Magical_Sequences {

    public int result = 0;
    public int MOD = 1_000_000_007;

    public void solve(int[] nums, int m, int k, int currIndex, int currSize, int bitmask_sum, int currProd) {
        if (currSize == m) {
            if (Integer.bitCount(bitmask_sum) == k) {
                result = (int) ((result + currProd) % MOD);
            }
            return; // stop exploring once m elements chosen
        }

        if (currIndex >= nums.length)
            return;

        int nextBitMaskSum = bitmask_sum + (int) Math.pow(2, currIndex);
        int nextProduct = (int) ((1L * currProd * nums[currIndex]) % MOD);
        // Take
        solve(nums, m, k, currIndex + 1, currSize + 1, nextBitMaskSum, nextProduct);

        // Not take
        solve(nums, m, k, currIndex + 1, currSize, bitmask_sum, currProd);
    }

    public int magicalSum(int m, int k, int[] nums) {
        solve(nums, m, k, 0, 0, 0, 1);
        return (int) (result % MOD);  
    }

    public static void main(String[] args) {
        int m = 2;
        int k = 2;
        int[] nums = { 5, 4, 3, 2, 1 };

        System.out.println("OUTPUT : " + new Find_Sum_Of_Array_Product_Of_Magical_Sequences().magicalSum(m, k, nums));
        // System.out.println("Set Bit count = " + Integer.bitCount(3));
    }
}
