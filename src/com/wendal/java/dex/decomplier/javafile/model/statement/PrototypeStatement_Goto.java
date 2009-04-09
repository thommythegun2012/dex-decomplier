package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Goto extends PrototypeStatement {
    
    public String goto_line_index;
    
    public PrototypeStatement_Goto(PrototypeStatement src) {
            this.type = GOTO;
        
            this.dex_offset = src.dex_offset;
            this.opcodes = src.opcodes;
            this.line_index = src.line_index;
            this.info = src.info;
            this.note = src.note;
            
            this.goto_line_index = info.replaceFirst("goto", "").trim();
//            System.out.println("----------------------LLLLLLLLLLLLLLLL" + goto_line_index);
            
    }
}
