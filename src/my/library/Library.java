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

    /** removes spaces from beginning and end */
    public static String trim(String string) {
        StringBuffer sb = new StringBuffer();
        int i = 0, j = string.length()-1;
        while(string.charAt(i) == ' ')
            i++;
        while(string.charAt(j) == ' ')
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

    // ====================Sort Algorithm====================
    public static <Type> void sort(CList<Type> list, CComparator<Type> comparator) {
        Type[] array = list.toArray();

        class Sorter<Type> {
            private Type[] array;

            public Sorter(Type[] array) {
                this.array = array;
            }


            private static <T> void merge(T[] array, int left, int mid, int right, CComparator<T> comparator) {
                int i = left, j = mid+1;
                T[] newArray = (T[]) new Object[right-left+1];
                // merge in newArray in sorted order
                int inx = 0;
                while(i <= mid && j <= right) {
                    int compare = comparator.compare(array[i], array[j]);
                    if(compare <= 0) {
                        newArray[inx++] = array[i++];
                    } else if(compare > 0) {
                        newArray[inx++] = array[j++];
                    }
                }
                // Handle the leftover elements if any on the left side
                while(i <= mid) {
                    newArray[inx++] = array[i++];
                }
                // Handle the righter elements if any on the left side
                while(j <= right) {
                    newArray[inx++] = array[j++];
                }
                // copy elements of newArray to array
                for(int l = 0; l < newArray.length; l++)
                    array[left++] = newArray[l];
            }

            /**Overloaded sort method*/
            private static <T> void sort(T[] array, int left, int right, CComparator<T> comparator) {
                if (array == null) {
                    throw new IllegalArgumentException("array is null.");
                }
                if(left >= right)
                    return;
                int mid = (left + right) / 2;
                sort(array, left, mid, comparator);
                sort(array, mid+1, right, comparator);
                merge(array, left, mid, right, comparator);
            }

            /**Overloaded sort method*/
            public static <T> void sort(T[] array, CComparator<T> comparator) {
                if (array == null) {
                    throw new IllegalArgumentException("array is null.");
                }
                sort(array, 0, array.length-1, comparator);
            }

            /**Overloaded sort method*/
            public static <Type> void sort(CList<Type> myList, CComparator<Type> comparator) {
                if (myList == null) {
                    throw new IllegalArgumentException("list is null.");
                }
                Type[] array = myList.toArray();
                sort(array, 0, array.length-1, comparator);
                // restore to the actual list
                for(int i = 0; i < array.length; i++)
                    myList.replaceAt(i, array[i]);
            }
        }

    }



    // ====================Helper Methods====================
}
