
public class ArrayMaxHeap implements HeapInterface<Integer>{

    final static int DEFAULT_SIZE = 25;
    private int[] heap;

    public ArrayMaxHeap(){
        this(DEFAULT_SIZE);
    }

    public ArrayMaxHeap(int size){
        heap = new int[size];
    }

    private void growHeap(){
        int currentSize = heap.length;
        int newSize = (int) Math.floor(1.5 * currentSize);
        int[] newHeap = new int[newSize];
        for (int i=0; i < heap.length; i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = false;
        for (int i=0; i < heap.length; i++){
            if (heap[i] == 0){
                empty = true;
            } else {
                empty = false;
            }
        }
        return empty;
    }

    @Override
    public boolean insert(Integer data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean optimalAdd(Integer data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Integer remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSize() {
        return heap.length;
    }
    
}