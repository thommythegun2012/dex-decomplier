package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Instance_Of extends PrototypeStatement {
    
    public String var_x_name;
    
    public String var_y_name;
    
    public String type_id_name;
    
    @Override
    public void parse() {
        
        var_x_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();        
        var_y_name = info.substring(info.indexOf(",")+1, info.lastIndexOf(",")).trim();
        
        String tmp_str = info.substring(info.lastIndexOf(" ")).trim();
        
        type_id_name = String_Toolkit.parseSingleClassName(tmp_str).replaceAll(";", "");

        super.parse();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + var_x_name + " = ("+var_y_name+" instanceof "+type_id_name+")";
    }
}
