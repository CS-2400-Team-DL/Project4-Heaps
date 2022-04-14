package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{

    public static void main(String[] args){
	    
        ArrayList<Integer> data = new ArrayList<>();
        File file = new File("data.txt");

        try {
            if (file.exists()){
                System.out.println("Reading " + file.getName());
            } else {
                System.out.println(file.getName() + " created");
                file.createNewFile();
            }

            Scanner input = new Scanner(file);

            while (input.hasNextInt()) {
                data.add(input.nextInt());
            }

            input.close();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        int[] intArray = new int[data.size()];
        int DATASIZE = data.size();
        for(int i=0; i< DATASIZE; i++){
        	intArray[i] = data.remove(0);
        }
        
        ArrayMaxHeap heap1 = new ArrayMaxHeap(intArray.length);
        int swaps = heap1.sequentialInsertBuild(intArray);
        
        
        // create heap 2 with optimal method
        
        // output to file

    }
}
