/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamultiplactor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author xavie
 */
public class DataMultiplactor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
       String input = args[0]; 
       double desiredLength = Double.parseDouble(args[1]); 
       String outputFile = args[2]; 
       Scanner fileScanner = new Scanner (new File(input));  
       fileScanner.useDelimiter(","); 
       ArrayList<String> values = new ArrayList<String>(); 
       double largestX = Double.MIN_VALUE; 
       double largestY = Double.MIN_VALUE; 
 
       while(fileScanner.hasNextLine())
       {
           String line = fileScanner.nextLine(); 
           Scanner lineScanner = new Scanner(line); 
           lineScanner.useDelimiter(",");
           double tempID = lineScanner.nextDouble(); 
           lineScanner.next(); 
           double tempX = lineScanner.nextDouble(); 
           double tempY = lineScanner.nextDouble(); 
           
           largestX= tempX>largestX? tempX:largestX; 
           largestY= tempY>largestY? tempY:largestY; 
           values.add(line); 
       }
      // System.out.println(values);
       
       int remainder = (int)desiredLength%values.size();
       desiredLength -= remainder; 
       for(int q =0; q<desiredLength; q++)
       {
         String element= values.get(q);
         String[] Templ= element.split(","); 
         double[] doubleLine = new double[Templ.length];
         for(int n =0; n < Templ.length; n++)
         {
             doubleLine[n]=Double.parseDouble(Templ[n]);
         }
         String[] newValues = new String[doubleLine.length];
         String newID = String.valueOf(values.size()+1);
         String newAgg = String.valueOf(doubleLine[1]);
         String newX = String.valueOf(doubleLine[2]+largestX*1.1); 
         String newY = String.valueOf(doubleLine[3]);
         String output = ""; 
         output = output.concat(newID+",");
         output = output.concat(newAgg+",");
         output = output.concat(newX+",");
         output = output.concat(newY);
         values.add(output);
       }
       PrintWriter testFile = new PrintWriter(outputFile);
       StringBuilder sb = new StringBuilder(); 
      // System.out.println(values.get(10));
       for(int x =0; x < values.size()-1; x++)
       {
       sb.append(values.get(x));
       sb.append("\n");
       }
       testFile.write(sb.toString());
       testFile.close(); 
       
       
    }
    
}
