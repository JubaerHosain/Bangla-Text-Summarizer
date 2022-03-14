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
        ranker = new Ranker();
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

        //String inputText = main.readFile("input_file.txt");

        // tokenize text
        //CList<CList<String>> tokens = main.tokenizer.tokenize(inputText);

        // remove stop words from tokens
        //tokens = main.swRemover.remove(tokens);

        // calculate cue word weight
//        MyList<Integer> sentence_rank = main.ranker.toString();

        CList<Integer> list = new CArrayList<>();
        list.add(4);
        list.add(2);
        list.add(-8);
        list.add(0);
        list.add(67);
        list.add(12);
        list.add(9);
        Library.sort(list, (obj1, obj2) -> obj1 - obj2);

        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();

        Integer[] array = new Integer[5];
        array[0] = 9;
        array[1] = -9;
        array[2] = 89;
        array[3] = 18;
        array[4] = -999;
        Library.sort(array, (obj1, obj2) -> obj1 - obj2);

        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
    }
}

























