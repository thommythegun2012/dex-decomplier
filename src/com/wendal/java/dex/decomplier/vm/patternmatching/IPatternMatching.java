package com.wendal.java.dex.decomplier.vm.patternmatching;

import java.util.List;

import com.wendal.java.dex.decomplier.dexfile.model.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.CatchException;
import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public interface IPatternMatching {
    
    boolean parse(List<PrototypeStatement> ps_list,
            List<LocalVar> lv_list, List<CatchException> exp_list,
            Object... setting) throws Throwable;

}
