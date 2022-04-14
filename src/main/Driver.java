package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{

    public static void main(String[] args){
	    
        //Get input from data.txt
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
        int DATASIZE = data.size();
        int[] intArray = new int[DATASIZE];
        for(int i=0; i< DATASIZE; i++){
        	intArray[i] = data.remove(0);
        }
        // Data from data.txt has been moved into intArray
        if(intArray.length > 0){

            ArrayMaxHeap heap1 = new ArrayMaxHeap(intArray.length);
            int swaps1 = heap1.sequentialInsertBuild(intArray);
        
            String toFile1 = "Heap1:\nHeap built using sequential insertions: ";
            for(int i=1;i<=10;i++){
                toFile1 += heap1.getHeap()[i] + ", ";
            } 

            toFile1 += "\nNumber of swaps in heap creation: " + swaps1;
            
            for(int i=0; i<10;i++){
                heap1.removeRoot();
            }

            toFile1 += "\nHeap after 10 removals: ";
            for(int i=1;i<=10;i++){
                toFile1 += heap1.getHeap()[i] + ", ";
            }    
            
            ArrayMaxHeap heap2 = new ArrayMaxHeap(intArray.length);
            // create heap 2 with optimal method
            int swaps2;
            String toFile2;


            File output = new File("output.txt");
            try{
                if(!output.exists()){
                    output.createNewFile();
                    System.out.println("Output created at: " + output.getAbsolutePath());
                } else {
                    System.out.println("output.txt has been updated");
                    output.delete();
                    output.createNewFile();
                }

                FileWriter writer1 = new FileWriter(output);
                writer1.write(toFile1);
                //writer1.write(toFile2);
                writer1.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("data.txt is empty");
        }
    }
}
