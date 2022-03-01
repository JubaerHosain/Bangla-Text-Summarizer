package my.library;

/**Implements merge sort algorithm*/
public class Sorter {
    private static <T> void merge(T[] array, int left, int mid, int right, MyComparator<T> comparator) {
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
    private static <T> void sort(T[] array, int left, int right, MyComparator<T> comparator) {
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
    public static <T> void sort(T[] array, MyComparator<T> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("array is null.");
        }
        sort(array, 0, array.length-1, comparator);
    }

    /**Overloaded sort method*/
    public static <T> void sort(MyList<T> myList, MyComparator<T> comparator) {
        if (myList == null) {
            throw new IllegalArgumentException("list is null.");
        }
        T[] array = myList.toArray();
        sort(array, 0, array.length-1, comparator);
        // restore to the actual list
        for(int i = 0; i < array.length; i++)
            myList.replaceAt(i, array[i]);
    }
}
