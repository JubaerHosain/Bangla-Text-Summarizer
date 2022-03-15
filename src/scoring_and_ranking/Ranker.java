package scoring_and_ranking;

import my.library.CArrayList;
import my.library.CList;
import my.library.Trie;

import java.io.IOException;

public class Ranker {
    private String inputText;
    CList<CList<String>>  preProcessedSentences;

    private CueWord cueWord;
    private Trie frequencyTrie;

    private CList<Score> scores;
    private int noOfSentences;

    public Ranker() {
        this.noOfSentences = 20;
        this.scores = new CArrayList<>(noOfSentences);
    }

    public Ranker(String inputText, CList<CList<String>> preProcessedSentences) throws IOException {
        this.noOfSentences = preProcessedSentences.size();
        this.inputText = inputText;
        this.preProcessedSentences = preProcessedSentences;
        cueWord = new CueWord();
        frequencyTrie = new Trie();
        scores = new CArrayList<>(noOfSentences);
    }

    /** initialize with Score Object */
    private void initializeScores() {
        for(int i = 0; i < noOfSentences; i++) {
           scores.add(new Score(i));
        }

        for(int i = 0; i < scores.size(); i++) {
            System.out.println(scores.get(i).getActualPosition());
        }
    }

    private void calculateFrequency() {
        // add pre-processed text to frequency trie
        for(int i = 0; i < preProcessedSentences.size(); i++) {
            CList<String> sentence = preProcessedSentences.get(i);
            for(int j = 0; j < sentence.size(); j++)
                frequencyTrie.add(sentence.get(j));
        }

        // add sentence frequency to each sentence score
        for(int i = 0; i < noOfSentences; i++) {
            CList<String> sentence = preProcessedSentences.get(i);
            int frequencySum = 0;
            for(int j = 0; j < sentence.size(); j++)
                frequencySum += frequencyTrie.getCount(sentence.get(j));
            Score score = scores.get(i);
            score.setSentenceFrequency(frequencySum);
        }
    }


    public void rank() {
        initializeScores();
        calculateFrequency();
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores.get(i).getSentenceFrequency());
        }

    }

    public static void main(String[] args) {
        Ranker ranker = new Ranker();
        ranker.rank();
    }
}
















