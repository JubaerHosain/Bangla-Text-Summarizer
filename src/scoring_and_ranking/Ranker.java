package scoring_and_ranking;

import my.library.CArrayList;
import my.library.CList;
import my.library.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Ranker {
    private String inputText;
    CList<CList<String>>  preProcessedSentences;

    private Trie cueWords;
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
        frequencyTrie = new Trie();
        scores = new CArrayList<>(noOfSentences);
    }

    /** stores cue words into a trie */
    private void readCueWords(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = line.trim();
            if(line.length() < 1) continue;

            //System.out.println(line);
            cueWords.add(line);
        }
        bufferedReader.close();
    }

    /** initialize scores list with Score Object */
    private void initializeScores() {
        for(int i = 0; i < noOfSentences; i++) {
           scores.add(new Score(i));
        }
    }

    /** calculate sentence frequency using word frequency */
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
            for(int j = 0; j < sentence.size(); j++) {
                frequencySum += frequencyTrie.getCount(sentence.get(j));
            }
            Score score = scores.get(i);
            score.setSentenceFrequency(frequencySum);
        }
    }

    private void calculatePositionalValue() {

    }

    private void calculateCueWordWeight() {

    }

    private void calculateTotalScore() {

    }




    public void rank() {
        initializeScores();
        calculateFrequency();



    }

    public static void main(String[] args) {
        Ranker ranker = new Ranker();
        ranker.rank();
    }
}
















