package pre_processing;


import my.library.MyList;
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
    Set<String> set;

    public SWRemover() throws IOException {
        stopWords = new Trie();
        set = new TreeSet<>();
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
            set.add(stopWord);
            stopWords.add(stopWord);
        }
        bufferedReader.close();
    }

    // remove stop words from sentences
    private MyList<String> removeWords(MyList<String> tokens) {
        MyList<String> newTokens = new MyList<>();
        for(int i = 0; i < tokens.size(); i++) {
            if(!stopWords.contains(tokens.get(i)))
                newTokens.add(tokens.get(i));
        }
        return newTokens;
    }


    public MyList<MyList<String>> remove(MyList<MyList<String>> tokenizedText) {
        for(int i = 0; i < tokenizedText.size(); i++) {
            MyList<String> newTokens = removeWords(tokenizedText.get(i));
            tokenizedText.replaceAt(i, newTokens);
        }
        return tokenizedText;
    }

    public static void main(String args[]) throws IOException {
        SWRemover swRemover = new SWRemover();
        for(String str: swRemover.set)
            System.out.println(str);
    }
}
