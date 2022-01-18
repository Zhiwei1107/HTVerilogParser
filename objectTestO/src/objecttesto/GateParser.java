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
    
    private boolean debug = false;
    private boolean paramsDebug = false;
    private String[] gateDefinitions;
    private String[][] network;
    private String[] wires;
    private String[] primaryInputs;
    private String[] primaryOutputs;
    private String[][] inouts;
    
    private float LGFIAverage;
    private int[] LGFI;
    
    private int[] FFI;
    private float FFIAverage;
    
    private int[] PI;
    private float PIAverage;
    
    private int[] FFO;
    private float FFOAverage;
    
    private int[] PO;
    private float POAverage;
    
    private String[] trojanNets;
    private boolean nullFlag = false;
    
    private ArrayList<String> FFGates;
    private int largeNum=200000;
    
    public GateParser(String[] GateDefinitions, String[] Wires, String[] PrimaryInputs, String[] PrimaryOutputs,String Trojans) throws IOException{
        FFGates = new ArrayList<String>();
        FFGates.add("DFFNX2");
        FFGates.add("SDFFSRX1");
        FFGates.add("DFFX2");
        FFGates.add("SDFFX1");
        
        gateDefinitions = GateDefinitions;
        
        primaryInputs = PrimaryInputs;
        primaryOutputs = PrimaryOutputs;
        inouts = new String[gateDefinitions.length][2];
        
        
        processGates();
        processTrojanNet(Trojans);
        
        wires = addNotDefinedWiresToWiresArray(Wires,trojanNets);
        trimmer(wires);
        LGFI = new int[wires.length];
        FFI = new int[wires.length];
        PI = new int[wires.length];
        FFO = new int[wires.length];
        PO = new int[wires.length];
        processGates();
//        System.out.println(trojanNets.length);

        calculateLGFI();
        System.out.println("LGFI Done");
        calculateFFI();
        System.out.println("FFI Done");
        calculatePI();
        System.out.println("PI Done");
        calculateFFO();
        System.out.println("FFO Done");
        calculatePO();
        System.out.println("PO Done");
//          System.out.println(getPO(wires[5481]));
//        printVectorsWithWireName();
//        saveInFile("/Users/reza/Desktop/output.txt");
//        printVectors();


 
//        printVectorsForScikit();
//        System.out.println(getFFiOfWire("N690"));f
    }
    
    
    public void trimmer(String[] strArray){
        for(int i=0;i<strArray.length;i++)
            strArray[i]=strArray[i].trim();
    }
    
    
    /**
     * 
     * @description Trojan lines parser 
     */
    
    public void printUniqueTrojanNets(String[] arrString){
        ArrayList<String> temparr = new ArrayList<String>();
        for(int i=0;i<arrString.length;i++)
            if(!temparr.contains(arrString[i].trim()))
                temparr.add(arrString[i].trim());
        System.out.println(temparr.size());
    }
    
    public String[] unifyTrojanNets(String[] arrString){
        ArrayList<String> temparr = new ArrayList<String>();
        
        for(int i=0;i<arrString.length;i++)
            if(!temparr.contains(arrString[i].trim()))
                temparr.add(arrString[i].trim());
        String[] result = new String[temparr.size()];
        return temparr.toArray(result);
    }
    
    public void  processTrojanNet(String lines){
        String Lines = lines.replaceAll("\\s+", " ").trim();
        String[] trojanLines =  Lines.split(";");
        String result="";
        for(int i=0;i<trojanLines.length;i++){
            if(i==0)
                result = parsTrojanLine(trojanLines[i]);
            else 
                result += ","+parsTrojanLine(trojanLines[i]);
        }
        
        trojanNets =  unifyTrojanNets(result.split(","));
        
        
        
    }
    
    public String[] addNotDefinedWiresToWiresArray(String[] Wirs, String[] Trojs){
        String[] total;
        int counter = 0;
        ArrayList<String> notListeds = new ArrayList<String>();
        ArrayList<String> Listeds = new ArrayList<String>();
        for(int i=0;i<Wirs.length;i++){
            for(int j=0;j<Trojs.length;j++)
                if(Wirs[i].trim().equals(Trojs[j].trim()))
                {
                    counter++;
                    notListeds.add(Trojs[j].trim());
                }
        }
//        System.out.println(counter);
        for(int i=0;i<Trojs.length;i++)
            if(!notListeds.contains(Trojs[i]))
                if(!Trojs[i].contains("'"))
                    Listeds.add(Trojs[i]);
            
        total = new String[Wirs.length+Listeds.size()];
        
        for(int i=0;i<Wirs.length;i++)
            total[i] = Wirs[i];
        for(int i=Wirs.length;i<Wirs.length+Listeds.size();i++)
            total[i] = Listeds.get(i-Wirs.length);

        return total;
    }
    
    //trojan definition line  parser
    public String parsTrojanLine(String line){
//        System.out.println(line);
        int loc=0;
        int temp=0;
        String tempstr = "";
        String result="";
        
        loc = line.indexOf("(")+1;
        tempstr = line.substring(loc);
        loc = tempstr.indexOf("(")+1;
        tempstr = tempstr.substring(loc);
        temp = tempstr.indexOf(")");
        result = nullRemover(tempstr.substring(0,temp).trim());
        while((loc = tempstr.indexOf(",")+1)>0){
            tempstr = tempstr.substring(loc);
            loc = tempstr.indexOf("(")+1;
            temp = tempstr.indexOf(")");
            result += ","+nullRemover(tempstr.substring(loc,temp).trim());
        }
        
        return result;
        
    }
    
    //function which checks if a net is used in trojan Gates or no!
    public boolean isInTrojanNet(String inpwire){
        boolean result=false;
        for(int i=0;i<trojanNets.length;i++)
            if(trojanNets[i].trim().equals(inpwire.trim()))
                result=true;
        return result;
    }
    
    
/**
 * @description functions  for printing and saving the  result in a seperate file
 **/    
    
    //only prints the vectors list
    public void printVectors(){
        System.out.println("LGFi,FFi,FFo,PI,PO");
        for(int i=0;i<wires.length;i++)
            System.out.println(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]);
    }
    
    //prints the vectors with their name as firs member in line array
    public void printVectorsWithWireName(){
        System.out.println("Wire Name,\tLGFi,FFi,FFo,PI,PO");
        for(int i=0;i<wires.length;i++)
            System.out.println(wires[i]+",\t"+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]);
    }
    
    //prints the well-suited input for sklearn in python 
    public void printVectorsForScikit(){
        int counter=0;
        String normal="Normal";
        String trojan="Trojan";
        System.out.println("LGFi,FFi,FFo,PI,PO,Class");
        for(int i=0;i<wires.length;i++){
            if(isInTrojanNet(wires[i])){
                System.out.println(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+trojan);
                counter++;
            } else
                System.out.println(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+normal);
        }
        System.out.println(counter);
    }
    
    
    //prints the well-suited input for sklearn in python with names 
    public void printVectorsForScikitWithNames(){
        int counter=0;
        String normal="Normal";
        String trojan="Trojan";
        System.out.println("Name,LGFi,FFi,FFo,PI,PO,Class");
        for(int i=0;i<wires.length;i++){
            if(isInTrojanNet(wires[i])){
                System.out.println(wires[i]+","+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+trojan);
                counter++;
            } else
                System.out.println(wires[i]+","+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+normal);
        }
        System.out.println(counter);
    }
    
    //saves the vector in a file where you choose
    public void saveInFile(String path) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path)));
        for(int i=0;i<wires.length;i++)
           bw.append(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+"\n");
        bw.flush();
        bw.close();
    }
    
    //saves the vector in a file where you choose with name of the wire
    public void saveInFileWithNames(String path) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path)));
        for(int i=0;i<wires.length;i++)
           bw.append(wires[i]+","+LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+"\n");
        bw.flush();
        bw.close();
    }
    
    public void saveInFileForScikit(String path,boolean append) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path),append));
        String normal="Normal";
        String trojan="Trojan";
        if(!append)
            bw.append("LGFi,FFi,FFo,PI,PO,Class\n");
        for(int i=0;i<wires.length;i++){
            if(isInTrojanNet(wires[i])){
                bw.append(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+trojan+"\n");
            } else
               bw.append(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+normal+"\n");
        }
        bw.flush();
        bw.close();
    }
    
    
    public void saveInFileForScikit(String path, String path1,boolean append) throws IOException{
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path),append));
        BufferedWriter bw1  =  new BufferedWriter(new FileWriter(new File(path1),append));
        String normal="Normal";
        String trojan="Trojan";
        ArrayList<String> normals = new  ArrayList<String>();
        ArrayList<String> trojans = new  ArrayList<String>();
//        if(!append)
//            bw.append("LGFi,FFi,FFo,PI,PO,Class\n");
        for(int i=0;i<wires.length;i++){
            if(isInTrojanNet(wires[i])){
                if(!trojans.contains(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+trojan))
                    trojans.add(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+trojan);
            } else{
                if(!normals.contains(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+normal))
                    normals.add(LGFI[i]+","+FFI[i]+","+FFO[i]+","+PI[i]+","+PO[i]+","+normal);
            }
        }


        for(int i=0;i<normals.size();i++)
                bw.append(normals.get(i)+"\n");
        for(int i=0;i<trojans.size();i++)
                bw1.append(trojans.get(i)+"\n");      

        bw.flush();
        bw.close();
        bw1.flush();
        bw1.close();
    }
    
    
/**
 * @description This part of code is calculating FFi and FFo parameter
 * dedicated to functions of FFi and FFo
 **/ 
    
    public void calculateFFI(){
        int total=0;
        for(int i=0;i<wires.length;i++){
//            System.out.println(wires[i]);
            FFI[i]=getFFiOfWire(wires[i].trim());
            total+=FFI[i];
            if(paramsDebug)
                System.out.println("FFI("+wires[i]+")\t"+FFI[i]);
        }
        FFIAverage = (float) total/wires.length;
        
    }
    
    public int getFFiOfWire(String wire){
//        if(getInputGates(wire)!=null)
//            System.out.println("nootnull");
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
            result = largeNum;
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
            {
                if(FFGates.contains(getGateType(gateDefinitions[inputgates[i]]).trim().toUpperCase()))
                    result=true;
//                System.out.println(gateDefinitions[inputgates[i]]);
            }
       /****imp****/ if(inputgates==null){
                        result=true;
                        nullFlag = true;
                     }
//       System.out.println(result);
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
//            System.out.println(i);
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
        int[] firstGates = GateNumber;
        ArrayList<Integer> nextGates = new ArrayList<Integer>();
        int repeatCounter=0;
        temp = GateNumber;
        while(flag){
            
            if(checkFFGateForOutput(temp))
                        flag = false;
            else{
                result++;
                temp = getNextGates(temp);
                if(temp!=null)
                if(temp.length==1){
                    if(firstGates.length==1)
                    {
                        if(!nextGates.contains(temp[0]))
                            nextGates.add(temp[0]);
                        else repeatCounter++;
                        if(repeatCounter>wires.length)
                        {
                            flag=false;
                            nullFlag=true;
                        }
                        
                    }
                }
            }
                
        }
        if(nullFlag)
        {
            result = largeNum;
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
                if(FFGates.contains(getGateType(gateDefinitions[inputgates[i]]).trim().toUpperCase()))
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
//            System.out.println(i);
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
            result = largeNum;
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
    
    public boolean isPrimaryInput(String input){
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
//            System.out.println();
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
//        System.out.println(inpgates[0]);
//        System.out.println(gateDefinitions[5332]);
        result = getPOForGate(inpgates);
        return result;
    }
    
    public int getPOForGate(int[] GateNumber){
        int result = 1;
        boolean flag=true;
        int[] temp;
        int[] firstGates = GateNumber;
        ArrayList<Integer> nextGates = new  ArrayList<Integer>();
        int repeatCounter = 0;
        temp = GateNumber;
//        System.out.println(firstGates.length);
        while(flag){
            
            if(checkPrimaryOutputForInput(temp))
                        flag = false;
            else{
                result++;
                temp = getNextGates(temp);
//                System.out.println(temp.length+"  "+repeatCounter);
                if(temp!=null)
                if(temp.length==1){
                    if(firstGates!=null)
                    if(firstGates.length==1 | firstGates.length==2)
                    {
                        if(!nextGates.contains(temp[0]))
                            nextGates.add(temp[0]);
                        else repeatCounter++;
                        if(repeatCounter>wires.length)
                        {
                            flag=false;
                            nullFlag=true;
                        }
                        
                    }
                }
            }
                
        }
        
        if(nullFlag)
        {
            result = largeNum;
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
    
    public boolean isPrimaryOutput(String input){
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
            case("LSDNENX1"):
                result = 2;
                break;

            case("SDFFX1"):
                result = 4;
                break;

            case("NAND2X0"):
                result = 2;
                break;

            case("NAND3X0"):
                result = 3;
                break;

            case("AO222X1"):
                result = 6;
                break;

            case("AND4X1"):
                result = 4;
                break;

            case("NAND4X0"):
                result = 4;
                break;

            case("AOI222X1"):
                result = 6;
                break;

            case("INVX0"):
                result = 1;
                break;

            case("AO22X1"):
                result = 4;
                break;

            case("AO221X1"):
                result = 5;
                break;

            case("OA22X1"):
                result = 4;
                break;

            case("ISOLANDX1"):
                result = 2;
                break;

            case("NOR2X0"):
                result = 2;
                break;

            case("XOR3X1"):
                result = 3;
                break;

            case("XNOR3X1"):
                result = 3;
                break;

            case("XNOR2X1"):
                result = 2;
                break;

            case("NOR4X0"):
                result = 4;
                break;

            case("AO21X1"):
                result = 3;
                break;

            case("AND3X1"):
                result = 3;
                break;

            case("OA21X1"):
                result = 3;
                break;

            case("OA221X1"):
                result = 5;
                break;

            case("NOR3X0"):
                result = 3;
                break;

            case("NBUFFX2"):
                result = 1;
                break;

            case("AOI221X1"):
                result = 5;
                break;

            case("OAI22X1"):
                result = 4;
                break;

            case("OR3X1"):
                result = 3;
                break;

            case("OA222X1"):
                result = 6;
                break;

            case("AND2X2"):
                result = 2;
                break;

            case("DFFNX2"):
                result = 2;
                break;

            case("LSDNX1"):
                result = 1;
                break;

            case("NOR3X1"):
                result = 3;
                break;

            case("NOR2X2"):
                result = 2;
                break;

            case("NOR4X1"):
                result = 4;
                break;

            case("OAI221X1"):
                result = 5;
                break;

            case("OAI222X1"):
                result = 6;
                break;

            case("INVX8"):
                result = 1;
                break;

            case("MUX21X2"):
                result = 3;
                break;

            case("DFFX2"):
                result = 2;
                break;

            case("AND3X4"):
                result = 3;
                break;

            case("MUX21X1"):
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
        for(int i=0;i<inouts.length;i++){
            if(checkForStringWithSplit(inouts[i][1], WireName)){
                if(counter==0){
                    counter++;
                    result = Integer.toString(i);
                }
                else result += ","+i;
             }
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
        if(input!=null){
            if(input.contains(",")){
                String[] Array = input.split(",");
                for(int i=0;i<Array.length;i++)
                    if(Array[i].trim().equals(wire.trim()))
                        return true;
            } else {
                if(input.trim().equals(wire.trim()))
                    return true;
            }
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
                case("LSDNENX1"):
                    result = getInputOutputs(line,2,1);
                    break;
                
                case("SDFFX1"):
                    result = getInputOutputs(line,4,2);
                    break;
                    
                case("NAND2X0"):
                    result = getInputOutputs(line,2,1);
                    break;
                    
                case("NAND3X0"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("AO222X1"):
                    result = getInputOutputs(line,6,1);
                    break;
                
                case("AND4X1"):
                    result = getInputOutputs(line,4,1);
                    break;
                
                case("NAND4X0"):
                    result = getInputOutputs(line,4,1);
                    break;
                
                case("AOI222X1"):
                    result = getInputOutputs(line,6,1);
                    break;
                    
                case("INVX0"):
                    result = getInputOutputs(line,1,1);
                    break;
                    
                case("AO22X1"):
                    result = getInputOutputs(line,4,1);
                    break;
                    
                case("AO221X1"):
                    result = getInputOutputs(line,5,1);
                    break;
                    
                case("OA22X1"):
                    result = getInputOutputs(line,4,1);
                    break;
                    
                case("ISOLANDX1"):
                    result = getInputOutputs(line,2,1);
                    break;
                    
                case("NOR2X0"):
                    result = getInputOutputs(line,2,1);
                    break;
                    
                case("XOR3X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("XNOR3X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("XNOR2X1"):
                    result = getInputOutputs(line,2,1);
                    break;
                    
                case("NOR4X0"):
                    result = getInputOutputs(line,4,1);
                    break;
                    
                case("AO21X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("AND3X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("OA21X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("OA221X1"):
                    result = getInputOutputs(line,5,1);
                    break;
                    
                case("NOR3X0"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("NBUFFX2"):
                    result = getInputOutputs(line,1,1);
                    break;
                    
                case("AOI221X1"):
                    result = getInputOutputs(line,5,1);
                    break;
                    
                case("OAI22X1"):
                    result = getInputOutputs(line,4,1);
                    break;
                    
                case("OR3X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("OA222X1"):
                    result = getInputOutputs(line,6,1);
                    break;
                    
                case("AND2X2"):
                    result = getInputOutputs(line,2,2);
                    break;
                    
                case("DFFNX2"):
                    result = getInputOutputs(line,2,2);
                    break;
                    
                case("LSDNX1"):
                    result = getInputOutputs(line,1,1);
                    break;
                    
                case("NOR3X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("NOR2X2"):
                    result = getInputOutputs(line,2,1);
                    break;
                    
                case("NOR4X1"):
                    result = getInputOutputs(line,4,1);
                    break;
                    
                case("OAI221X1"):
                    result = getInputOutputs(line,5,1);
                    break;
                    
                case("OAI222X1"):
                    result = getInputOutputs(line,6,1);
                    break;
                    
                case("INVX8"):
                    result = getInputOutputs(line,1,1);
                    break;
                    
                case("MUX21X2"):
                    result = getInputOutputs(line,3,2);
                    break;
                    
                case("DFFX2"):
                    result = getInputOutputs(line,2,2);
                    break;
                    
                case("AND3X4"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                case("MUX21X1"):
                    result = getInputOutputs(line,3,1);
                    break;
                    
                

            }
        
        return result;      
    }
    
    public String[] getInputOutputs(String line, int  inpnum, int outnum){
        String[] result = new  String[2];
        int loc=0;
        int temp=0;
        String tempstr = "";
        
        loc = line.indexOf("(")+1;
        tempstr = line.substring(loc);
        loc = tempstr.indexOf("(")+1;
        tempstr = tempstr.substring(loc);
        temp = tempstr.indexOf(")");
        result[0] = nullRemover(tempstr.substring(0,temp).trim());
        if(inpnum>1){
            for(int i=0;i<inpnum-1;i++){
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[0] += ","+nullRemover(tempstr.substring(loc,temp).trim());
            }
        }
        
        loc = tempstr.indexOf(",")+1;
        tempstr = tempstr.substring(loc);
        loc = tempstr.indexOf("(")+1;
        temp = tempstr.indexOf(")");
        result[1] = nullRemover(tempstr.substring(loc,temp).trim());
        if(outnum>1){
            for(int j=0;j<outnum-1;j++)
                if(!(tempstr.indexOf(",")<0)){
                    loc = tempstr.indexOf(",")+1;
                    tempstr = tempstr.substring(loc);
                    loc = tempstr.indexOf("(")+1;
                    temp = tempstr.indexOf(")");
                    result[1] += ","+nullRemover(tempstr.substring(loc,temp).trim());
                }
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
//        if(input.contains("'"))
//            result = "";
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
