package scoring_and_ranking;

import my.library.*;

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
    private Trie skeletonWords;
    private Trie frequencyTrie;

    private CList<Score> scores;
    private int noOfSentences;

    public Ranker(CList<String> skeletonWords, CList<CList<String>> tokenizedText, CList<CList<String>> preProcessedText) throws IOException {
        if(tokenizedText.size() != preProcessedText.size()) {
            throw new ArrayIndexOutOfBoundsException("size of tokenizedText and preProcessedText is not equal");
        }


        this.noOfSentences = preProcessedText.size();
        this.tokenizedText = tokenizedText;
        this.preProcessedText = preProcessedText;

        this.frequencyTrie = new Trie();

        // add skeletonWords list to the skeletonWords Trie
        this.skeletonWords = new Trie();
        for(int i = 0; i < skeletonWords.size(); i++) {
            this.skeletonWords.add(skeletonWords.get(i));
        }

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
            scores.get(i).setPositionalValue(1/(double)(i+1));
        }
    }

    /** this method takes input tokenized text(not stemmed)*/
    private void calculateCueWordWeight() {
        // add cue word weight to each sentence
        // if it contains at least one cue word
        for(int i = 0; i < this.noOfSentences; i++) {
            CList<String> sentence = this.tokenizedText.get(i);
            for(int j = 0; j < sentence.size(); j++) {
                if(this.cueWords.contains(sentence.get(j))) {
                    this.scores.get(i).setCueWordsWeight(this.GAMA);
                    break;
                }
            }
        }
    }

    private void calculateSkeletonWeight() {
        for(int i = 0; i < this.noOfSentences; i++) {
            CList<String> tokens = preProcessedText.get(i);
            for(int j = 0; j < tokens.size(); j++) {
                if(this.skeletonWords.contains(tokens.get(j))) {
                    this.scores.get(i).setSkeletonWeight(this.LAMBDA);
                }
            }
        }
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
        calculateCueWordWeight();
        calculateSkeletonWeight();
        calculateTotalScore();

        // sort sentences in descending order by totalScore
        // then frequency, cue word weight, skeleton weight, positional value
        Library.sort(scores, new CComparator<Score>() {
            @Override
            public int compare(Score obj1, Score obj2) {
                double diff = obj1.getTotalScore() - obj2.getTotalScore();
                if(diff != 0)
                    return diff > 0 ? -1 : 1;

                diff = obj1.getSentenceFrequency() - obj2.getSentenceFrequency();
                if(diff != 0)
                    return diff > 0 ? -1 : 1;

                diff = obj1.getCueWordsWeight() - obj2.getCueWordsWeight();
                if(diff != 0)
                    return diff > 0 ? -1 : 1;

                diff = obj1.getSkeletonWeight() - obj2.getSkeletonWeight();
                if(diff != 0)
                    return diff > 0 ? -1 : 1;

                diff = obj1.getPositionalValue() - obj2.getPositionalValue();
                if(diff != 0)
                    return diff > 0 ? -1 : 1;

                return 0;
            }
        });

        for(int i = 0; i < this.noOfSentences; i++) {
            System.out.print("STF: " + scores.get(i).getSentenceFrequency());
            System.out.print(", AP: " + scores.get(i).getActualPosition());
            System.out.print(", CW: " + scores.get(i).getCueWordsWeight());
            System.out.print(", PV: " + scores.get(i).getPositionalValue());
            System.out.print(", SW: " + scores.get(i).getSkeletonWeight());
            System.out.println(", TS: " + scores.get(i).getTotalScore());
        }
    }

    public static void main(String[] args) {

    }
}
















