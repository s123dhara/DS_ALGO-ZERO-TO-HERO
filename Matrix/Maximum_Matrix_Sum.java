public class Maximum_Matrix_Sum {

    public static long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;       
        long sum = 0;
       
        int min_neg = Integer.MAX_VALUE;
        int count = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) { 
                if(matrix[i][j] < 0) {
                    count++;
                }                
                min_neg = Math.min(min_neg, Math.abs(matrix[i][j]));
                sum += Math.abs(matrix[i][j]);
                
            }
        }

        if(count % 2 == 0) {
            return sum;
        }
        return sum - 2 * min_neg;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {-1, -2, -3},
            {1, 2, 3}
        };
        System.out.println(maxMatrixSum(matrix));
    }
}
