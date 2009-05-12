package com.wendal.java.dex.decomplier.vm;

import java.util.ArrayList;
import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.CatchException;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_If;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_packed_switch;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sparse_switch;

public class Vm_Main {

    private List<PrototypeStatement> ps_list;
    
    private List<LocalVar> lv_list;
    
    private List<CatchException> exp_list;
    private List<String> source_statement = new ArrayList<String>();
    public List<String> parse(List<PrototypeStatement> list,
            List<LocalVar> lv_list, List<CatchException> exp_list,
            Object... setting) {
        
        this.ps_list = list;
        this.lv_list = lv_list;
        this.exp_list =exp_list;
        
        
        
        boolean simple_method = true;
        for (int i = 0; i < ps_list.size(); i++) {
            PrototypeStatement ps = ps_list.get(i);
            if (ps instanceof PrototypeStatement_Goto) {
                addFlag_Start(((PrototypeStatement_Goto) ps).goto_line_index);
                i++;
                // PrototypeStatement pss = new Flag_End();
                // this.ps_list.add(i, pss);
                simple_method = false;
                continue;
            }
            if (ps instanceof PrototypeStatement_If) {
                simple_method = false;
            }
            if (ps instanceof PrototypeStatement_sparse_switch) {
                simple_method = false;
            }
            if (ps instanceof PrototypeStatement_packed_switch) {
                simple_method = false;
            }
        }

        if (simple_method) {
            parseSimpleMethod();
        } else {
            for (PrototypeStatement ps : ps_list) {
                source_statement.add(ps.toString());
            }
        }
        return source_statement;
    }

    private void addFlag_Start(String flag_line_offset) {
        for (int i = 0; i < ps_list.size(); i++) {
            PrototypeStatement ps = ps_list.get(i);
            if (ps.dex_offset != null
                    && ps.dex_offset.endsWith(flag_line_offset)) {
                ps_list.add(i - 1, new Flag_Start());
                return;
            }
        }
    }

    /**
     * Simple method is that without any Goto and If , switch
     */
    private void parseSimpleMethod() {
        VirtualRegister vr = new VirtualRegister();
        vr.initLocalVa(lv_list);
        for (PrototypeStatement ps : ps_list) {
            source_statement.add(ps.toString());
        }
    }
}
