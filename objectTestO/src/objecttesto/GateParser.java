/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objecttesto;

import javax.swing.tree.DefaultTreeSelectionModel;

/**
 *
 * @author reza
 */
public class GateParser {
    
    private static boolean debug = false;
    private static String[] gateDefinitions;
    private static String[][] network;
    private static String[] wires;
    private static String[] primaryInputs;
    private static String[] primaryOutputs;
    private static String[][] inouts;
    private static float LGFIAverage;
    private static int[] LGFI;
    
    public GateParser(String[] GateDefinitions, String[] Wires, String[] PrimaryInputs, String[] PrimaryOutputs){
        gateDefinitions = GateDefinitions;
        wires = Wires;
        primaryInputs = PrimaryInputs;
        primaryOutputs = PrimaryOutputs;
        inouts = new String[gateDefinitions.length][2];
        LGFI = new int[wires.length];
        processGates();
        calculateLGFI();
        
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
            total+=LGFI[i];
        }
        LGFIAverage  = (float)total/wires.length;
        if(debug)
            System.out.println(LGFIAverage);
    }
    
    //calculate Logic Gate Fan in(LGFi) for a  wire
    public int getLGFi(String Wire){
        int lgfi = 0;
        int[] inputs = getIntputGates(Wire);
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
                        secondLevelGates = getIntputGates(tempInputs[k]);
                        lgfi+=getGateInputsCount(secondLevelGates);
                    }
                } else if(firstLevelGateInputs.length()>0){
                        secondLevelGates = getIntputGates(firstLevelGateInputs);
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
    
    public int[] getIntputGates(String WireName){
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
