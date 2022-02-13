package bangla.stemmer;

import my.library.Trie;

import java.io.*;
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


    public Stemmer() throws IOException {
        notStem = new TreeSet<>();
        bochonSuffixes = new TreeSet<>();
        bivoktiSuffixes = new TreeSet<>();
        extraSuffixes = new TreeSet<>();
        replaceSuffixes = new TreeMap<>();
        otherSuffixes = new TreeSet<>();

        // read not stem
        //readAndStore(notStem, "not_stemming.txt");

        // read bochon suffixes
        //readAndStore(bochonSuffixes, "bochon_suffixes.txt");

        // read bivokti suffixes
        //readAndStore(bivoktiSuffixes, "bivokti_suffixes.txt");

        // read extra suffixes
        //readAndStore(extraSuffixes, "extra_suffixes.txt");

        // read replaceable suffixes
        // ............................

        // read other suffixes
        //readAndStore(otherSuffixes, "other_suffixes.txt");
    }

    private void readAndStore(TreeSet<String> set, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            System.out.println(line);
            set.add(line);
        }
        bufferedReader.close();
    }

    private void readAndStore(TreeMap<String, String> set, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            System.out.println(line);
            set.put(line, line);
        }
        bufferedReader.close();
    }


    private boolean suffixMatcher(String suffix, String word) {
        int m = suffix.length()-1;
        int n = word.length()-1;

        if(m > n) {
            return false;
        }

        while(m >= 0 && n >= 0) {
            if(suffix.charAt(m) == word.charAt(n) || suffix.charAt(m) == '.') {
                m--; n--;
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

    public static void main(String[] args) throws IOException {
        Stemmer stemmer = new Stemmer();
        // create trie instead of TreeSet

    }
}
