package com.company;

import my.library.List;
import bangla.tokenizer.MyLibrary;

public class Main {

    private void start() {
        MyLibrary myLibrary = new MyLibrary();
        myLibrary.splitString(".", "My Name is Md Jubaer Hosain. I am a Student of University of Dhaka.");
    }

    private void check() {
        List<Integer> list = new List<>();
        list.add(45);
        list.add(34);
        list.add(35);
        list.add(34545);
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();

        list.remove(0);
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();

        list.remove(0);
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();

        list.add(242424);
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
        System.out.println();

        int[] a = new int[1];
        a[0] = 99;
        operation(a);
        System.out.println(a[0]);
    }

    private void operation(int a[]) {
        a = new int[1];
        a[0] = 10;
    }

    public static void main(String[] args) {
        // write your code here
        Main main = new Main();
//        main.start();

//        int ch = 0980;
//        char ch = 'ঀ';
//        System.out.println(ch);
//        System.out.println((int)ch);
//        char c1 = (char)2432;
//        System.out.println(c1);
//        int hex = 0x9FF;
//        System.out.println(hex);

        String str1 = "গেল";
        String str = ".েল";

        String a[] = str1.split(str);
//      System.out.println(a[0]);
        System.out.println(str1.contains(str));

        String input = "পুঞ্জ";
        for(int i = 0; i < input.length(); i++) {
            System.out.println(input.charAt(i));
        }
    }
}
