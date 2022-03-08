package scoring_and_ranking;

import my.library.CArrayList;
import my.library.CList;
import my.library.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**This class implements all necessary method 
 * required for cue word processing*/
public class CueWord {
    private Trie cueWords;

    
    public CueWord() throws IOException {
        cueWords = new Trie();

        readCueWords(cueWords, "1_cue_words.txt");
    }

    private void readCueWords(Trie trie, String fileName) throws IOException {
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

    public boolean contains(String string) {
        return cueWords.contains(string);
    }

    private boolean hasCueWord(String sentence) {
        return true;
    }

    public CList<Boolean> calculateWeight(String inputText, CList<CList<String>> sentences) {
        CList<Boolean> cueValue = new CArrayList<>();


        // found in trie
        // by kmp

        return cueValue;
    }

    public static void main(String[] args) throws IOException {
        CueWord cueWord = new CueWord();
        cueWord.contains("");

    }
}
