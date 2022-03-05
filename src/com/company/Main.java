package com.company;

import bangla.stemmer.Stemmer;
import bangla.sw_remover.SWRemover;
import bangla.tokenizer.Tokenizer;
import my.library.MyList;
import my.library.MyComparator;
import my.library.Sorter;
import my.library.Trie;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Main {

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

    private void printList(MyList<MyList<String>> list) {
        for(int i = 0; i < list.size(); i++) {
            MyList<String> tokens = list.get(i);
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
//        String text = main.readFile("input_file.txt");
//        System.out.println(text);

//        Stemmer stemmer = new Stemmer();
//
//        Tokenizer tokenizer = new Tokenizer();
//        List<String> sentences = tokenizer.getSentences(text);
//        System.out.println(sentences.size());
//
//        for(int i = 0; i < sentences.size(); i++) {
//            String sentence = sentences.get(i);
//            List<String> words = tokenizer.getWords(sentence);
//            for(int j = 0; j < words.size(); j++)
//                System.out.println(words.get(j) + " -> " + stemmer.findStem(words.get(j)));
//        }


//        char ch = '!';
//        char ch1 = '!';
//        System.out.println((int)'।');
//        System.out.println((int)'৷');

//        Scanner in = new Scanner(System.in);
//
//        int n = in.nextInt();
//
//        MyList<Integer> a = new MyList<>();
//        for(int i = 0; i < n; i++) {
//            int num = in.nextInt();
//            a.add(num);
//        }
//
//        class MyCmp implements MyComparator<Integer> {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        }
//
//        Sorter.sort(a, new MyCmp());
//
//        for(int i = 0; i < a.size(); i++) {
//            System.out.print(a.get(i) + " ");
//        }

//        Trie trie = new Trie();
//        trie.add("Jubaer");
//        trie.add("Jubaer");
//        trie.add("Jubaer Hosain");
//        System.out.println(trie.contains("Jubaer"));
//        trie.remove("Jubaer");
//        trie.remove("Jubaer");
//        System.out.println(trie.contains("Jubaer"));
//        System.out.println(trie.contains("Jubaer Hosain"));
//        trie.remove("Jubaer");

        Tokenizer tokenizer = new Tokenizer();
        String txt = main.readFile("input_file.txt");
        MyList<MyList<String>> tokenizedText = tokenizer.tokenize(txt);


        char ch = '“';
        char ch1 = '“';
        System.out.println((int)ch);

        SWRemover swRemover = new SWRemover();
        tokenizedText = swRemover.remove(tokenizedText);
        main.printList(tokenizedText);

        Stemmer stemmer = new Stemmer();
        tokenizedText = stemmer.stemText(tokenizedText);
        main.printList(tokenizedText);


    }
}
