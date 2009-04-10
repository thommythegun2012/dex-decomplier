package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Check_Cast extends PrototypeStatement {
    
    public String check_obj;
    
    public String check_type;
    
    @Override
    public void parse() {
        super.parse();
        
        check_obj = info.substring("check-cast ".length() ,info.indexOf(",")).trim();
        
        check_type = String_Toolkit.parseSingleClassName(info.substring(info.indexOf(",")+1).trim()).replaceAll(";", "");
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +"/* CheckCase : --> (" + check_type+ ")"+check_obj+" /*";
    }
}
