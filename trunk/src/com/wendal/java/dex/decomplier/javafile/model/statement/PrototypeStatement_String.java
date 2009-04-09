package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_String extends PrototypeStatement {
    
    public PrototypeStatement_String() {
        this.type = STRING;
    }
    
    public String var_values;
    
    public String var_name;

    @Override
    public String toString() {
        
        return super.toString();
    }
}
