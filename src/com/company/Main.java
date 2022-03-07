package com.company;

import my.library.CArrayList;
import my.library.CIterator;
import my.library.CList;
import my.library.MyList;
import pre_processing.SWRemover;
import pre_processing.Stemmer;
import pre_processing.Tokenizer;
import scoring_and_ranking.CueWord;

import java.io.*;
import java.net.URL;

public class Main {
    private Tokenizer tokenizer;
    private SWRemover swRemover;
    private Stemmer stemmer;
    private CueWord cueWord;

    public Main() throws IOException {
        tokenizer = new Tokenizer();
        swRemover = new SWRemover();
        stemmer = new Stemmer();
        cueWord = new CueWord();
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

//        Tokenizer tokenizer = new Tokenizer();
//        String txt = main.readFile("input_file.txt");
//        MyList<MyList<String>> tokenizedText = tokenizer.tokenize(txt);
//
//        SWRemover swRemover = new SWRemover();
        //tokenizedText = swRemover.remove(tokenizedText);
        //main.printList(tokenizedText);

        //Stemmer stemmer = new Stemmer();
        //tokenizedText = stemmer.stemText(tokenizedText);
        //main.printList(tokenizedText);

//        MyList<String> tokens = tokenizedText.get(0);
//        for(int i = 0; i < tokens.size()-1; i++) {
//            System.out.println(tokens.get(i) + " -> " + main.stemmer.stemWord(tokens.get(i)));
//        }

        CList<Integer> list = new CArrayList<>();
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(6);
        list.add(8);
        list.add(9);
        CIterator iterator = list.getIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
