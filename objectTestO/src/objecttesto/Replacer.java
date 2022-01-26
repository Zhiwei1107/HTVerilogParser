/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objecttesto;

/**
 *
 * @author reza
 */
public class Replacer {
    
    public  Replacer(){
    
    }
    
    public String replace(String input,String base,String alternate){
        String result=input;
        result  = result.replace(base, alternate);
        return result;
    }
    
}
