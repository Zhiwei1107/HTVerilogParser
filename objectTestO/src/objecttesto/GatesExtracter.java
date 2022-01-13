/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objecttesto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static objecttesto.ObjectTestO.initGates;

/**
 *
 * @author reza
 */
public class GatesExtracter {
    
    public ArrayList<String> Gates;
    
    public GatesExtracter(String[] paths) throws FileNotFoundException, IOException{
        Gates = new ArrayList<String>();
        String temp="";
        String mainString="";
        BufferedReader br;
        for(int i=0;i<paths.length;i++){
            br = new BufferedReader(new FileReader(new File(paths[i])));
            while((temp=br.readLine())!=null)
                mainString+=temp.trim();
            parseLines(mainString.split(";"), Gates);
            br.close();
            mainString="";

        }
        for(int j=0;j<Gates.size();j++)
            System.out.println(Gates.get(j));
    }
    
    public static void parseLines(String[] lines, ArrayList a){
        String gateDefinitionsTemp="";
        String tempLine="";
        String lineHead="";
        
        for(int i=0;i<lines.length;i++){
            switch((lineHead=getLineHead(lines[i]))){
                case "input":
                    break;
                case "output":
                    break;
                case "wire":
                    break;
                case "assign":
                    break;
                case "endmodule":
                    break;
                case "module":
                    break;
                case "`timescale":
                    break;
                case "comment":
                    
                    break;
                default: 
                    tempLine = lineHead;
                    break;
                
            }
            if(!a.contains(tempLine.trim().toUpperCase()) && tempLine!="")
                a.add(tempLine.trim().toUpperCase());
        }

    }
    
    public static String getLineHead(String line){ 
        String tempLineHead = line.split(" ")[0].trim();
        if(tempLineHead.indexOf("//")==0)
            tempLineHead="comment";
        if(tempLineHead.indexOf("endmodule")==0)
            tempLineHead="endmodule";
        return tempLineHead;
    }
    
}
