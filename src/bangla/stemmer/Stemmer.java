package bangla.stemmer;

import java.util.TreeMap;
import java.util.TreeSet;

public class Stemmer {
    private TreeSet<String> notSteamed;
    private TreeSet<String> bochonSuffix;
    private TreeSet<String> bivoktiSuffix;

    public Stemmer() {
        
    }

    public String findStem(String word) {
        word = word.trim();

        for(int i = word.length()-1; i >= 0; i++) {
            String suffix = word.substring(i);
            if(notSteamed.contains(suffix)) {
                return word;
            } else if(bochonSuffix.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            }
        }

        return word;
    }
}
