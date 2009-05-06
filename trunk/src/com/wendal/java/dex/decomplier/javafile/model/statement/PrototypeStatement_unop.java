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

/**
 * Perform the identified unary operation on the source register, storing the result in the destination register.
 * @author zcchen
 *
 */
public class PrototypeStatement_unop extends PrototypeStatement {
    public String vx_name;
    
    public String vy_name;

    public String v_op;

    @Override
    public void parse() {

        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();
        
        vy_name = info.substring(info.indexOf(", ")+2);
        
        if(opcodes.startsWith("neg-")){
            v_op = "-";
        }else if(opcodes.startsWith("not-")){
            v_op = "^";
        }else if(opcodes.startsWith("int-to-long")){
            v_op = "(long)";
        }else if(opcodes.startsWith("int-to-float")){
            v_op = "(float)";
        }else if(opcodes.startsWith("int-to-double")){
            v_op = "(double)";
        }else if(opcodes.startsWith("long-to-int")){
            v_op = "(int)";
        }else if(opcodes.startsWith("long-to-float")){
            v_op = "(float)";
        }else if(opcodes.startsWith("long-to-double")){
            v_op = "(double)";
        }else if(opcodes.startsWith("float-to-int")){
            v_op = "(int)";
        }else if(opcodes.startsWith("float-to-long")){
            v_op = "(long)";
        }else if(opcodes.startsWith("float-to-double")){
            v_op = "(double)";
        }else if(opcodes.startsWith("double-to-int")){
            v_op = "(int)";
        }else if(opcodes.startsWith("double-to-long")){
            v_op = "(long)";
        }else if(opcodes.startsWith("double-to-float")){
            v_op = "(float)";
        }else if(opcodes.startsWith("int-to-byte")){
            v_op = "(byte)";
        }else if(opcodes.startsWith("int-to-char")){
            v_op = "(char)";
        }else if(opcodes.startsWith("int-to-short")){
            v_op = "(short)";
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n/*"  + vx_name + " = "+v_op+ " vy_name ";
    }
}
