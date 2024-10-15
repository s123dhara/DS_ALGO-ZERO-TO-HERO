class Solution {
    public int numberOfChild(int n, int k) {
        if(k>=(2*(n-1))){
            k = k%(2*(n-1));
        }
        if(k>=(n-1)){
            k = k%(n-1);
            return (n-k-1);
        }
        else{
            return k;
        }
    }
}