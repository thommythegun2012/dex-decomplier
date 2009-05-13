package com.wendal.java.dex.decomplier.vm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.CatchException;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.javafile.model.Vxxx;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_If;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnVoid;
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
        this.exp_list = exp_list;

        // 登记全部return-void语句,只记录line_index
        ArrayList<String> ps_returnvoid_list = new ArrayList<String>();
        for (PrototypeStatement ps : ps_list) {
            if (ps instanceof PrototypeStatement_ReturnVoid) {
                ps_returnvoid_list.add(Integer.toString(ps.line_index, 16));
            }
        }
        // 替换掉指向return-void的goto语句
        // System.out.println(ps_returnvoid_list.size());
        for (int i = 0; i < ps_list.size(); i++) {
            PrototypeStatement ps = ps_list.get(i);
            if (ps instanceof PrototypeStatement_Goto) {
                PrototypeStatement_Goto ps_goto = (PrototypeStatement_Goto) ps;
                for (String string : ps_returnvoid_list) {
                    if (string.equals(ps_goto.goto_line_index)) {
                        // 替换成return-void
                        int index = ps_list.indexOf(ps);
                        ps_list.set(index, PrototypeStatement.convertTotype(ps,
                                PrototypeStatement_ReturnVoid.class));

                        // System.out.println("--->替换跳转return-void: " +
                        // ps_goto.line_index+" --> "+ps_goto.goto_line_index);
                        break;
                    }
                }
            }
        }

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
        parseLocalVals();
        {
            VirtualRegister vr = initRegiterByPrototypeStatement(ps_list);
            for (String key  : vr.getKeys()) {
                RegisterUnit ru = vr.getRegiter(key);
                List<PrototypeStatement> tmp_ps_list = new ArrayList<PrototypeStatement>();
                    for (PrototypeStatement ps : ps_list) {
                        if(ps.hasVxxx(key)){
                            tmp_ps_list.add(ps);
                            System.out.println(ps.dex_offset );
                    }
                }
            }
//            System.out.println(vr.getKeys().size());
        }
        for (PrototypeStatement ps : ps_list) {

            source_statement.add(ps.toString());
        }
    }

    private void parseLocalVals() {
        VirtualRegister vr = new VirtualRegister();
        vr.initLocalVa(lv_list);
        for (LocalVar lv : lv_list) {
            for (PrototypeStatement ps : ps_list) {
                if (ps.line_index >= lv.start_line_index
                        && ps.line_index <= lv.end_line_index) {
                    ps.setVxxxValue("v" + lv.reg, lv.name);
                }
            }
        }
    }

    private static VirtualRegister initRegiterByPrototypeStatement(List<PrototypeStatement> v_ps_list) {
        VirtualRegister vr = new VirtualRegister();
        for (PrototypeStatement ps : v_ps_list) {
            if (ps instanceof PrototypeStatement_If) {
                for (RegisterUnit ru : vr.getRegiters()) {
                    ru.passIf();
                }
                continue;
            }
            if (ps instanceof PrototypeStatement_Goto) {
                for (RegisterUnit ru : vr.getRegiters()) {
                    ru.passGOTO();
                }
                continue;
            }

            for (Field field : ps.getV()) {
                Vxxx vx = field.getAnnotation(Vxxx.class);
                if (vx.type().equals(Vxxx.Type.GET)) {
                    String tmp_str = ps.getVxxxValue(field);
                    if (tmp_str != null) {
                        vr.getRegiter(tmp_str).getValue();
                    }
                } else if (vx.type().equals(Vxxx.Type.GET)) {
                    String tmp_str = ps.getVxxxValue(field);
                    if (tmp_str != null) {
                        vr.getRegiter(tmp_str).addSetEvent();
                    }
                }
            }
        }
        return vr;
    }
}
