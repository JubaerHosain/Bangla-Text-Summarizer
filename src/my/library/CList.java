package my.library;

public interface CList<Type> extends CIterable<Type>{
    int size();
    void add(Type element);
    Type get(int index);
    void replaceAt(int index, Type element);
    void insertAt(int index, Type element);
    void removeFrom(int index);
    boolean contains(Type element);
    Type[] toArray();
    void clear();
}
