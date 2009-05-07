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

public class PrototypeStatement_Move extends PrototypeStatement {
    
    public boolean isWide;
    
    public boolean isObject;

    public String var_a_name;
    public String var_b_name;
    
    @Override
    public void parse() {
        super.parse();
        
        var_a_name = info.substring(info.indexOf(" ")+1 , info.indexOf(",")).trim();
        var_b_name = info.substring(info.indexOf(",")+1).trim();
        
        if(info.indexOf("-wide") > -1){
            isWide = true;
        }
        
        if(info.indexOf("-object ") > -1){
            isObject = true;
            if(var_b_name.startsWith("L")){
                String class_name_temp = var_b_name.substring(0, var_b_name.indexOf(";"));
                String class_name =String_Toolkit.parseSingleClassName(class_name_temp);
                String var_name = var_b_name.substring(var_b_name.indexOf(".")+1,var_b_name.indexOf(":"));
                
                var_b_name = class_name + "." + var_name;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nMove : "+var_a_name +" = " + var_b_name;
    }

}
