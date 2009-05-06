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

public class PrototypeStatement_Const_class extends PrototypeStatement {
    public String vx_name;
    
    public String var_value;
    
    @Override
    public void parse() {
        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1 , info.lastIndexOf(",")).trim();
        
        var_value = info.substring(info.lastIndexOf("L") , info.indexOf(";")).replaceAll("/", ".");
        
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n" +  vx_name + " = " + var_value + ".class";
    }
}
