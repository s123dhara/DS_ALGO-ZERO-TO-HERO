class Solution {
    public int maximumGain(String s, int x, int y) {
        String first;
        String second;

        int high;
        int low;

        if(x > y) {
            first = "ab";
            second = "ba";
            high = x;
            low = y;
        } else {
            first = "ba";
            second = "ab";
            high = y;
            low = x;
        }

        int result = 0;
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(!st.isEmpty() && st.peek() == first.charAt(0) && ch == first.charAt(1)) 
            {
                st.pop();
                result += high;
            }
            else 
            {
                st.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }

        sb.reverse();

        for(int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if(!st.isEmpty() && st.peek() == second.charAt(0) && ch == second.charAt(1)) 
            {
                st.pop();
                result += low;
            }
            else 
            {
                st.push(ch);
            }
        }

        return result;
        
    }
}