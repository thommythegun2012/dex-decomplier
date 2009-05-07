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
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Instance_Of extends PrototypeStatement {
    
    public String var_x_name;
    
    public String var_y_name;
    
    public String type_id_name;
    
    @Override
    public void parse() {
        
        var_x_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();        
        var_y_name = info.substring(info.indexOf(",")+1, info.lastIndexOf(",")).trim();
        
        String tmp_str = info.substring(info.lastIndexOf(" ")).trim();
        
        type_id_name = String_Toolkit.parseSingleClassName(tmp_str).replaceAll(";", "");

        super.parse();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + var_x_name + " = ("+var_y_name+" instanceof "+type_id_name+")";
    }
}
