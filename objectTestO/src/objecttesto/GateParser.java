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
    
    private static String[] gateDefinitions;
    private static String[][] network;
    private static String[] wires;
    private static String[] primaryInputs;
    private static String[] primaryOutputs;
    private static String[][] inouts;
    
    
    public GateParser(String[] GateDefinitions, String[] Wires, String[] PrimaryInputs, String[] PrimaryOutputs){
        gateDefinitions = GateDefinitions;
        wires = Wires;
        primaryInputs = PrimaryInputs;
        primaryOutputs = PrimaryOutputs;
        inouts = new String[gateDefinitions.length][2];
        processGates();
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
        processGateInOut(" SDFFSRX1 iXMIT_xmit_ShiftRegH_reg_0_ ( .D(n190), .SI(iXMIT_state_2_), .SE(        test_se), .CK(sys_clk), .SN(1'b1), .RN(n266), .Q(n281), .QN(n257) )");
    }
    
    public void processInOuts(){
        for(int i=0;i<gateDefinitions.length;i++)
        {
            switch(gateDefinitions[i].split(" ")[0])
            {
                case "BUFX1":
                    
                case "OAI21X1":
                case "AOI21X1":
                case "INVX1":
                case "AOI22X1":
                case "NOR2X1":
                case "NAND3X1":
                case "OR4X1":
                case "AND2X1":
                case "NAND4X1":
                case "OR2X1":
                case "NAND2X1":
                case "XOR2X1":
                case "SDFFSRX1":
                case "MX2X1":

            }
        }
        
    
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
                loc = tempstr.indexOf(",")+1;
                tempstr = tempstr.substring(loc);
                loc = tempstr.indexOf("(")+1;
                temp = tempstr.indexOf(")");
                result[1] = ","+nullRemover(tempstr.substring(loc,temp).trim());
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
                System.out.println(result[0]+"\t"+result[1]);
                break;
                

            }
        
        return result;      
    }
    
    public String nullRemover(String input){
        String result = "";
        if(input.indexOf("null")==0){
                    result = input.split("null")[1];
                    return result;
        }
        return input;
    }
    
}
