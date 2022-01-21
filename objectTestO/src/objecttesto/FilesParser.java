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
    public  String basePath="";
    
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
        parseTrojans(normals,outputFilePath);
        
    }
//    
    public FilesParser(String pathlist,String[] benchmarkNames, String outputFilePath) throws IOException{
        makeLearnFilesForEveryUnknown(pathlist,benchmarkNames,outputFilePath);
//        
    }
    
    public void makeLearnFilesForEveryUnknown(String basePath,String[] benchmarkNames,String outputPath) throws FileNotFoundException, IOException{
        boolean  append=true;
        BufferedReader br;
        BufferedWriter bw;
        String temp="";
        ArrayList<String> normals;
        ArrayList<String> trojans;
        int[] counter =new int[benchmarkNames.length];
        
        for(int j=0;j<benchmarkNames.length;j++){
            bw = new BufferedWriter(new FileWriter(new File(outputPath+benchmarkNames[j]+".unk"),append));
            normals = new ArrayList<String>();
            bw.append("LGFI,FFI,FFO,PI,PO,Class\n");
            for(int i=0;i<benchmarkNames.length;i++){
                br = new BufferedReader(new FileReader(new File(basePath+benchmarkNames[i]+".nomral")));
                if(i!=j){
                     while((temp=br.readLine())!=null){
                        temp  =  temp.replaceAll("\\s+", " ").trim().replace("Normal","0");
                        if(!normals.contains(temp)){
                            normals.add(temp);
                            bw.append(temp+"\n");
//                            System.out.println(i);
                        }
                    }
                 } else System.out.println(i);
            }
            bw.flush();
            counter[j]=normals.size();
        }
        
        int count = 2;
        
        for(int j=0;j<benchmarkNames.length;j++){
            bw = new BufferedWriter(new FileWriter(new File(outputPath+benchmarkNames[j]+".unk"),append));
            
            for(int k=0;k<count;k++){
                trojans = new ArrayList<String>();

                for(int i=0;i<benchmarkNames.length;i++){
                    br = new BufferedReader(new FileReader(new File(basePath+benchmarkNames[i]+".trojan")));

                    if(i!=j){
                         while((temp=br.readLine())!=null){
                            temp  =  temp.replaceAll("\\s+", " ").trim().replace("Trojan","1");
                            if(!trojans.contains(temp)){
                                trojans.add(temp);
                                bw.append(temp+"\n");
                            }
                        }
                     }
                }
                if(k==0)
                    count = counter[j]/trojans.size();
            }
            bw.flush();
        }
        
    }
    
    
    public void balanceTrojans(String originalFile, String balancedFile, int repeat) throws FileNotFoundException, IOException{
        boolean  append=true;
        BufferedReader br  =  new BufferedReader(new FileReader(new File(originalFile)));
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(balancedFile),append));
        String temp="";
        ArrayList<String> things = new  ArrayList<String>();
        
        while((temp=br.readLine())!=null){
            temp  =  temp.replaceAll("\\s+", " ").trim();
            things.add(temp);
        }
        for(int i=0;i<repeat;i++)
            for(int j=0;j<things.size();j++)
                bw.append(things.get(j).trim()+"\n");
        
        bw.flush();
        bw.close();
    }
    
    
    public void unifyFinalForScikit(String path,String path1) throws IOException{
        boolean  append=true;
        BufferedReader br  =  new BufferedReader(new FileReader(new File(path)));
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(path1),append));
        String temp="";
        ArrayList<String> things = new  ArrayList<String>();
        
        while((temp=br.readLine())!=null){
            temp  =  temp.replaceAll("\\s+", " ").trim();
            if(!things.contains(temp))
                    things.add(temp);
        }
        for(int i=0;i<things.size();i++)
            bw.append(things.get(i).trim()+"\n");
        
        bw.flush();
        bw.close();
    }
    
    public void calculateAverageOfLines(String  path) throws FileNotFoundException, IOException{
        String[] splitted;
        String temp="";
        int counter=0;
        int[] Average = new int[5]; 
        int[] count200000 = new int[5];
        for(int i=0;i<5;i++)
                   count200000[i]=0;
        
        BufferedReader br  =  new BufferedReader(new FileReader(new File(path)));
        while((temp=br.readLine())!=null){
            temp  =  temp.replaceAll("\\s+", " ").trim();
            splitted = temp.split(",");
            for(int i=0;i<5;i++)
                if(!splitted[i].trim().equals("200000")){
                    if(counter==0)
                        Average[i]= Integer.parseInt(splitted[i].trim());
                    else  Average[i]+= Integer.parseInt(splitted[i].trim());
                } else {
                    if(counter==0)
                        Average[i]= 0;
                    else
                        Average[i]+= 0;
                    count200000[i]++;
                }
            counter++;
        }
        for(int i=0;i<5;i++)
                   System.out.println( (float)(Average[i]/(counter-count200000[i])));
                   
//        System.out.println(counter);
    
    }
    
    public void parseTrojans(ArrayList<String> trojans,String filepath) throws FileNotFoundException, IOException{
        BufferedReader br;
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(filepath+"Normal"),true));
        String temp="";
//        for(int j=0;j<16;j++)
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
    }
    
    public void parseNormals(ArrayList<String> trojans,String filepath) throws IOException{
        BufferedReader br;
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(filepath),true));
        String temp="";
        
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
    
    }
    
    public void parseTrojans7(ArrayList<String> trojans,String filepath) throws IOException{
        BufferedReader br;
        BufferedWriter bw  =  new BufferedWriter(new FileWriter(new File(filepath),true));
        String temp="";
        int counter = 0;
        int loop = 10;
        for(int j=0;j<loop;j++){
            for(int i=0;i<trojans.size();i++){

                br = new BufferedReader(new FileReader(new File(trojans.get(i))));
                while((temp=br.readLine())!=null)
                {  
                    temp = temp.replaceAll("\\s+", " ").trim();
                    bw.append(temp+"\n");
                }
                temp="";
            }
        }
        bw.flush();
        bw.close();
    }
    
    public void parseNormals7(ArrayList<String> trojans,String filepath){
    
    }
}
