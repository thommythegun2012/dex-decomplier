//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_binop_lit16 extends PrototypeStatement {
    public String vx_name;
    
    public String vy_name;
    
    public String vz_value;
    
    public String v_op;
    
    @Override
    public void parse() {
        super.parse();

        vx_name = info.substring(info.indexOf(" ")+1 , info.indexOf(","));
        
        vy_name = info.substring(info.indexOf(",")+1 , info.indexOf(", #"));
        
        vz_value = info.substring(info.lastIndexOf(" ")+1);
        
        if(info.startsWith("add")){
            v_op = "+";
        }else if(info.startsWith("rsub")){
            v_op = "-";
        }else if(info.startsWith("mul")){
            v_op = "*";
        }else if(info.startsWith("div")){
            v_op = "/";
        }else if(info.startsWith("rem")){
            v_op = "%";
        }else if(info.startsWith("and")){
            v_op = "&"; 
        }else if(info.startsWith("or")){
            v_op = "|"; 
        }else if(info.startsWith("xor")){
            v_op = "^"; 
        }else if(info.startsWith("shl")){
            v_op = "<<"; 
        }else if(info.startsWith("shr")){
            v_op = ">>";
        }else if(info.startsWith("ushr")){
            v_op = ">>"; 
        }
    }

    @Override
    public String toString() {
        return super.toString()+"\n/* " + vx_name + " = " + vy_name + " " +v_op + " " + vz_value;
    }
}
