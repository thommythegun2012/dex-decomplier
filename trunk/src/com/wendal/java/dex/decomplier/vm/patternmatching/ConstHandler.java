package com.wendal.java.dex.decomplier.vm.patternmatching;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wendal.java.dex.decomplier.dexfile.model.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.CatchException;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Const;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_If;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_packed_switch;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sparse_switch;

public class ConstHandler extends AbstractPatternMatching {

    @Override
    public boolean handle(List<PrototypeStatement> ps_list,
            List<LocalVar> lv_list, List<CatchException> exp_list,
            Object... setting) throws Throwable {
        
        Set<String> v_list = new HashSet<String>();
        
        for (PrototypeStatement ps : ps_list) {
            if(ps instanceof PrototypeStatement_Const){
                for (Field field : ps.getV()) {
                    v_list.add((String)field.get(ps));
//                    System.out.println(field.get(ps));
                }
            }
        }
        for (String vx_name : v_list) {
//            System.out.println(vx_name);
//            System.out.println("((((((((((((((((((((((((((((((((");
            for (int i = 0; i < ps_list.size(); i++) {
//                System.out.println("Here--------------");
                PrototypeStatement ps = ps_list.get(i);
                int o_i = i;
                if(ps instanceof PrototypeStatement_Const 
                        && ((PrototypeStatement_Const)ps).vx_name.equals(vx_name)){
//                    System.out.println(vx_name +"-----------------<");
                    
                    i++;
//                    System.err.println("this i "+i);
                    List<PrototypeStatement> ppp_list = new ArrayList<PrototypeStatement>();
                    flag : for (; i < ps_list.size(); i++) {
                        PrototypeStatement ps_tmp = ps_list.get(i);
//                        System.out.println("Get i = " +i );
                        if(ps_tmp instanceof PrototypeStatement_If){
                            i = ps_list.size();
                            break flag;
                        }
                        if(ps_tmp instanceof PrototypeStatement_Goto){
                            i = ps_list.size();
                            break flag;
                        }
                        if(ps_tmp instanceof PrototypeStatement_sparse_switch){
                            i = ps_list.size();
                            break flag;
                        }
                        if(ps_tmp instanceof PrototypeStatement_packed_switch){
                            i = ps_list.size();
                            break flag;
                        }
                        if(ps_tmp instanceof PrototypeStatement_Const 
                                && ((PrototypeStatement_Const)ps).vx_name.equals(vx_name)){
                            i = ps_list.size();
                            break flag;
                        }
                        
//                        if(ps_tmp instanceof PrototypeStatement_aget){
//                            System.out.println(ps_tmp.needPutVxxx(vx_name));
//                            break flag;
//                        }
                        
//                        if(ps_tmp.hasVxxx(vx_name)){
                            if(ps_tmp.needPutVxxx(vx_name)){
//                                System.out.println("vx_name = " +vx_name);
//                                System.out.println("current I = "+i);
                                i = ps_list.size();
                                break flag;
                            }else if(ps_tmp.hasVxxx(vx_name)){
//                                System.out.println(ps_tmp.getClass());
                                ppp_list.add(ps_tmp);
                            }
//                            System.out.println(ps_tmp.getClass());
//                            System.out.println("vx_name : " +vx_name);
//                            System.out.println("current I : "+i);
                            
//                        }
                    }
                    if(ppp_list.size() < 1){
//                        throw new RuntimeException();
                    }else{
                        String value_tmp = ((PrototypeStatement_Const)ps).var_value;
                        for (PrototypeStatement pp_tmp_2 : ppp_list) {
                            pp_tmp_2.setVxxxValue(vx_name, value_tmp);
//                            System.out.println(value_tmp);
//                            System.out.println(pp_tmp);
                        }
                    }
//                }else{
//                    if(ps instanceof PrototypeStatement_Const){
//                        System.out.println(((PrototypeStatement_Const)ps).var_value);
//                    }
//                    System.out.println("KKKK-------------- " + ppp_list.size() );
//                    if(ppp_list.size() > 0){
//                        System.out.println(ppp_list.get(0));
//                    }
//                }else{
//                    System.out.println(ps.getClass());
                }
                i = o_i;
//                System.out.println("Done ? "+i);
            }
            
        }
        
        return false;
    }

}
