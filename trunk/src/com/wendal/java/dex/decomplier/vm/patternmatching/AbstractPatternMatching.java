package com.wendal.java.dex.decomplier.vm.patternmatching;

import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.CatchException;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public abstract class AbstractPatternMatching implements IPatternMatching{

    public void setUp(){
        ;
    }
    
    public void setDown(){
        ;
    }
    
    @Override
    public boolean parse(List<PrototypeStatement> ps_list,
            List<LocalVar> lv_list, List<CatchException> exp_list,
            Object... setting) throws Throwable{
        setUp();
        
        boolean result = handle(ps_list,lv_list,exp_list,setting);
        
        setDown();
        
        return result;
    }
    
    public abstract boolean handle(List<PrototypeStatement> ps_list,
            List<LocalVar> lv_list, List<CatchException> exp_list,
            Object... setting) throws Throwable;
}
