package bangla.sw_remover;


import my.library.MyList;
import my.library.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/** Bengali Stop Word Remover*/
public class SWRemover {

    private Trie stopWords;

    public SWRemover() throws IOException {
        stopWords = new Trie();
        readStopWords("bangla_stopwords.txt");
        readStopWords("bangla_stopwords1.txt");
        readStopWords("bangla_stopwords2.txt");
    }

    private void readStopWords(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        while (bufferedReader.ready()) {
            String stopWord = bufferedReader.readLine();
            stopWord.trim();
            stopWords.add(stopWord);
        }
        bufferedReader.close();
    }

    // remove stop words from sentences
    public MyList<String> remove(MyList<String> sentence) {
        MyList<String> newSentence = new MyList<>();
        for(int i = 0; i < sentence.size(); i++) {
            if(!stopWords.contains(sentence.get(i)))
                newSentence.add(sentence.get(i));
        }
        return newSentence;
    }
}
