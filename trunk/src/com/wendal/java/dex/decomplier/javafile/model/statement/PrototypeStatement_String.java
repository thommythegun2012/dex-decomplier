package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_String extends PrototypeStatement {
    
    public PrototypeStatement_String(PrototypeStatement ps) {
//        this.type = STRING;
        this.info = ps.info;
        this.dex_offset = ps.dex_offset;
        this.note = ps.note;
        this.opcodes = ps.opcodes;
    }
    
    /**
     * °üº¬Ë«ÒýºÅ
     */
    public String var_values;
    
    public String var_name;
    
    public void parse() {
        var_name = info.substring("const-string ".length() , info.indexOf(",")).trim();
        var_values = info.substring(info.indexOf("\""), info.lastIndexOf("\"")+1);
    }

    @Override
    public String toString() {
        return var_name + " = " + var_values;
    }
}
