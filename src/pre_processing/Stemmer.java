package pre_processing;

import my.library.CArrayList;
import my.library.CList;
import my.library.Pair;
import my.library.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class Stemmer {
    private Trie notStem;
    private Trie bochonSuffixes;
    private Trie bivoktiSuffixes;
    private Trie otherSuffixes;
    private Trie extraSuffixes;

    private CList<Pair<String, String>> replaceSuffixes;
    private CList<Pair<String, String>> replaceWithDot;

    public Stemmer() throws IOException {
        notStem = new Trie();
        bochonSuffixes = new Trie();
        bivoktiSuffixes = new Trie();
        otherSuffixes = new Trie();
        extraSuffixes = new Trie();

        replaceSuffixes = new CArrayList<>();
        replaceWithDot = new CArrayList<>();

        // read not stem
        readAndStoreToTrie(notStem, "2_not_stemming.txt");

        // read bochon suffixes
        readAndStoreToTrie(bochonSuffixes, "3_bochon_suffixes.txt");

        // read bivokti suffixes
        readAndStoreToTrie(bivoktiSuffixes, "4_bivokti_suffixes.txt");

        // read other suffixes
        readAndStoreToTrie(otherSuffixes, "5_other_suffixes.txt");

        // read extra suffixes
        readAndStoreToTrie(extraSuffixes, "6_extra_suffixes.txt");

        // read replaceable suffixes
        readAndStoreToListOfPair(replaceSuffixes, "7_replace_suffixes.txt");

        // replace suffixes with dot
        readAndStoreToListOfPair(replaceWithDot, "8_replace_suffixes_with_dot.txt");
    }

    private void readAndStoreToTrie(Trie trie, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            if(line.length() < 1) continue;
            trie.add(line);
        }
        bufferedReader.close();
    }

    private void readAndStoreToList(CList<String> myList, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            if(line.length() < 1) continue;
            myList.add(line);
        }
        bufferedReader.close();
    }

    private void readAndStoreToListOfPair(CList<Pair<String, String>> myList, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            String[] tokens = line.split("->");
            if(tokens.length <= 1) continue;
            tokens[0] = tokens[0].trim();
            tokens[1] = tokens[1].trim();
            myList.add(new Pair<>(tokens[0], tokens[1]));
        }
        bufferedReader.close();
    }

    private boolean matchSuffix(String suffix, String word) {
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

    public String stemWord(String input) {
        String word = input;

        if(notStem.contains(word)) {
            return word;
        }

        for(int i = word.length()-1; i >= 0; i--) {
            String suffix = word.substring(i);
            if(notStem.contains(suffix)) {
                return word;
            } else if(bochonSuffixes.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            } else if(bivoktiSuffixes.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            } else if(otherSuffixes.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            }
        }

        // replace suffixes
        for(int i = 0; i < replaceSuffixes.size(); i++) {
            String suffix = replaceSuffixes.get(i).getFirst();
            if(matchSuffix(suffix, word)) {
                word = word.substring(0, word.length()-suffix.length());
                word += replaceSuffixes.get(i).getSecond();
                return word;
            }
        }


        // replace for replace with dot
        for(int i = 0; i < replaceWithDot.size(); i++) {
            String suffix = replaceWithDot.get(i).getFirst();
            if(matchSuffix(suffix, word)) {
                StringBuffer sb = new StringBuffer(word);
                int k = word.length()-suffix.length();
                String addSuffix = replaceWithDot.get(i).getSecond();
                for(int j = 0; j < addSuffix.length(); k++, j++) {
                    if(addSuffix.charAt(j) != '.') {
                        sb.setCharAt(k, addSuffix.charAt(j));
                    }
                }
                word = sb.substring(0, k);
                return word;
            }
        }

        // extra suffixes
        for(int i = word.length()-1; i >= 0; i--) {
            String suffix = word.substring(i);
            if(extraSuffixes.contains(suffix)) {
                word = word.substring(0, i);
                return word;
            }
        }

        return word;
    }

    public CList<String> stemList(CList<String> tokens) {
        int numberOfWord = tokens.size();
        CList<String> stemmedTokens = new CArrayList<>(numberOfWord);
        for(int i = 0; i < tokens.size(); i++) {
            stemmedTokens.add(stemWord(tokens.get(i)));
        }
        return stemmedTokens;
    }

    public CList<CList<String>> stemText(CList<CList<String>> tokenizedText) {
        int numberOfSentence = tokenizedText.size();
        CList<CList<String>> stemmedText = new CArrayList<>(numberOfSentence);
        for(int i = 0; i < numberOfSentence; i++) {
            stemmedText.add(stemList(tokenizedText.get(i)));
        }
        return stemmedText;
    }
}
