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
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_new_array extends PrototypeStatement {

    @Vxxx(type=Vxxx.Type.PUT)
    String vx_name;
    
    @Vxxx
    String vy_name;
    
    String var_type;
    
    @Override
    public void parse() {
        super.parse();

        vx_name = info.substring(info.indexOf(" ")+1,info.indexOf(","));
        
        vy_name = info.substring(info.indexOf(", ")+2,info.lastIndexOf(","));
        
        var_type = String_Toolkit.parseType(info.substring(info.lastIndexOf(", ")+2).replaceAll(";", "")).replaceAll("/", ".");
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + vx_name +" = new "+var_type.substring(0,var_type.length()-1) + vy_name + "]";
    }
}
