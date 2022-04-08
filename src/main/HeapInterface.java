
public interface HeapInterface<T> {

    public boolean isEmpty();

    public boolean insert(T data);

    public boolean optimalAdd(T data);

    public T remove();

    public int getSize();

}