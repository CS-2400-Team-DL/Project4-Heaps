
public class ArrayMaxHeap implements HeapInterface<Integer>{

    private int[] heap;
    private int indexOfLast;

    private static int DEFAULT_CAPACITY = 20;
    private static int MAX_CAPACITY = 1000000;
    private boolean isMaxed;

    public ArrayMaxHeap(){
        this(DEFAULT_CAPACITY);
    }
    public ArrayMaxHeap(int size){
        if (size == MAX_CAPACITY){
            isMaxed = true;
            heap = new int[MAX_CAPACITY + 1];
        } else if (size < MAX_CAPACITY){
            heap = new int[size + 1];
            isMaxed = false;
        } else {
            throw new IllegalArgumentException("Capacity exceeds limit");
        }
        indexOfLast = 0;
    }

    @Override
    public boolean isEmpty() {
        return (indexOfLast < 1);
    }

    @Override
    public int getSize() {
        return indexOfLast;
    }

    @Override
    public Integer removeRoot() {
        Integer root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[indexOfLast];
            indexOfLast--;
            reHeap(1);
        }
        return root;
    }

    private void reHeap(int startingIndex){
        boolean done = false;
        int active = heap[startingIndex];
        int leftChild = startingIndex * 2;
        while (!done && (leftChild <= indexOfLast)){
            int largestChild = leftChild;
            int rightChild = leftChild + 1;

            if ((rightChild <= indexOfLast) && (heap[rightChild] >= heap[largestChild]) ){
                largestChild = rightChild;
            }

            if (active <= heap[largestChild]){
                heap[startingIndex] = heap[largestChild];
                startingIndex = largestChild;
                leftChild = 2 * startingIndex;
            } else {
                done = true;
            }
            heap[startingIndex] = active;
        }
    }

    @Override
    public Integer getRoot() {
        Integer root = null;
        if(!isEmpty()){
            root = heap[1];
        }
        return root;
    }

    @Override
    public boolean add(Integer entry) {
        if (!isMaxed){
            int newIndex = indexOfLast + 1;
            int parentIndex = (int) ((newIndex) / 2);
            
            if (newIndex >= heap.length && !growArray()){
                return false;
            }
            while ( (parentIndex > 0) && entry >= heap[parentIndex]){
                heap[newIndex] = heap[parentIndex];
                newIndex = parentIndex;
                parentIndex = ((newIndex) / 2);
            }

            heap[newIndex] = entry;
            indexOfLast++;
        }
        return true;
    }

    @Override
    public void clear() {
        while(indexOfLast > 0){
            heap[indexOfLast] = 0;
            indexOfLast--;
        }
    }

    private boolean growArray(){
        if(!isMaxed){
            int[] tmpHeap;
            int newSize = (int) 2 *  heap.length;

            if (newSize > MAX_CAPACITY){
               tmpHeap = new int[MAX_CAPACITY + 1];
               isMaxed = true;
            } else {
                tmpHeap = new int[newSize]; 
            }  

            //Transfer the content of original array to the new one;
            for(int i=0; i<heap.length; i++){
                tmpHeap[i] = heap[i];
            }
            heap = tmpHeap;
            return true;
        } else {
            return false;
        }
    }
}