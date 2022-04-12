
public interface HeapInterface<T extends Comparable<? super T>>{ 
    // type parameter must support comparison with other instances of its own type

    public boolean isEmpty();
    public int getSize();

    public T removeRoot();
    public T getRoot();

    public boolean add(T entry);
    public void clear();

}