package my.library;

/**
 * Implements using dynamic array
 * @param <Type>
 */
public class CArrayList<Type> implements CList<Type> {
    private int size = 0;
    private int capacity = 10;
    private Type[] array;

    public CArrayList() {
        array = (Type[]) new Object[capacity];
    }

    public CArrayList(int capacity) {
        this.capacity = capacity;
        array = (Type[]) new Object[capacity+5];
    }

    @Override
    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    @Override
    public void add(Type element) {
        if(capacity - size <= 2)
            increaseArrayLength();
        array[size++] = element;
    }

    @Override
    public Type get(int index) {
        if(index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void replaceAt(int index, Type element) {
        if(index >= 0 && index < size) {
            array[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void insertAt(int index, Type element) {

    }

    @Override
    public void removeFrom(int index) {
        if(index >= 0 && index < size) {
            array[index] = null;
            while(index < size) {
                array[index] = array[index+1];
                index += 1;
            }
            size -= 1;
            array[index] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean contains(Type element) {
        for(int i = 0; i < size; i++) {
            if(array[i] == element)
                return true;
        }
        return false;
    }

    @Override
    public Type[] toArray() {
        Type[] newArray = (Type[]) new Object[size];
        for(int i = 0; i < size; i++)
            newArray[i] = this.array[i];
        return newArray;
    }

    @Override
    public CIterator<Type> getIterator() {
        CIterator<Type> iterator = new CIterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && array[index] != null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Type next() {
                return array[index++];
            }
        };
        return iterator;
    }

    // private methods
    private void increaseArrayLength() {
        // Increase array size by 50%
        capacity = array.length + array.length/2;
        Type[] newArray = (Type[]) new Object[capacity+2];
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void clear() {
        array = null;
        size = 0;
        capacity = 0;
    }
}
