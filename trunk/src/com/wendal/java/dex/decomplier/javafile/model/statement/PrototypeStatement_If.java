package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

/**
 * 一整类判断,与一个值进行对比
 * @author zcchen
 *
 */
public class PrototypeStatement_If extends PrototypeStatement {
    
    public String If_eq = "if-eq";
    public String If_ne =  "if-ne";
    public String If_lt =  "if-lt";
    public String If_ge =  "if-ge";
    public String If_gt =  "if-gt";
    public String If_le =  "if-le";
    
    public PrototypeStatement_If(PrototypeStatement ps) {
        this.info = ps.info.trim();
        this.line_index = ps.line_index;
        this.note = ps.note;
        this.opcodes = ps.opcodes.trim();
        
        parse();
    }
    
    public String jump_to ;

    public String value_a = "";
    public String value_b = "";
    public boolean isIf_Zero = false;
    
    public String op;

    @Override
    public void parse() {
        String IF_info = info.substring(0,info.trim().indexOf(" ")+1).trim();
        if(IF_info.endsWith("z")){
            isIf_Zero = true;
        }
        
        if (IF_info.startsWith(If_eq)) {
            op = "==";
        }
        if (IF_info.startsWith(If_ne)) {
            op = "!=";
        }
        if (IF_info.startsWith(If_lt)) {
            op = "<";
        }
        if (IF_info.startsWith(If_ge)) {
            op = ">=";
        }
        if (IF_info.startsWith(If_gt)) {
            op = ">";
        }if (IF_info.startsWith(If_le)) {
            op = "<=";
        }
        
        //取vx
        value_a = info.substring(info.indexOf(" ")+1 ,info.indexOf(",")).trim();
        if(isIf_Zero){
            value_b = "0";
            jump_to = info.substring(info.indexOf(",")+1 ).trim();
        }else{
            value_b = info.substring(info.indexOf(",")+1 , info.lastIndexOf(",")).trim();
            jump_to = info.substring(info.lastIndexOf(",")+1 ).trim();
        }
        
        super.parse();
    }
    
    @Override
    public String toString() {
        
        return "if("+value_a+ " "+op + " "+ value_b +") jump_to " +jump_to;
    }
}
