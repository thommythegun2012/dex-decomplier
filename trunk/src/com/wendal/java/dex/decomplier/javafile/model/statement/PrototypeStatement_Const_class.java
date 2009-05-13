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

public class PrototypeStatement_Const_class extends PrototypeStatement {
    @Vxxx(type=Vxxx.Type.PUT)
    public String vx_name;
    
    public String var_value;
    
    @Override
    public void parse() {
        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1 , info.lastIndexOf(",")).trim();
        
        var_value = info.substring(info.lastIndexOf("L")+1 , info.indexOf(";")).replaceAll("/", ".");
        
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n" +  vx_name + " = " + var_value + ".class";
    }
}
