package com.company;

import my.library.CArrayList;
import my.library.CComparator;
import my.library.CList;
import my.library.Library;
import pre_processing.SWRemover;
import pre_processing.Stemmer;
import pre_processing.Tokenizer;
import scoring_and_ranking.Ranker;

import java.io.*;
import java.net.URL;

public class Main {
    private Tokenizer tokenizer;
    private SWRemover swRemover;
    private Stemmer stemmer;
    private Ranker ranker;

    public Main() throws IOException {
        tokenizer = new Tokenizer();
        swRemover = new SWRemover();
        stemmer = new Stemmer();
        //ranker = new Ranker();
    }

    private String readFile(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        while (bufferedReader.ready()) {
            sb.append(bufferedReader.readLine() + " ");
        }
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
        // write your code here
        Main main = new Main();

        String inputText = main.readFile("input_file.txt");
        //System.out.println(inputText);

        // tokenize text
        CList<CList<String>> tokens = main.tokenizer.tokenize(inputText);

        Ranker ranker = new Ranker(tokens, tokens);
        ranker.rank();


        main.writeFile("output_file.txt");
    }
}

























