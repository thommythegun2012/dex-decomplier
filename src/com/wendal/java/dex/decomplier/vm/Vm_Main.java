package com.wendal.java.dex.decomplier.vm;

import java.util.ArrayList;
import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;

public class Vm_Main {
    
    private List<PrototypeStatement> ps_list;
    
    public List<String> parse(List<PrototypeStatement> list, List<? extends LocalVar> lv_list , List<? extends Object> exp_list , Object...setting) {
        List<String> source_statement = new ArrayList<String>();
        this.ps_list = list;
        
        for (int i = 0; i < ps_list.size(); i++) {
            PrototypeStatement ps = ps_list.get(i);
            if(ps instanceof PrototypeStatement_Goto){
                addFlag_Start(((PrototypeStatement_Goto)ps).goto_line_index);
                i++;
//                PrototypeStatement pss = new Flag_End();
//                this.ps_list.add(i, pss);
            }
        }
        
        for (PrototypeStatement ps : ps_list) {
            source_statement.add(ps.toString());
        }

        return source_statement;
    }

    private void addFlag_Start(String flag_line_offset){
        for (int i = 0; i < ps_list.size(); i++) {
            PrototypeStatement ps = ps_list.get(i);
            if(ps.dex_offset != null && ps.dex_offset.endsWith(flag_line_offset)){
                ps_list.add(i-1, new Flag_Start());
                return;
            }
        }
    }
    
//    private void addFlag_End(int d){
//        
//    }
}
