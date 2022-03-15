package scoring_and_ranking;

/** Related variables for a Sentence */
public class Score {
    // sentence frequency using word frequency = STF
    // sum of all word_frequency of the sentence
    // word frequency = how many times this word occurred in the total text
    private int sentenceFrequency;

    // positional value of this sentence = PV
    // formula
    private double positionalValue;

    private double cueWordWeight;
    private double totalScore;

    // position of sentence in the text
    private int actualPosition;

    public Score(int actualPosition) {
        this.actualPosition = actualPosition;
        sentenceFrequency = 0;
        positionalValue = 0;
        cueWordWeight = 0;
        totalScore = 0;
    }

    public int getSentenceFrequency() {
        return sentenceFrequency;
    }

    public void setSentenceFrequency(int sentenceFrequency) {
        this.sentenceFrequency = sentenceFrequency;
    }

    public double getPositionalValue() {
        return positionalValue;
    }

    public void setPositionalValue(int positionalValue) {
        this.positionalValue = positionalValue;
    }

    public double getCueWordWeight() {
        return cueWordWeight;
    }

    public void setCueWordWeight(double cueWordWeight) {
        this.cueWordWeight = cueWordWeight;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public int getActualPosition() {
        return actualPosition;
    }
}

















