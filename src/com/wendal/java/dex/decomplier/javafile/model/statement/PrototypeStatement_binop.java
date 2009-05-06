//:~
/**
 * $Id: $
 * 
 * @author $Author: $
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_binop extends PrototypeStatement {
    public String vx_name;
    
    public String vy_name;
    
    public String vz_name;
    
    public String v_op;
    
    @Override
    public void parse() {
        super.parse();

        vx_name = info.substring(info.indexOf(" ")+1 , info.indexOf(","));
        vy_name = info.substring(info.indexOf(",")+1 , info.lastIndexOf(",")).trim();
        vz_name = info.substring(info.lastIndexOf(",")+1).trim();
        
        if(info.startsWith("add")){
            v_op = "+";
        }else if(info.startsWith("sub")){
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
        return super.toString()+"\n/* " + vx_name + " = " + vy_name + " " +v_op + " " + vz_name;
    }
}
