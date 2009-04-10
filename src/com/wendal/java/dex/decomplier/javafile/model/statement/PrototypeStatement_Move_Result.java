package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Move_Result extends PrototypeStatement {
    
    public boolean isWide;
    
    public boolean isObject;
    
    public String var_name;
    
    @Override
    public void parse() {
        super.parse();
        
        var_name = info.substring(info.indexOf(" ")+1).trim();
        if(info.startsWith("move-result-wide ")){
            isWide = true;
        }
        
        if(info.startsWith("move-result-object ")){
            isObject = true;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nMove-Result --> "+var_name;
    }
}
