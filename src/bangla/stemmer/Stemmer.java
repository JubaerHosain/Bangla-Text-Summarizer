package bangla.stemmer;

import my.library.MyList;
import my.library.Pair;
import my.library.Trie;
import java.net.URL;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Stemmer {
    private Trie notStem;
    private Trie bochonSuffixes;
    private Trie bivoktiSuffixes;
    private Trie otherSuffixes;

    private MyList<String> extraSuffixes;
    private MyList<Pair<String, String>> replaceSuffixes;
    private MyList<Pair<String, String>> replaceWithDot;


    public Stemmer() throws IOException {
        notStem = new Trie();
        bochonSuffixes = new Trie();
        bivoktiSuffixes = new Trie();
        otherSuffixes = new Trie();

        extraSuffixes = new MyList<>();
        replaceSuffixes = new MyList<>();
        replaceWithDot = new MyList<>();

        // read not stem
        readAndStoreToTrie(notStem, "not_stemming.txt");

        // read bochon suffixes
        readAndStoreToTrie(bochonSuffixes, "bochon_suffixes.txt");

        // read bivokti suffixes
        readAndStoreToTrie(bivoktiSuffixes, "bivokti_suffixes.txt");

        // read other suffixes
        readAndStoreToTrie(otherSuffixes, "other_suffixes.txt");

        // read extra suffixes
        readAndStoreToList(extraSuffixes, "extra_suffixes.txt");

        // read replaceable suffixes
        readAndStoreToListOfPair(replaceSuffixes, "replace_suffixes.txt");

        // replace suffixes with dot
        readAndStoreToListOfPair(replaceWithDot, "replace_suffixes_with_dot.txt");
    }


    private void readAndStoreToTrie(Trie trie, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            if(line.length() < 1) continue;
            //System.out.println(line);
            trie.add(line);
        }
        bufferedReader.close();
    }

    private void readAndStoreToList(MyList<String> myList, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            if(line.length() < 1) continue;
            //System.out.println(line);
            myList.add(line);
        }
        bufferedReader.close();
    }

    private void readAndStoreToListOfPair(MyList<Pair<String, String>> myList, String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            String[] tokens = line.split("->");
            if(tokens.length <= 1) continue;
            tokens[0] = tokens[0].trim();
            tokens[1] = tokens[1].trim();
            //System.out.println(tokens[0] + "->" + tokens[1]);
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


    public String findStem(String word) {
        word = word.trim();

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
        for(int i = 0; i < extraSuffixes.size(); i++) {
            String suffix = extraSuffixes.get(i);
            if(matchSuffix(suffix, word)) {
                word = word.substring(0, word.length()-suffix.length());
                return word;
            }
        }

        return word;
    }

    public MyList<MyList<String>> stem(MyList<MyList<String>> tokenizedText) {
        return tokenizedText;
    }


    public static void main(String[] args) throws IOException {
        Stemmer stemmer = new Stemmer();
        // create trie instead of Trie
        String str = "বদনের কাজে আসে করে গেলে হেসে কামালের";
        String[] tokens = str.split(" ");
        for(int i = 0; i < tokens.length; i++) {
            String word = tokens[i].trim();
            System.out.println(stemmer.findStem(word));
        }
    }
}
