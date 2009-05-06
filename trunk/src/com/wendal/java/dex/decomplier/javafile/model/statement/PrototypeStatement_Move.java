//:~
/**
 * $Id: $
 * 
 * @author $Author: $
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Move extends PrototypeStatement {
    
    public boolean isWide;
    
    public boolean isObject;

    public String var_a_name;
    public String var_b_name;
    
    @Override
    public void parse() {
        super.parse();
        
        var_a_name = info.substring(info.indexOf(" ")+1 , info.indexOf(",")).trim();
        var_b_name = info.substring(info.indexOf(",")+1).trim();
        
        if(info.indexOf("-wide") > -1){
            isWide = true;
        }
        
        if(info.indexOf("-object ") > -1){
            isObject = true;
            if(var_b_name.startsWith("L")){
                String class_name_temp = var_b_name.substring(0, var_b_name.indexOf(";"));
                String class_name =String_Toolkit.parseSingleClassName(class_name_temp);
                String var_name = var_b_name.substring(var_b_name.indexOf(".")+1,var_b_name.indexOf(":"));
                
                var_b_name = class_name + "." + var_name;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nMove : "+var_a_name +" = " + var_b_name;
    }

}
