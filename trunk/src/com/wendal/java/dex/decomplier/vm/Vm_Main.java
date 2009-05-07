package com.wendal.java.dex.decomplier.vm;

import java.util.ArrayList;
import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class Vm_Main {
    
    public static List<String> parse(List<? extends PrototypeStatement> ps_list, List<? extends LocalVar> lv_list , List<? extends Object> exp_list , Object...setting) {
        List<String> source_statement = new ArrayList<String>();
        
        for (PrototypeStatement ps : ps_list) {
            source_statement.add(ps.toString());
        }

        return source_statement;
    }

}
