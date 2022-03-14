package my.library;

class Sorter<Type> {
    private Type[] array;

    public Sorter() {}

    /** marge two sorted segment */
    private void merge(int left, int mid, int right, CComparator<Type> comparator) {
        int i = left, j = mid+1;
        Type[] newArray = (Type[]) new Object[right-left+1];
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
        // Handle the righter elements if any on the right side
        while(j <= right) {
            newArray[inx++] = array[j++];
        }
        // copy elements of newArray to array
        for(int l = 0; l < newArray.length; l++)
            array[left++] = newArray[l];
    }

    /**Overloaded sort method (recursive)*/
    private void sort(int left, int right, CComparator<Type> comparator) {
        if(left >= right)
            return;
        int mid = (left + right) / 2;
        sort(left, mid, comparator);
        sort(mid+1, right, comparator);
        merge(left, mid, right, comparator);
    }

    /**Overloaded sort method (sort array)*/
    public void sort(Type[] array, CComparator<Type> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("array is null.");
        }
        this.array = array;
        sort(0, array.length-1, comparator);
    }

    /**Overloaded sort method (sort list)*/
    public void sort(CList<Type> myList, CComparator<Type> comparator) {
        if (myList == null) {
            throw new IllegalArgumentException("list is null.");
        }
        this.array = myList.toArray();
        sort(0, array.length-1, comparator);
        // not efficient for linked list
        // restore to the actual list
        for(int i = 0; i < array.length; i++)
            myList.replaceAt(i, array[i]);
    }
}
