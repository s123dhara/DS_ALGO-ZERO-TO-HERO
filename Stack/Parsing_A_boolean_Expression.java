import java.util.Stack;

public class Parsing_A_boolean_Expression {

    /*Approach -2 With Double Stack */

    public static boolean solve(Stack<Character> st, char operator) {
        boolean currResult = st.peek() == 'f' ? false : true;

        if (operator == '&') {
            while(!st.isEmpty()) {
                currResult = currResult & (st.pop() == 'f' ? false : true);
            }
        } else if (operator == '|') {
            while(!st.isEmpty()) {
                currResult = currResult | (st.pop() == 'f' ? false : true);
            }
        } else {
            // '!' operator
            currResult = st.pop() == 'f'; // negate the first element
        }

        return currResult;
    }

    public static boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();
        int n = expression.length();
        Stack<Character> st2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            char currChar = expression.charAt(i);
            if (currChar == ',') {
                continue;
            }
        
            if (currChar == ')') {
                // Fix the loop condition
                while (st.peek() != '(') {
                    st2.push(st.pop());
                }
                st.pop(); // remove '('

                // Calculate and add result to st
                char operator = st.pop();
                boolean result = solve(st2, operator);
                st.push(result ? 't' : 'f'); // push the result back to st                
            }else {
                st.push(currChar);
            }
        }

        return st.peek() == 't';
    }

    public static void main(String[] args) {
        // String expression = "&(|(f))";
        String expression = "!(&(f,t))";
        System.out.println("OUTPUT : " + parseBoolExpr(expression)); // Should print false
    }
}
