package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Const extends PrototypeStatement {
    
    public String var_name;
    
    public String var_type;
    
    public String var_value;
    
    public boolean isWide;
    
    @Override
    public void parse() {
        super.parse();
        
        var_name = info.substring(info.indexOf(" ")+1 , info.lastIndexOf(",")).trim();
        
        var_type = info.substring(info.indexOf("#")+1 , info.lastIndexOf(" ")).trim();
        var_value = info.substring(info.lastIndexOf(" ")).trim();
        
        if(info.startsWith("const-wide")){
            isWide = true;
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n" + var_type + " " + var_name + " = " + var_value;
    }
}
