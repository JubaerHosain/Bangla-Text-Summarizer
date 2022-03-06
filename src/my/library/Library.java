package my.library;

import java.util.Iterator;

// this is a static class
public abstract class Library {
    public static <Type> void fill(CList<Type> list, Type value) {
        CIterator it = list.getIterator();
//        arraylist vs link list
    }
}
