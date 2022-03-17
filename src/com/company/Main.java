package com.company;

import my.library.*;
import pre_processing.SWRemover;
import pre_processing.Stemmer;
import pre_processing.Tokenizer;
import scoring_and_ranking.Ranker;

import java.io.*;
import java.net.URL;

public class Main {
    private char DARI1 = '।';
    private char DARI2 = '৷';
    private String titleOrHeader;

    public Main() {
        this.titleOrHeader = "";
    }


    private String readFile(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
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
                System.out.println(line);
            }
        }

        this.titleOrHeader = sbTitle.toString();
        bufferedReader.close();
        return sb.toString();
    }


    private void writeFile(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        String str = "Hello World";
        for(int i = 0; i < 17; i++)
            bufferedWriter.write("Hello World1+1 ");

        bufferedWriter.close();
    }


    private void printList(CList<CList<String>> list) {
        for(int i = 0; i < list.size(); i++) {
            CList<String> tokens = list.get(i);
            System.out.print((i+1) + ": ");
            for(int j = 0; j < tokens.size(); j++) {
                System.out.print(tokens.get(j) + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        // read text from file
        Main main = new Main();
        String inputText = main.readFile("input_file.txt");

        // tokenize text
        Tokenizer tokenizer = new Tokenizer();
        CList<CList<String>> tokenizedText = tokenizer.tokenize(inputText);

        // extract skeleton words titleOrHeader using tokenizer
        CList<String> skeletonWords = tokenizer.getWords(main.titleOrHeader);


        // remove stop words
        SWRemover swRemover = new SWRemover();
        CList<CList<String>> swRemovedText = swRemover.remove(tokenizedText);

        // stem words of each sentences
        Stemmer stemmer = new Stemmer();
        CList<CList<String>> stemmedText = stemmer.stemText(swRemovedText);

        // stem skeletonWords
        skeletonWords = stemmer.stemList(skeletonWords);

        // rank the sentences
        Ranker ranker = new Ranker(skeletonWords, tokenizedText, stemmedText);
        ranker.rank();


        main.writeFile("output_file.txt");
    }
}

























