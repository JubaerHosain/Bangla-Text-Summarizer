package scoring_and_ranking;

/** Related variables for a Sentence */
public class Score {
    // sentence frequency using word frequency = STF
    // sum of all word_frequency of the sentence
    // word frequency = how many times this word occurred in the total text
    private int sentenceFrequency;

    // positional value of this sentence = PV
    // formula .........................................
    private double positionalValue;

    private double cueWordsWeight;

    private double skeletonWeight;
    private double totalScore;

    // position of sentence in the text
    private int actualPosition;

    public Score(int actualPosition) {
        this.actualPosition = actualPosition;
        this.sentenceFrequency = 0;
        this.positionalValue = 0;
        this.cueWordsWeight = 0;
        this.totalScore = 0;
        this.skeletonWeight = 0;
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

    public void setPositionalValue(double positionalValue) {
        this.positionalValue = positionalValue;
    }

    public double getCueWordsWeight() {
        return cueWordsWeight;
    }

    public void setCueWordsWeight(double cueWordsWeight) {
        this.cueWordsWeight = cueWordsWeight;
    }

    public double getSkeletonWeight() {
        return skeletonWeight;
    }

    public void setSkeletonWeight(double skeletonWeight) {
        this.skeletonWeight = skeletonWeight;
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

















