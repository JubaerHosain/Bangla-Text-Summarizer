package my.library;

import java.lang.reflect.Type;

// this is a static class
public abstract class Library {
    // ====================CList Methods====================
    /** try to don't use for CLinkedList */
    public static <Type> void fill(CList<Type> list, Type value) {
        for(int i = 0; i < list.size(); i++) {
            list.replaceAt(i, value);
        }
    }

    // ====================String Methods====================
    /** return a substring from start(inclusive) to end */
    public static String substring(String string, int start){
        if(start < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        StringBuffer sb = new StringBuffer();
        for(int i = start; i < string.length(); i++)
            sb.append(string.charAt(i));
        return sb.toString();
    }

    /** return a substring from start(inclusive) to end(exclusive) */
    public static String substring(String string, int start, int end){
        if(start < 0 || end >= string.length()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        StringBuffer sb = new StringBuffer();
        for(int i = start; i < end; i++)
            sb.append(string.charAt(i));
        return sb.toString();
    }

    /** compares two string is equal or not */
    public static boolean equals(String a, String b) {
        if(a.length() != b.length())
            return false;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }

    /** removes spaces from beginning and end of the string */
    public static String trim(String string) {
        StringBuffer sb = new StringBuffer();
        int i = 0, j = string.length()-1;
        while(i < j && string.charAt(i) == ' ')
            i++;
        while(i < j && string.charAt(j) == ' ')
            j--;
        while(i <= j) {
             sb.append(string.charAt(i));
             i += 1;
        }
        return sb.toString();
    }

    /** returns true if string is contain given pattern(Using KMP) */
    public static boolean contains(String string, String pattern) {
        return true;
    }

    // ====================Sorting Algorithm====================
    /** sort list (don't use primitive type) */
    public static <Type> void sort(CList<Type> list, CComparator<Type> comparator) {
        Sorter<Type> sorter = new Sorter<>();
        sorter.sort(list, comparator);
    }

    /** sort array (don't use primitive type) */
    public static <Type> void sort(Type[] array, CComparator<Type> comparator) {
        Sorter<Type> sorter = new Sorter<>();
        sorter.sort(array, comparator);
    }



    // ====================Helper Methods====================
}
