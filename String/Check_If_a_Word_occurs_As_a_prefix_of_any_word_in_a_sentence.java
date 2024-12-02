public class Check_If_a_Word_occurs_As_a_prefix_of_any_word_in_a_sentence {

    public static int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        int index = 0;
        for(String wd : words) {
            index += 1;
            if(wd.startsWith(searchWord)) return index;
        }

        return -1;
    }


    public static void main(String[] args) {
        String sentence = "i love eating burger";
        String searchWord = "burg";

        System.out.println("OUTPUT : "+isPrefixOfWord(sentence, searchWord));
    }
}