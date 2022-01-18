/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objecttesto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author reza
 */
public class FilesParser {
    
    public String[] paths;
    
    public FilesParser(String pathlist,String outputFilePath) throws IOException{
        paths = pathlist.split(",");
        ArrayList<String> normals = new ArrayList<String>();
        ArrayList<String> trojans = new ArrayList<String>();
        for(int i=0;i<paths.length;i++)
            if(paths[i].contains(".trojan"))
                trojans.add(paths[i]);
            else normals.add(paths[i]);
        for(int i=0;i<normals.size();i++)
            System.out.println(trojans.get(i));
        parseTrojans(trojans,outputFilePath);
        
    }
    
    
    
    public void parseTrojans(ArrayList<String> trojans,String filepath) throws FileNotFoundException, IOException{
        BufferedReader br;
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(filepath),true));
        String temp="";
        for(int j=0;j<16;j++)
        for(int i=0;i<trojans.size();i++){
            br = new BufferedReader(new FileReader(new File(trojans.get(i))));
            while((temp=br.readLine())!=null)
            {  
                temp = temp.replaceAll("\\s+", " ").trim();
                bw.append(temp+"\n");
            }
            temp="";
        
        }
        bw.flush();
        bw.close();
//        br.close();
        
    }
    
    public void parseNormals(){
    
    }
    
}
