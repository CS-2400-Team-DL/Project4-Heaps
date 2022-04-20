package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{

    public static void main(String[] args){

        //Get input from data.txt
        int[] intArray = getInput();
        // Data from data.txt has been moved into intArray

        if(intArray.length > 0){

            ArrayMaxHeap heap1 = new ArrayMaxHeap();
            int swaps1 = heap1.sequentialInsertBuild(intArray);
        
            // Format the string toFile1 for heap 1
            String toFile1 = "\nHeap1:\nHeap built using sequential insertions: ";
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
            // End of String formating
            
            ArrayMaxHeap heap2 = new ArrayMaxHeap();
            // create heap 2 with optimal method
            int swaps2 = heap2.optimalMethodBuild(intArray);
            String toFile2 = "\nHeap2:\nHeap built using optimal method: ";
            for(int i=1;i<=10;i++){
                toFile2 += heap2.getHeap()[i] + ", ";
            } 

            toFile2 += "\nNumber of swaps in heap creation: " + swaps2;
            
            for(int i=0; i<10;i++){
                heap2.removeRoot();
            }

            toFile2 += "\nHeap after 10 removals: ";
            for(int i=1;i<=10;i++){
                toFile2 += heap2.getHeap()[i] + ", ";
            }    

            // Output to a file
            File output = new File("output.txt");
            try{
                if(!output.exists()){
                    output.createNewFile();
                    System.out.println(" Output created at: " + output.getAbsolutePath());
                } else {
                    System.out.println(" Output.txt has been updated");
                    output.delete();
                    output.createNewFile();
                }

                FileWriter writer1 = new FileWriter(output);
                writer1.write(toFile1);
                writer1.write(toFile2);
                writer1.close();


            } catch (IOException e) {   e.printStackTrace();    }

        } else {
            System.out.println("data.txt is empty");
        }
    }

    private static int[] getInput(){

        ArrayList<Integer> data = new ArrayList<>();
        File file = new File("data.txt");

        try {

            if (file.exists()){
                System.out.println(" Reading " + file.getName());
            } else {
                System.out.println(" "+file.getName() + "was created at " + file.getAbsolutePath());
                file.createNewFile();
            }

            Scanner input = new Scanner(file);

            int line = 1;
            while (input.hasNext()){
                if (input.hasNextInt()) {
                    data.add(input.nextInt());
                } else{
                    input.next();
                    System.out.println(" NON-integer in data.txt on line " + line);
                }
                line++;
            }   
            input.close();
            
        } catch(IOException e) {    e.printStackTrace();    }

        int[] intArray = new int[data.size()];
        for(int i=0; i<intArray.length ; i++){
        	intArray[i] = data.remove(0);
        }

        return intArray;
    }

}
