package com.wendal.java.dex.decomplier.vm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wendal.java.dex.decomplier.dexfile.model.LocalVar;

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
    
    public void initLocalVa(List<LocalVar> lv){
        for (LocalVar localVar : lv) {
            String r_name = "v"+localVar.reg;
            String r_val = localVar.name;
            
            RegisterUnit ru = new RegisterUnit();
            ru.setType(RegisterUnit.LV);
            ru.setValue(r_val);
            
            maps.put(r_name, ru);
        }
    }
}
