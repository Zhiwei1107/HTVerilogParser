/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objecttesto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultTreeSelectionModel;

/**
 *
 * @author reza
 */
public class GateParser {
    
    private static boolean debug = false;
    private static boolean paramsDebug = false;
    private static String[] gateDefinitions;
    private static String[][] network;
    private static String[] wires;
    private static String[] primaryInputs;
    private static String[] primaryOutputs;
    private static String[][] inouts;
    
    private static float LGFIAverage;
    private static int[] LGFI;
    
    private static int[] FFI;
    private static float FFIAverage;
    
    private static int[] PI;
    private static float PIAverage;
    
    private static int[] FFO;
    private static float FFOAverage;
    
    private static int[] PO;
    private static float POAverage;
    
    private static boolean nullFlag = false;
    
    
    public GateParser(String[] GateDefinitions, String[] Wires, String[] PrimaryInputs, String[] PrimaryOutputs) throws IOException{
        gateDefinitions = GateDefinitions;
        wires = Wires;
        primaryInputs = PrimaryInputs;
        primaryOutputs = PrimaryOutputs;
        inouts = new String[gateDefinitions.length][2];
        LGFI = new int[wires.length];
        FFI = new int[wires.length];
        PI = new int[wires.length];
        
        FFO = new int[wires.length];
        PO = new int[wires.length];
        
        processGates();
        calculateLGFI();
        calculateFFI();
        calculatePI();
        calculateFFO();
        calculatePO();
//        printVectorsWithWireName();
        saveInFile("/Users/reza/Desktop/output.txt");
    }
   
    
/**
 * @description functions  for printing and saving the  result in a seperate file
 **/    
    
    //only prints the vectors list
    public static void printVectors(){
        System.out.println("LGFi,FFi,FFo,PI,PO");
        for(int i=0;i<wires.length;i++)
            System.out.println(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]);
    }
    
    //prints the vectors with their name as firs member in line array
    public static void printVectorsWithWireName(){
        System.out.println("Wire Name,\tLGFi,FFi,FFo,PI,PO");
        for(int i=0;i<wires.length;i++)
            System.out.println(wires[i]+",\t"+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]);
    }
    
    //saves the vector in a file where you choose
    public static void saveInFile(String path) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path)));
        for(int i=0;i<wires.length;i++)
           bw.append(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+"\n");
        bw.flush();
        bw.close();
    }
    
    //saves the vector in a file where you choose with name of the wire
    public static void saveInFileWithNames(String path) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path)));
        for(int i=0;i<wires.length;i++)
           bw.append(wires[i]+","+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+"\n");
        bw.flush();
        bw.close();
    }
    
    
    
/**
 * @description This part of code is calculating FFi and FFo parameter
 * dedicated to functions of FFi and FFo
 **/ 
    
    public void calculateFFI(){
        int total=0;
        for(int i=0;i<wires.length;i++){
            FFI[i]=getFFiOfWire(wires[i].trim());
            total+=FFI[i];
            if(paramsDebug)
                System.out.println("FFI("+wires[i]+")\t"+FFI[i]);
        }
        FFIAverage = (float) total/wires.length;
        
    }
    
    public int getFFiOfWire(String wire){
        return getFFI(getInputGates(wire));
    }
//    public int getFFI(String[] wires){
//        int result = 0;
//        boolean flag=true;
//        int[] temp;
//        String[] currentWire = wires;
//        while(flag){
//            for(int i=0;i<currentWire.length;i++){
//                temp = getInputGates(currentWire[i]);
//                if(checkFFGateForInput(temp))
//                    flag = false;
//            }
//            if(result == 0){
//                result++;
//                for(int )
//            }
//                
//        }
//        return result;
//    }
    
    public int getFFI(int[] GateNumber){
        int result = 0;
        boolean flag=true;
        int[] temp;
        temp = GateNumber;
        while(flag){
            
            if(checkFFGateForInput(temp))
                        flag = false;
            else{
                result++;
                temp = getPreviousGates(temp);
            }
                
        }
        if(nullFlag)
        {
            result = 1000;
            nullFlag  = false;
        }
        return result;
    }
    
    public int[] getPreviousGates(int GateNumber){
        int[] results;
        String temp="";
        String result="";
        int  counter=0;
        String[] inputwires = inouts[GateNumber][0].split(",");
        for(int i=0;i<inputwires.length;i++){
            temp=getInputGatesString(inputwires[i]);
            if(temp.length()>0)
                if(counter==0){
                        counter++;
                        result=temp;
                    }
                else result+=","+temp;
        }
        String[] tempres = unify(result.split(","));
        
        return getIntArray(tempres);
            
    }
    
    public int[] getPreviousGates(int[] GateNumber){
        int[] results;
        String temp="";
        String result="";
        int  counter=0;
        if(GateNumber!=null){
        for(int j=0;j<GateNumber.length;j++){
        String[] inputwires = inouts[GateNumber[j]][0].split(",");
            for(int i=0;i<inputwires.length;i++){
                temp=getInputGatesString(inputwires[i]);
                if(temp.length()>0)
                    if(counter==0){
                        counter++;
                        result=temp;
                    }
                    else result+=","+temp;
            }
        }
        String[] tempres = unify(result.split(","));
        
        return getIntArray(tempres);
        }
        return null;
    }
    
    public int[] getIntArray(String[] array){
        
        int[] result = new int[array.length];
        for(int i=0;i<array.length;i++){
           if(array[i].length()>0)
            result[i]=Integer.parseInt(array[i].trim());
        }
        return result;
    }
    
    public int[] getIntArray(String string){
        String[] array = string.split(",");
        int[] result = new int[array.length];
        for(int i=0;i<array.length;i++)
            result[i]=Integer.parseInt(array[i].trim());
        return result;
    }
    
    
    public String[] unify(String[] strings){
        ArrayList<String> list  = new ArrayList<String>();
        for(int i=0;i<strings.length;i++)
            if(!list.contains(strings[i]))
                list.add(strings[i]);
        String[] result = new String[list.size()];
        result = list.toArray(result);
        return result;
    }
    
    public boolean checkFFGateForInput(int[] inputgates){
        boolean result = false;
        if(inputgates!=null)
            for(int i=0;i<inputgates.length;i++)
                if(getGateType(gateDefinitions[inputgates[i]]).equals("SDFFSRX1"))
                    result=true;
       /****imp****/ if(inputgates==null){
                        result=true;
                        nullFlag = true;
                     }
        return result;
    }
    
    
//    public void calculateFFO(){
//        
//    }
//    
//    public int getFFO(String wire){
//        int result = 0;
//        
//        return result;
//    
//    }
    
    public void calculateFFO(){
        int total=0;
        for(int i=0;i<wires.length;i++){
            FFO[i]=getFFoOfWire(wires[i].trim());
            total+=FFO[i];
            if(paramsDebug)
                System.out.println("FFO("+wires[i]+")\t"+FFO[i]);
        }
        FFOAverage = (float) total/wires.length;
        
    }
    
    public int getFFoOfWire(String wire){
        return getFFO(getOutputGates(wire));
    }
    
    public int getFFO(int[] GateNumber){
        int result = 0;
        boolean flag=true;
        int[] temp;
        temp = GateNumber;
        while(flag){
            
            if(checkFFGateForOutput(temp))
                        flag = false;
            else{
                result++;
                temp = getNextGates(temp);
            }
                
        }
        if(nullFlag)
        {
            result = 1000;
            nullFlag  = false;
        }
        return result;
    }
    
    public int[] getNextGates(int GateNumber){
        int[] results;
        String temp="";
        String result="";
        int  counter=0;
        String[] outputwires = inouts[GateNumber][1].split(",");
        for(int i=0;i<outputwires.length;i++){
            temp=getOutputGatesString(outputwires[i]);
            if(temp.length()>0)
                if(counter==0){
                        counter++;
                        result=temp;
                    }
                else result+=","+temp;
        }
        String[] tempres = unify(result.split(","));
        
        return getIntArray(tempres);
            
    }
    
    public int[] getNextGates(int[] GateNumber){
        int[] results;
        String temp="";
        String result="";
        int  counter=0;
        if(GateNumber!=null){
        for(int j=0;j<GateNumber.length;j++){
        String[] inputwires = inouts[GateNumber[j]][1].split(",");
            for(int i=0;i<inputwires.length;i++){
                temp=getOutputGatesString(inputwires[i]);
                if(temp.length()>0)
                    if(counter==0){
                        counter++;
                        result=temp;
                    }
                    else result+=","+temp;
            }
        }
        if(result!=""){
        String[] tempres = unify(result.split(","));
        
        return getIntArray(tempres);
        }
        }
        return null;
    }
    
    public boolean checkFFGateForOutput(int[] inputgates){
        boolean result = false;
        if(inputgates!=null)
            for(int i=0;i<inputgates.length;i++)
                if(getGateType(gateDefinitions[inputgates[i]]).equals("SDFFSRX1"))
                    result=true;
       /****imp****/ if(inputgates==null){
                        result=true;
                        nullFlag = true;
                     }
        return result;
    }
    
    public String getGateType(String line){ return line.trim().split(" ")[0]; }
    
/**
 * @description This part of code is calculating PI and PO parameter
 * dedicated to functions of FFi and FFo
 **/    
    
    public void calculatePI(){
        int total=0;
        for(int i=0;i<wires.length;i++){
            PI[i]  = getPI(wires[i]);
            total+=PI[i];
            if(paramsDebug)
                System.out.println("PI("+wires[i]+")\t"+PI[i]);
        }
        PIAverage = (float) total/wires.length;
        
    }
    
    public int getPI(String wire){
        int result = 0;
        int[] inpgates = getInputGates(wire);
        result = getPIForGate(inpgates);
        return result;
    }
    
    public int getPIForGate(int[] GateNumber){
        int result = 1;
        boolean flag=true;
        int[] temp;
        temp = GateNumber;
        while(flag){
            
            if(checkPrimaryInputForInput(temp))
                        flag = false;
            else{
                result++;
                temp = getPreviousGates(temp);
            }
                
        }
        if(nullFlag)
        {
            result = 1000;
            nullFlag  = false;
        }
        return result;
    }
    
    public boolean checkPrimaryInputForInput(int[] inputgates){
        boolean result = false;
        String[] temp;
        if(inputgates!=null)
            for(int i=0;i<inputgates.length;i++)
            {
                temp = inouts[inputgates[i]][0].split(",");
                for(int j=0;j<temp.length;j++)
                    if(isPrimaryInput(temp[j].trim()))
                        result=true;
            }
//                if(getGateType(gateDefinitions[inputgates[i]]).equals("SDFFSRX1"))
//                    result=true;
       /****imp****/ if(inputgates==null){
                        result=true;
                        nullFlag = true;
                     }
        return result;
    }
    
    public static boolean isPrimaryInput(String input){
        boolean result=false;
        for(int i=0;i<primaryInputs.length;i++)
            if(primaryInputs[i].trim().equals(input.trim()))
                result=true;
        return result;
    }
    
    
    
    //PO  part!
    
    public void calculatePO(){
        int total=0;
        for(int i=0;i<wires.length;i++){
            PO[i]  = getPO(wires[i]);
            total+=PO[i];
            if(paramsDebug)
                System.out.println("PO("+wires[i]+")\t"+PO[i]);
        }
        POAverage = (float) total/wires.length;
        
    }
    
    public int getPO(String wire){
        int result = 0;
        int[] inpgates = getOutputGates(wire);
        result = getPOForGate(inpgates);
        return result;
    }
    
    public int getPOForGate(int[] GateNumber){
        int result = 1;
        boolean flag=true;
        int[] temp;
        temp = GateNumber;
        while(flag){
            
            if(checkPrimaryOutputForInput(temp))
                        flag = false;
            else{
                result++;
                temp = getNextGates(temp);
            }
                
        }
        if(nullFlag)
        {
            result = 1000;
            nullFlag  = false;
        }
        return result;
    }
    public boolean checkPrimaryOutputForInput(int[] inputgates){
        boolean result = false;
        String[] temp;
        if(inputgates!=null)
            for(int i=0;i<inputgates.length;i++)
            {
                temp = inouts[inputgates[i]][1].split(",");
                for(int j=0;j<temp.length;j++)
                    if(isPrimaryOutput(temp[j].trim()))
                        result=true;
            }
        
        if(inputgates==null){
            result=true;
            nullFlag = true;
        }
        return result;
    }
    
    public static boolean isPrimaryOutput(String input){
        boolean result=false;
        for(int i=0;i<primaryOutputs.length;i++)
            if(primaryOutputs[i].trim().equals(input.trim()))
                result=true;
        return result;
    }
    
    
/**
 * @description This part of code is calculating LGFI parameter
 * dedicated to functions of LGFI
 * 
 **/    
    
    public void calculateLGFI(){
        int total = 0;
        for(int i=0;i<wires.length;i++){
            LGFI[i]=getLGFi(wires[i]);
            if(paramsDebug)
                System.out.println("LGFI("+wires[i]+")\t"+LGFI[i]);
            total+=LGFI[i];
        }
        LGFIAverage  = (float)total/wires.length;

    }
    
    //calculate Logic Gate Fan in(LGFi) for a  wire
    public int getLGFi(String Wire){
        int lgfi = 0;
        int[] inputs = getInputGates(Wire);
        String firstLevelGateInputs= "";
        String[] tempInputs;
        int[] secondLevelGates;
        if(inputs!=null)
        {
            for(int i=0;i<inputs.length;i++){
//                System.out.println(inputs[i]+"\t"+gateDefinitions[inputs[i]]);
                firstLevelGateInputs = inouts[inputs[i]][0];
                if(firstLevelGateInputs.contains(",")){
                    tempInputs = firstLevelGateInputs.split(",");
                    for(int k=0;k<tempInputs.length;k++)
                    {
                        secondLevelGates = getInputGates(tempInputs[k]);
                        lgfi+=getGateInputsCount(secondLevelGates);
                    }
                } else if(firstLevelGateInputs.length()>0){
                        secondLevelGates = getInputGates(firstLevelGateInputs);
                        lgfi+=getGateInputsCount(secondLevelGates);
                }
            }
                
        }
        return lgfi;    
    }
    
/**
 *@description Supportive functions of  LGFi calculations
 *@getGateInputsCount calculates the gate inputs based on the first word of the line
 * this function is used in calculation  of LGFi parameter
 **/
    
    public int getGateInputsCount(int[] lines){
        int result = 0 ;
        if(!(lines==null))
            for(int i=0;i<lines.length;i++)
                result+=getGateInputsCount(lines[i]);
        return  result;
    
    }
    
    public int getGateInputsCount(int lineNumber){
        int result = 0;
        switch(gateDefinitions[lineNumber].trim().split(" ")[0]){
            case "BUFX1":
                result = 1;
                break;
            case "OAI21X1":
                result = 3;
                break;
            case "AOI21X1":
                result = 3;
                break;
            case "INVX1":
                result = 1;
                break;
            case "AOI22X1":
                result = 4;
                break;
            case "NOR2X1":
                result = 2;
                break;
            case "NAND3X1":
                result = 3;
                break;
            case "OR4X1":
                result = 4;
                break;
            case "AND2X1":
                result = 2;
                break;
            case "NAND4X1":
                result = 4;
                break;
            case "OR2X1":
                result = 2;
                break;
            case "NAND2X1":
                result = 2;
                break;
            case "XOR2X1":
                result = 2;
                break;
            case "SDFFSRX1":
                result = 5;
                break;
            case "MX2X1":
                result = 3;
                break;
        }
        return result;
    }
    
 /**
 *@description functions for traversing the linked list of the gates
 * backward and forward functions are made up
 *@getInputGates lists the gates that current wire is output of them
 *@getOutputGates lists the gates that current wire is input of them
 * 
 **/   
    
    public int[] getOutputGates(String WireName){
        String result = "";
        int counter = 0;
        for(int i=0;i<inouts.length;i++)
            if(checkForStringWithSplit(inouts[i][0], WireName)){
                if(counter==0){
                    counter++;
                    result = Integer.toString(i);
                }
                else result += ","+i;
             }
        String[] strings;
        int[] linenumbers;
        
        if(result.contains(",")){
            strings = result.split(",");
            linenumbers = new int[strings.length];
            for(int i=0;i<strings.length;i++)
                linenumbers[i] = Integer.parseInt(strings[i]);
            return  linenumbers;
        } else if(result.length()>0){
            linenumbers = new int[1];
            linenumbers[0]  =  Integer.parseInt(result);
            return linenumbers;
        } 
        return null;
    
    }
    
    public int[] getInputGates(String WireName){
        String result = "";
        int counter = 0;
        for(int i=0;i<inouts.length;i++)
            if(checkForStringWithSplit(inouts[i][1], WireName)){
                if(counter==0){
                    counter++;
                    result = Integer.toString(i);
                }
                else result += ","+i;
             }
        if(debug)
            System.out.println(result);
        String[] strings;
        int[] linenumbers;
        
        if(result.contains(",")){
            strings = result.split(",");
            linenumbers = new int[strings.length];
            for(int i=0;i<strings.length;i++)
                linenumbers[i] = Integer.parseInt(strings[i]);
            return  linenumbers;
        } else if(result.length()>0){
            linenumbers = new int[1];
            linenumbers[0]  =  Integer.parseInt(result);
            return linenumbers;
        } 
        return null;
    }
    
    public String getOutputGatesString(String WireName){
        String result = "";
        int counter = 0;
        for(int i=0;i<inouts.length;i++)
            if(checkForStringWithSplit(inouts[i][0], WireName)){
                if(counter==0){
                    counter++;
                    result = Integer.toString(i);
                }
                else result += ","+i;
             }
        if(debug)
            System.out.println(result);
        String[] strings;
        int[] linenumbers;
        
        return result;
    }
    
    public String getInputGatesString(String WireName){
        String result = "";
        int counter = 0;
        for(int i=0;i<inouts.length;i++)
            if(checkForStringWithSplit(inouts[i][1], WireName)){
                if(counter==0){
                    counter++;
                    result = Integer.toString(i);
                }
                else result += ","+i;
             }
        if(debug)
            System.out.println(result);
        String[] strings;
        int[] linenumbers;
        
        return result;
    }
    
    public boolean checkForStringWithSplit(String input, String wire){
        if(input.contains(",")){
            String[] Array = input.split(",");
            for(int i=0;i<Array.length;i++)
                if(Array[i].trim().equals(wire.trim()))
                    return true;
        } else {
            if(input.trim().equals(wire.trim()))
                return true;
        }
        return false;
    }
    
    
    
    /**
    *
    * @description 
    * get gate definitions and  inputs and outputs
    * extract all inputs and outputs for every item -> save in inouts
    * extract connections with other items on input and outputs -> save input,GateNumber -> network (Something like linkedList)
    * 
    **/
    
    public void processGates(){
        processInOuts();
    }
    
    public void processInOuts(){
        for(int i=0;i<gateDefinitions.length;i++)
            inouts[i] =  processGateInOut(gateDefinitions[i]);
          
    }
    
    
    public String[] processGateInOut(String GateLine){
        String[] result = new String[2];
        String  line =  GateLine.trim();
        int loc=0;
        int temp=0;
        String tempstr = "";
        switch(line.split(" ")[0])
        {
            case "BUFX1":    
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",");
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "OAI21X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] += tempstr.substring(loc,temp).trim();
                result[1] = nullRemover(result[1]);
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "AOI21X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] += tempstr.substring(loc,temp).trim();
                result[1] = nullRemover(result[1]);
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "INVX1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",");
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "AOI22X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "NOR2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "NAND3X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] += tempstr.substring(loc,temp).trim();
                result[1] = nullRemover(result[1]);
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "OR4X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "AND2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
                
            case "NAND4X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
                
            case "OR2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "NAND2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "XOR2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
            case "SDFFSRX1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = nullRemover(tempstr.substring(loc,temp).trim());
                if(!(tempstr.indexOf(",")<0)){
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                }
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                
                
            case "MX2X1":
                loc = line.indexOf("(")+1;
                tempstr = line.substring(loc);
                loc = tempstr.indexOf("(")+1;
                tempstr = tempstr.substring(loc);
                temp = tempstr.indexOf(")");
                result[0] = nullRemover(tempstr.substring(0,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] += tempstr.substring(loc,temp).trim();
                result[1] = nullRemover(result[1]);
                if(debug)
                    System.out.println(result[0]+"\t"+result[1]);
                break;
                

            }
        
        return result;      
    }
    
    public String nullRemover(String input){
        String result = input;
        if(input.indexOf("null")==0){
            result = input.split("null")[1];
        }
        if(input.indexOf("[")>=0){
            result = input.substring(0,input.indexOf("["));
        }
        return result;
    }
    
    public boolean isItReallyWire(String name){
        for(int i=0;i<wires.length;i++)
            if(wires[i].trim().equals(name.trim()))
                return true;
    
        return false;
    }
    public void getAllInOutsList(){
        
        
    }
    
    
    //Verilog File parser
    public void parseVerilogFile(){
        
        
    }
    
}
