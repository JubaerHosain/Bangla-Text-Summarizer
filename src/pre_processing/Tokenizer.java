package pre_processing;

import my.library.CArrayList;
import my.library.CList;

import java.util.HashSet;
import java.util.Set;

public class Tokenizer {
    private char DARI1 = '।';
    private char DARI2 = '৷';

    private Set<Character> escapChars;

    public Tokenizer() {
        escapChars = new HashSet<>();
        escapChars.add(' ');
        escapChars.add(',');
        escapChars.add(',');
        escapChars.add('\'');
        escapChars.add('"');
        escapChars.add('“');
        escapChars.add('`');
        escapChars.add('?');
        escapChars.add('!');
        escapChars.add('.');
        escapChars.add('-');
        escapChars.add(DARI1);
        escapChars.add(DARI2);
        escapChars.add('(');
        escapChars.add(')');
    }

    /** split text to sentence by dari*/
    private CList<String> getSentences(String text) {
        CList<String> sentences = new CArrayList<>();
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

        String sentence = sb.toString().trim();
        if(sentence.length() > 0)
            sentences.add(sentence);

        return sentences;
    }

    /** split sentences by space, comma, double quote,
     * single quote question mark, exclamation sign, dot
     */
    public CList<String> getWords(String sentence) {
        CList<String> words = new CArrayList<>();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < sentence.length(); i++) {
            if(escapChars.contains(sentence.charAt(i))) {
                String word = sb.toString().trim();
                if(word.length() > 0)
                    words.add(word);
                sb = new StringBuffer();
            } else {
                sb.append(sentence.charAt(i));
            }
        }

        String word = sb.toString().trim();
        if(word.length() > 0)
            words.add(word);

        return words;
    }

    public CList<CList<String>> tokenize(String inputText) {
        CList<String> sentences = getSentences(inputText);
        int numberOfSentence = sentences.size();
        CList<CList<String>> tokenizedText = new CArrayList<>(numberOfSentence);
        for(int i = 0; i < numberOfSentence; i++) {
            CList<String> words = getWords(sentences.get(i));
            tokenizedText.add(words);
        }
        return tokenizedText;
    }
}
