package my.library;

import java.util.Arrays;

public class MyList<Type> {
    private int size = 0;
    private Type[] array;

    public MyList() {
        // Initial given size is 500
        array = (Type[]) new Object[500];
    }

    private void increaseArrayLength() {
        // Increase array size by twice
        array = Arrays.copyOf(array, 2*array.length);
    }

    public Type get(int index) {
        if(index < size) {
            return array[index];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(Type element) {
        if(array.length - size <= 5) {
            increaseArrayLength();
        }
        array[size++] = element;
    }
}
