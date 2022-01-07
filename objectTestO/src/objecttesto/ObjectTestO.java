/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package objecttesto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 *
 * @author reza
 */
public class ObjectTestO {
    
    private static String[] WiresArray;
    private static String[] WiresCountArray;
    private static int totalWireUsageCount;
    private static String gateLevelNetList;
    private static String[] gatesDefinitionLines;
    private static ArrayList<String> usedGateList;
    private static String[] primaryInputs;
    private static String[] primaryOutputs;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
        // TODO code application logic here
        getWiresUsageStatistics();
        initGates();
        primaryInputs = new String[2];
        primaryOutputs = new String[2];
        GateParser parser = new GateParser(gatesDefinitionLines,WiresArray,primaryInputs,primaryOutputs);
        parser.processInOuts();
    }
    
    public static void getWiresUsageStatistics() throws IOException{
        File f = new File("/Users/reza/Desktop/wires.txt");
        FileReader fr  =  new  FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        
        File f1 = new File("/Users/reza/Desktop/tempnet.txt");
        FileReader fr1  =  new  FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        String temp="";
        String mainTemp = "";
        Boolean loop = false;
        
        while((temp=br.readLine())!=null){
            temp = temp.trim(); // remove leading and trailing whitespace
            temp=temp.replaceAll("\\s+", " ");
            mainTemp += temp;
           

        }        
        
        String[] wires = mainTemp.split(",");
        
        for(int j=0;j<wires.length;j++)
        {
            wires[j] = wires[j].replaceAll("\\s+", "");
        }
        WiresArray = wires;
        temp="";
        mainTemp = "";
        while((temp=br1.readLine())!=null){
            mainTemp += temp;
        } 
        int i=0;
        int iterator = 0;
        int counter = 0;
        String counterMemmory = "";
        gateLevelNetList = mainTemp;
        for (i=0;i<wires.length;i++)
        {
            iterator = ocCounter(mainTemp, wires[i]);
            if(iterator > 0){
//                System.out.println(wires[i]+":"+iterator);
                counterMemmory+=wires[i]+":"+iterator+",";
                
            }
            counter+= iterator;
        }
        WiresCountArray  = counterMemmory.split(",");
        totalWireUsageCount = counter;
        br.close();

    }
    
    public static int ocCounter(String context, String query){
        int number = 0;
        int loc = 0;
        int temp = 0;
        boolean start = false;
        boolean loop  = true;
        while(loop){
            if(start)
                loc = context.indexOf(query,temp+query.length());
            else{
                loc = context.indexOf(query,temp);
                start = true;
            }
            if(loc>=0){
                temp = loc;           
                if(context.charAt(temp-1)=='(' || context.charAt(temp-1)==' ')
                    if(context.charAt(temp+query.length())==')' || context.charAt(temp+query.length())==' ' || context.charAt(temp+query.length())=='[')
                        number++;

            }else{
                loop = false;
            }
        }
        
        return number;
        
    }
    
    public static void printWiresCountArray(){
        for(int i = 0 ; i< WiresCountArray.length; i++)
            System.out.println(WiresCountArray[i]);
    }
    
 
    public static void initGates(){
        
        String[] Gates1 = gateLevelNetList.split(";");

        for(int i = 0; i< Gates1.length;i++)
        {
            Gates1[i] = Gates1[i].trim();
            System.out.println(Gates1[i]);
        }
        
        gatesDefinitionLines = Gates1; 
        
//        System.out.println("\n\n\n\n");
        String temp = "";
        String[] GateNameArray = new String[gatesDefinitionLines.length];
        
        ArrayList<String> GateNameArrayList = new ArrayList<String>();
                
        for(int j = 0; j< gatesDefinitionLines.length;j++){
            
            GateNameArray[j]= gatesDefinitionLines[j].split(" ")[0].trim();
            if(GateNameArrayList.indexOf(GateNameArray[j])<0)
                GateNameArrayList.add(GateNameArray[j]);
            
        }
        
//        for(int k=0; k<GateNameArrayList.size();k++)
//            System.out.println(GateNameArrayList.get(k));
        usedGateList = GateNameArrayList;
        
        
        
    }
    
}
