public class Driver{

    public static void main(String[] args){

        ArrayMaxHeap heap = new ArrayMaxHeap(4);

        for ( int i=0; i < 15; i++){
            System.out.println(heap.add(i));
        }
        System.out.println(heap.getRoot());
        heap.removeRoot();
        heap.add(99);
        heap.clear();

        heap.add(1);

    }
}