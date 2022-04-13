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

            Scanner input = new Scanner(new FileReader(file));

            while (input.hasNextInt()) {
                data.add(input.nextInt());
            }

            input.close();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(data.size());
        int[] intArray = new int[data.size()];
        System.out.println(intArray.length);

        for(int i=0; i < data.size(); i++){
            intArray[i] = data.remove(i);
        }

        

    }
}
