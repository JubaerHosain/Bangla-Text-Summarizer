package bangla.tokenizer;

import my.library.MyList;
import java.util.TreeSet;

public class Tokenizer {
    private char DARI1 = '।';
    private char DARI2 = '৷';

    private MyList<Character> escapChars;

    public Tokenizer() {
        escapChars = new MyList<>();
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

    public MyList<String> getSentences(String text) {
        MyList<String> sentences = new MyList<>();
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
    public MyList<String> getWords(String sentence) {
        MyList<String> words = new MyList<>();
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
