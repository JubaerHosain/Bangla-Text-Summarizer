package bangla.tokenizer;

import my.library.List;
import java.util.TreeSet;

public class Tokenizer {
    private char DARI1 = '।';
    private char DARI2 = '৷';

    private TreeSet<Character> escapChars;

    public Tokenizer() {
        escapChars = new TreeSet<>();
        // add escap chars
        escapChars.add(' ');
        escapChars.add(',');
        escapChars.add(',');
        escapChars.add('\'');
        escapChars.add('"');
        escapChars.add('“');
        escapChars.add('”');
        escapChars.add('`');
        escapChars.add('?');
        escapChars.add('!');
        escapChars.add('.');
        escapChars.add(DARI1);
        escapChars.add(DARI2);
    }

    public List<String> getSentences(String text) {
        List<String> sentences = new List<>();
        // split TEXT by dari
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == DARI1 || text.charAt(i) == DARI2) {
                String sentence = sb.toString().trim();
                if(sentence.length() > 0)
                    sentences.add(sentence);
                sb = new StringBuffer();
            } else {
                sb.append(text.charAt(i));
            }
        }
        return sentences;
    }

    /** split sentences by space, comma, double quote,
     * single quote question mark, exclamation sign, dot
     */
    public List<String> getWords(String sentence) {
        List<String> words = new List<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < sentence.length(); i++) {
            if(escapChars.contains(sentence.charAt(i))) {
                String word = sb.toString();
                if(word.length() > 0)
                    words.add(word);
                sb = new StringBuffer();
            } else {
                sb.append((char)sentence.charAt(i));
            }
        }
        return words;
    }

    public static void main(String[] args) {
        Tokenizer t = new Tokenizer();
        System.out.println(t.DARI2 == '।');
    }
}
