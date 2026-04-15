public class Shortest_Distance_To_Target_String_In_A_Circular_Array {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;

        for (int step = 0; step < n; step++) {
            int forward = (startIndex + step) % n;
            int backward = (startIndex - step + n) % n;

            if (words[forward].equals(target) || words[backward].equals(target)) {
                return step;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        String[]  words = {"hello","i","am","leetcode","hello"};
        String target = "hello";
        int startIndex = 1;

        System.out.println("OUTPUT : "+new Shortest_Distance_To_Target_String_In_A_Circular_Array().closestTarget(words, target, startIndex));
    }
}
