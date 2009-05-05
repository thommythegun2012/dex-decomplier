package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_nop extends PrototypeStatement {
    
    @Override
    public void parse() {
        super.parse();
    }

    @Override
    public String toString() {
        return super.toString() +"/* Just a nop */";
    }
}
