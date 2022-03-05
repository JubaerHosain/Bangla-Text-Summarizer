package my.library;


/**
 * This is my custom List class that would be used in this project
 */
public class MyList<Type> {
    private int size = 0;
    private Type[] array;

    public MyList() {
        array = (Type[]) new Object[10];
    }

    public MyList(int maxSize) {
        array = (Type[]) new Object[maxSize];
    }

    public int size() {
        return this.size;
    }

    public Type[] toArray() {
        Type[] newArray = (Type[]) new Object[size];
        for(int i = 0; i < size; i++)
            newArray[i] = array[i];
        return newArray;
    }

    private void increaseArrayLength() {
        // Increase array size by 100%
        int newLength = array.length + array.length;
        Type[] newArray = (Type[]) new Object[newLength];
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void add(Type element) {
        if(array.length - size <= 5)
            increaseArrayLength();
        array[size++] = element;
    }

    public Type get(int index) {
        if(index < size) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void replaceAt(int index, Type element) {
        index = Math.abs(index);
        if(index < size) {
            array[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void removeFrom(int index) {
        index = Math.abs(index);
        if(index < size) {
            array[index] = null;
            while(index < size) {
                array[index] = array[index+1];
                index = index + 1;
            }
            size--;
            array[index] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean contains(Type element) {
        for(int i = 0; i < size; i++) {
            if(array[i] == element)
                return true;
        }
        return false;
    }
}
