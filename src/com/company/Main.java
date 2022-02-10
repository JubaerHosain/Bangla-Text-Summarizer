package com.company;

import my.library.MyList;
import my.library.MyLibrary;

public class Main {

    private void start() {
        MyLibrary myLibrary = new MyLibrary();
        myLibrary.splitString("aab", "aaaisaaaisaais");
    }

    private void operation(int a[]) {
        a = new int[1];
        a[0] = 10;
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World");
        Main main = new Main();
//        main.start();

        MyList<Integer> list = new MyList<>();
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
        main.operation(a);
        System.out.println(a[0]);
    }
}
