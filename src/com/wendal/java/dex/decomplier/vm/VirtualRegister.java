package com.wendal.java.dex.decomplier.vm;

import java.util.HashMap;
import java.util.Map;

public class VirtualRegister {
    
    private Map<String, RegisterUnit> maps = new HashMap<String, RegisterUnit>();
    
    public RegisterUnit getRegiter(String vx){
        RegisterUnit ru = maps.get(vx);
        if(ru == null){
            ru = new RegisterUnit();
            maps.put(vx, ru);
        }
        return ru;
    }

    public void putRegiter(String vx , RegisterUnit ru){
        maps.put(vx, ru);
    }
}
