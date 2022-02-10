package com.company;

import my.library.MyLibrary;

public class Main {

    private void start() {
        MyLibrary myLibrary = new MyLibrary();
        myLibrary.splitString("aab", "aaaisaaaisaais");
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World");
        Main main = new Main();
        main.start();
    }
}
