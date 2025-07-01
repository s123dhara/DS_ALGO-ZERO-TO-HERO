class Solution {
    public String[] findWords(String[] words) {             
        int[] map = new int[26];
        for(char ch : "qwertyuiop".toCharArray()) {
            map[ch - 'a'] = 1;
        }
        for(char ch : "asdfghjkl".toCharArray()) {
            map[ch - 'a'] = 2;
        }
        for(char ch : "zxcvbnm".toCharArray()) {
            map[ch - 'a'] = 3;
        }

        List<String> temp = new ArrayList<>();
        for(String word : words) {
            if(word.length() == 1) {
                temp.add(word);
                continue;
            }

            int currLevel = map[Character.toLowerCase(word.charAt(0)) - 'a'];
            boolean flag = true;
            for(int i = 1; i < word.length(); i++) {
                int level = map[Character.toLowerCase(word.charAt(i)) - 'a'];
                if(currLevel != level) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                temp.add(word);
            }
        }   



        return temp.toArray(new String[0]);
     }
}