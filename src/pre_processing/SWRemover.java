package pre_processing;

import my.library.CArrayList;
import my.library.CList;
import my.library.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

/** Bengali Stop Word Remover*/
public class SWRemover {
    private Trie stopWords;

    public SWRemover() throws IOException {
        stopWords = new Trie();
        readStopWords("1_bangla_stopwords.txt");
    }

    private void readStopWords(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        while (bufferedReader.ready()) {
            String stopWord = bufferedReader.readLine();
            stopWord = stopWord.trim();
            stopWords.add(stopWord);
        }
        bufferedReader.close();
    }

    /** removes stop words from a tokenized sentence*/
    private CList<String> eraseStopWords(CList<String> tokens) {
        CList<String> newTokens = new CArrayList<>();
        for(int i = 0; i < tokens.size(); i++) {
            if(!stopWords.contains(tokens.get(i)))
                newTokens.add(tokens.get(i));
        }
        return newTokens;
    }

    /** removes stop words from tokenized sentences*/
    public CList<CList<String>> removeStopWords(CList<CList<String>> tokenizedText) {
        int numberOfSentences = tokenizedText.size();
        CList<CList<String>> swRemovedText = new CArrayList<>(numberOfSentences);
        for(int i = 0; i < numberOfSentences; i++) {
            CList<String> newTokens = eraseStopWords(tokenizedText.get(i));
            swRemovedText.add(newTokens);
        }
        return swRemovedText;
    }
}
