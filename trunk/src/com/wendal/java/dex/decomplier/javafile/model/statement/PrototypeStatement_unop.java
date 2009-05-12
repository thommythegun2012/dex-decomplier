//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.javafile.model.Vxxx;

/**
 * Perform the identified unary operation on the source register, storing the result in the destination register.
 * @author zcchen
 *
 */
public class PrototypeStatement_unop extends PrototypeStatement {

    @Vxxx
    public String vx_name;

    @Vxxx
    public String vy_name;

    public String v_op;

    @Override
    public void parse() {

        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();
        
        vy_name = info.substring(info.indexOf(", ")+2);
        
        if(info.startsWith("neg-")){
            v_op = "-";
        }else if(info.startsWith("not-")){
            v_op = "^";
        }else if(info.startsWith("int-to-long")){
            v_op = "(long)";
        }else if(info.startsWith("int-to-float")){
            v_op = "(float)";
        }else if(info.startsWith("int-to-double")){
            v_op = "(double)";
        }else if(info.startsWith("long-to-int")){
            v_op = "(int)";
        }else if(info.startsWith("long-to-float")){
            v_op = "(float)";
        }else if(info.startsWith("long-to-double")){
            v_op = "(double)";
        }else if(info.startsWith("float-to-int")){
            v_op = "(int)";
        }else if(info.startsWith("float-to-long")){
            v_op = "(long)";
        }else if(info.startsWith("float-to-double")){
            v_op = "(double)";
        }else if(info.startsWith("double-to-int")){
            v_op = "(int)";
        }else if(info.startsWith("double-to-long")){
            v_op = "(long)";
        }else if(info.startsWith("double-to-float")){
            v_op = "(float)";
        }else if(info.startsWith("int-to-byte")){
            v_op = "(byte)";
        }else if(info.startsWith("int-to-char")){
            v_op = "(char)";
        }else if(info.startsWith("int-to-short")){
            v_op = "(short)";
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n/*"  + vx_name + " = "+v_op+ " "+ vy_name;
    }
}
