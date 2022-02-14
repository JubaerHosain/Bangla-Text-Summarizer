package bangla.sw_remover;


import my.library.Trie;

/** Bengali Stop Word Remover*/
public class SWRemover {

    private Trie stopWords;

    public SWRemover() {
        stopWords = new Trie();
        //readStopWords();
    }
}
