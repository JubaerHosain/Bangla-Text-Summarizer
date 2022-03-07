package my.library;

public interface CIterator<T> {
    public boolean hasNext();
    public boolean hasPrevious();
    public T next();
}
