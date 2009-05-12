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

public class PrototypeStatement_binop_2addr extends PrototypeStatement {
    @Vxxx
    public String vx_name;

    @Vxxx
    public String vy_name;

    @Vxxx
    public String vz_name;
    
    public String v_op;
    
    @Override
    public void parse() {
        super.parse();

        vx_name = info.substring(info.indexOf(" ")+1 , info.indexOf(","));
        
        vy_name = vx_name;
        
        vz_name = info.substring(info.indexOf(",")+1 );
        
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
