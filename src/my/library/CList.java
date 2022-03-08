package my.library;

public interface CList<Type> extends CIterable<Type>{
    public int size();
    public void add(Type element);
    public Type get(int index);
    public void replaceAt(int index, Type element);
    public void insertAt(int index, Type element);
    public void removeFrom(int index);
    public boolean contains(Type element);
    public Type[] toArray();
}
