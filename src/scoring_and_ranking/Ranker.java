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
    // co-factor of sentence total frequency
    private final double ALPHA = 0.22;
    // co-factor of positional value
    private final double BETA = 0.11;
    // cue word weight for each sentence
    private final double GAMA = 0.65;
    // weight of skeleton of document
    private final double LAMBDA = 0.25;

    // tokenized but not stemmed
    CList<CList<String>> tokenizedText;
    // all preprocessing done
    CList<CList<String>>  preProcessedText;

    private Trie cueWords;
    private Trie frequencyTrie;

    private CList<Score> scores;
    private int noOfSentences;

    public Ranker(CList<CList<String>> tokenizedText, CList<CList<String>> preProcessedText) throws IOException {
        this.noOfSentences = preProcessedText.size();
        this.tokenizedText = tokenizedText;
        this.preProcessedText = preProcessedText;

        this.frequencyTrie = new Trie();

        // initialize scores list with Score Object (given actual position of sentence)
        this.scores = new CArrayList<>(this.noOfSentences);
        for(int i = 0; i < this.noOfSentences; i++) {
            this.scores.add(new Score(i));
        }

        this.cueWords = new Trie();
        this.readCueWords("1_cue_words.txt");
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

    /** calculate sentence frequency using word frequency */
    private void calculateFrequency() {
        // add pre-processed text to frequency trie
        for(int i = 0; i < preProcessedText.size(); i++) {
            CList<String> sentence = preProcessedText.get(i);
            for(int j = 0; j < sentence.size(); j++)
                frequencyTrie.add(sentence.get(j));
        }

        // add sentence frequency to each sentence score
        for(int i = 0; i < noOfSentences; i++) {
            CList<String> sentence = preProcessedText.get(i);
            int frequencySum = 0;
            for(int j = 0; j < sentence.size(); j++) {
                frequencySum += frequencyTrie.getCount(sentence.get(j));
            }
            scores.get(i).setSentenceFrequency(frequencySum);
        }
    }

    /** calculate positional value for each sentences */
    private void calculatePositionalValue() {
        for(int i = 0; i < this.noOfSentences; i++) {
            scores.get(i).setPositionalValue(1/(i+1));
        }
    }

    /** this method takes input tokenized text(not stemmed)*/
    private void calculateCueWordWeight(CList<CList<String>> tokenizedText) {
        // add cue word weight to each sentence
        // if it contains at least one cue word
        for(int i = 0; i < this.noOfSentences; i++) {
            CList<String> sentence = tokenizedText.get(i);
            for(int j = 0; j < sentence.size(); j++) {
                if(this.cueWords.contains(sentence.get(j))) {
                    this.scores.get(i).setCueWordsWeight(this.GAMA);
                    break;
                }
            }
        }
    }

    private void calculateSkeletonWeight() {

    }

    /** finally calculate actual total score for each sentence */
    private void calculateTotalScore() {
        for(int i = 0; i < this.noOfSentences; i++) {
            Score score = scores.get(i);
            double totalScore = (this.ALPHA * score.getSentenceFrequency());
            totalScore += (this.BETA * score.getPositionalValue());
            totalScore += score.getCueWordsWeight() + score.getSkeletonWeight();
            score.setTotalScore(totalScore);
        }
    }


    public void rank() {
        calculateFrequency();
        calculatePositionalValue();
        calculateTotalScore();
    }

    public static void main(String[] args) {

    }
}
















