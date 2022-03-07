package my.library;

// this is a static class
public abstract class Library {
    // ====================CList Methods====================
    /** don't use for CLinkedList */
    public static <Type> void fill(CList<Type> list, Type value) {
        for(int i = 0; i < list.size(); i++) {
            list.replaceAt(i, value);
        }
    }

    // ====================String Methods====================
    /** return a substring from start(inclusive) to end */
    public static String substring(String string, int start){
        return string;
    }

    /** return a substring from start(inclusive) to end(exclusive) */
    public static String substring(String string, int start, int end){
        return string;
    }

    public static boolean equals(String a, String b) {
        if(a.length() != b.length())
            return false;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }

    // ====================Sort Algorithm====================
    public static <Type> void sort(CList<Type> list) {

    }
}
