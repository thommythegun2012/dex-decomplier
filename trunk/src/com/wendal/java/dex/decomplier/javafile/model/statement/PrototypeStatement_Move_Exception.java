package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Move_Exception extends PrototypeStatement {
    public String var_name;
    
    @Override
    public void parse() {
        super.parse();
        
        var_name = info.substring(info.indexOf(" ")+1).trim();
        
    }

    @Override
    public String toString() {
        return super.toString() + "\nMove-Exception --> "+var_name;
    }
}
