package com.wendal.java.dex.decomplier.vm;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;


public class Flag_Start extends PrototypeStatement{

    @Override
    public String toString() {
        return "while(true){";
    }
}
