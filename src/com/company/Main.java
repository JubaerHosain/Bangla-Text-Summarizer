package com.company;

import my.library.CList;
import my.library.Library;
import pre_processing.SWRemover;
import pre_processing.Stemmer;
import pre_processing.Tokenizer;
import scoring_and_ranking.Ranker;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private char DARI1 = '।';
    private char DARI2 = '৷';
    private String titleOrHeader;

    public Main() {
        this.titleOrHeader = "";
    }


    private String readFile(String fileName) throws IOException {
        // URL url = this.getClass().getResource(fileName);
        // File file = new File(url.getFile());
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        StringBuffer sb = new StringBuffer();
        StringBuffer sbTitle = new StringBuffer();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            line = Library.trim(line);
            sb.append(line + " ");

            // if this line is a header or title and not contain dari
            if(line.length() <= 5 && !line.contains(this.DARI1+"") && !line.contains(this.DARI2+"")) {
                sbTitle.append(line + " ");
            }
        }

        this.titleOrHeader = sbTitle.toString();
        bufferedReader.close();
        return sb.toString();
    }


    private void writeFile(String fileName) throws IOException {
        // URL url = this.getClass().getResource(fileName);
        // File file = new File(url.getFile());
        File file = new File(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        String str = "Hello World";
        for(int i = 0; i < 17; i++)
            bufferedWriter.write("Hello World" + i + i + "\n");

        bufferedWriter.close();
    }

    private int wordCount(CList<CList<String>> list) {
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            CList<String> tokens = list.get(i);
            for(int j = 0; j < tokens.size(); j++) {
                count += tokens.get(j).length();
            }
        }
        return count;
    }


    private void generateSummary(CList<Integer> positions, CList<CList<String>> tokenizedText) {
        // take input from user, the percentage user want to see
        System.out.print("\t\t\t How much you want to see(percentage): ");
        Scanner scanner = new Scanner(System.in);
        double percentage = scanner.nextDouble();

        int totalSentence = positions.size();
        int noOfSentence = (int) ((percentage/100.0) * totalSentence);
        
        if(noOfSentence < 2) {
            noOfSentence = 2;
        }
        if(noOfSentence > totalSentence) {
            noOfSentence = totalSentence;
        }

        // remove extra sentences from the end
        for(int i = totalSentence-1; i >= noOfSentence; i--)
            positions.removeFrom(i);

        // sort by ascending order of actual position
        Library.sort(positions, (obj1, obj2) -> obj1 - obj2);

        System.out.println();
        System.out.print("Summary: ");

        for(int i = 0; i < noOfSentence; i++) {
            CList<String> sentence = tokenizedText.get(positions.get(i));
            System.out.println(Library.listToString(sentence) + "৷");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("\t\t\t Welcome to BANGLA TEXT SUMMARIZER\n");
        System.out.print("\t\t\t Enter input file path: ");

        Scanner scanner = new Scanner(System.in);
        String inputFileName = scanner.next();

        long startTime = System.nanoTime();

        // read text from file
        Main main = new Main();
//        String inputText = main.readFile("input_file.txt");
        String inputText = main.readFile(inputFileName);

        // tokenize text
        Tokenizer tokenizer = new Tokenizer();
        CList<CList<String>> tokenizedText = tokenizer.tokenize(inputText);

        // extract skeleton words titleOrHeader using tokenizer
        CList<String> skeletonWords = tokenizer.getWords(main.titleOrHeader);

        // remove stop words from tokens
        SWRemover swRemover = new SWRemover();
        CList<CList<String>> swRemovedText = swRemover.removeStopWords(tokenizedText);

        // stem stop word removed words of each sentences
        Stemmer stemmer = new Stemmer();
        CList<CList<String>> stemmedText = stemmer.stemText(swRemovedText);

        // stem skeletonWords
        CList<String> stemmedSkeletonWords = stemmer.stemList(skeletonWords);

        // rank the sentences
        Ranker ranker = new Ranker(stemmedSkeletonWords, tokenizedText, stemmedText);
        CList<Integer> positions = ranker.rank();

        System.out.println(main.wordCount(tokenizedText));

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1e9);

        main.generateSummary(positions, tokenizedText);
    }
}

