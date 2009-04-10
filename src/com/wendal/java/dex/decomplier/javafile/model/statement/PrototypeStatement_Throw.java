package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Throw extends PrototypeStatement {
    
    public PrototypeStatement_Throw() {
        // TODO Auto-generated constructor stub
    }
    
    public String throw_obj;
    
    @Override
    public void parse() {
        
        throw_obj = info.substring(info.indexOf(" ")).trim();
        
        super.parse();
    }
    
    @Override
    public String toString() {
        return "throw "+throw_obj;
    }

}
