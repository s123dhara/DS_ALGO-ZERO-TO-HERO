import java.util.*;
class Pair 
{
    public char key;
    public int value;

    Pair(char key_1, int value_1) {
        this.key = key_1;
        this.value = value_1;
    }

    @Override
    public String toString() {
        return "Pair [key=" + key + ", value=" + value + "]";
    }   

    
}

public class Sort_Characters_By_Frequency {

    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }        

        PriorityQueue<Pair> pq = new PriorityQueue<>( (a,b) -> b.value - a.value );
        for(char key : map.keySet()) {
            int value = map.get(key);
            Pair ob = new Pair(key, value);
            pq.offer(ob);
        }

        while(!pq.isEmpty()) {
            Pair currPair = pq.poll();
            char key = currPair.key;
            int value = currPair.value;

            while(value-- > 0) {
                sb.append(key);
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        System.out.println("OUTPUT : " + frequencySort(s));
    }
}