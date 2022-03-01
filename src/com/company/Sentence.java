package com.company;

/** Related variables for a Sentence */
public class Sentence {
    // sentence frequency using word frequency = STF
    // positional value of this sentence = PV
    private int sentenceFrequency;
    private int positionalValue;
    private double cueWordWeight;
    private double sentenceScore;
    private int actualPosition;

    public Sentence(int actualPosition) {
        this.actualPosition = actualPosition;
        sentenceFrequency = 0;
        positionalValue = 0;
        cueWordWeight = 0;
        sentenceScore = 0;
    }

    public int getSentenceFrequency() {
        return sentenceFrequency;
    }

    public void setSentenceFrequency(int sentenceFrequency) {
        this.sentenceFrequency = sentenceFrequency;
    }

    public int getPositionalValue() {
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

    public double getSentenceScore() {
        return sentenceScore;
    }

    public void setSentenceScore(double sentenceScore) {
        this.sentenceScore = sentenceScore;
    }

    public int getActualPosition() {
        return actualPosition;
    }
}

















