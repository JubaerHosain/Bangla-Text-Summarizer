package bangla.stemmer;

import java.net.URL;
import java.util.TreeMap;
import java.util.TreeSet;

public class Stemmer {
    private TreeSet<String> notStem;
    private TreeSet<String> bochonSuffixes;
    private TreeSet<String> bivoktiSuffixes;
    private TreeSet<String> extraSuffixes;
    private TreeMap<String, String> replaceSuffixes;
    private TreeSet<String> otherSuffixes;


    public Stemmer() {
        notStem = new TreeSet<>();
        bochonSuffixes = new TreeSet<>();
        bivoktiSuffixes = new TreeSet<>();
        extraSuffixes = new TreeSet<>();
        replaceSuffixes = new TreeMap<>();
        otherSuffixes = new TreeSet<>();
        readNotStem();
        readBochonSuffixes();
        readBivoktiSuffixes();
        readExtraSuffixes();
        readReplaceSuffixes();
        readOtherSuffixes();
    }

    private void readOtherSuffixes() {
        URL path = Stemmer.class.getResource("myFile.txt");
        System.out.println(path);
//        File f = new File(path.getFile());
//        reader = new BufferedReader(new FileReader(f));
    }

    private void readReplaceSuffixes() {
    }

    private void readExtraSuffixes() {
    }

    private void readBivoktiSuffixes() {
    }

    private void readBochonSuffixes() {
    }

    private void readNotStem() {
    }

    private boolean suffixMatcher(String suffix, String word) {
        int m = suffix.length()-1;
        int n = word.length()-1;

        if(m > n) {
            return false;
        }

        while(m >= 0 && n >= 0) {
            if(suffix.charAt(m) == word.charAt(n) || suffix.charAt(m) == '.') {
                m--;
                n--;
            } else {
                return false;
            }
        }

        return true;
    }

    public String findStem(String word) {
        word = word.trim();

        for(int i = word.length()-1; i >= 0; i++) {
            String suffix = word.substring(i);
            if(notStem.contains(suffix)) {
                return word;
            } else if(bochonSuffixes.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            }
        }

        return word;
    }
}
