package com.company;

import bangla.stemmer.Stemmer;
import bangla.tokenizer.Tokenizer;
import my.library.List;
import my.library.Sorter;

import java.io.*;
import java.net.URL;
import java.util.Comparator;
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


    public static void main(String[] args) throws IOException {
        // write your code here
//        Main main = new Main();
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

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        List<Integer> a = new List<>();
        for(int i = 0; i < n; i++) {
            int num = in.nextInt();
            a.add(num);
        }

        class MyCmp implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        Sorter.sort(a, new MyCmp());

        for(int i = 0; i < a.size(); i++) {
            System.out.print(a.get(i) + " ");
        }
    }
}
