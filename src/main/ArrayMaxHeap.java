package main;

public class ArrayMaxHeap implements HeapInterface<Integer>{

    private int[] heap;
    private int indexOfLast;

    private static int DEFAULT_CAPACITY = 20;
    private static int MAX_CAPACITY = 1000000;
    private boolean isMaxed;

    public ArrayMaxHeap(){
        this(DEFAULT_CAPACITY);
    }
    public ArrayMaxHeap(int initialSize){
        if (initialSize == MAX_CAPACITY){
            isMaxed = true;
            heap = new int[MAX_CAPACITY + 1];
        } else if (initialSize < MAX_CAPACITY){
            heap = new int[initialSize + 1];
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

    public int[] getHeap(){
        return heap;
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

    private int reHeap(int startingIndex){
        //added amount of swaps to indicate how many times it swapped.
        int reHeapSwaps = 0;
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
                reHeapSwaps++;
            } else {
                done = true;
            }
        }
        heap[startingIndex] = active;
        return reHeapSwaps;
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

    public int sequentialInsertBuild(int[] intArray){
    	int swaps = 0;
		
		
		for (int i=0; i < intArray.length; i++) {
			
			int newIndex = indexOfLast + 1;
			int parent = newIndex / 2;

            if (newIndex >= heap.length) {growArray();}

			while( (parent > 0) && (intArray[i] >= heap[parent]) ) {
				swaps++;
				heap[newIndex] = heap[parent];
				newIndex = parent;
				parent = newIndex / 2;
			}
			
			heap[newIndex] = intArray[i];
			indexOfLast++;
        
		}
		
		return swaps;
    }

    public int optimalMethodBuild(int[] unsortedArray){
        int swaps = 0;
        
        for (int i=0; i < unsortedArray.length; i++){
            int newIndex = indexOfLast + 1;
            if(newIndex >= heap.length){
                growArray();
            }
            heap[newIndex] = unsortedArray[i];
            indexOfLast++;
        }

        for (int rootIndex = indexOfLast / 2; rootIndex > 0; rootIndex--){
            swaps += reHeap(rootIndex);
        }
        return swaps;
    }


}
