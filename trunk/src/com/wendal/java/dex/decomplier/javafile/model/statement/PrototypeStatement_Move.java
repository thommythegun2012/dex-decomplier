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

    public String vx_name;
    public String vy_name;
    
    @Override
    public void parse() {
        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1 , info.indexOf(",")).trim();
        vy_name = info.substring(info.indexOf(",")+1).trim();
        
        if(info.indexOf("-wide") > -1){
            isWide = true;
        }
        
        if(info.indexOf("-object ") > -1){
            isObject = true;
            if(vy_name.startsWith("L")){
                String class_name_temp = vy_name.substring(0, vy_name.indexOf(";"));
                String class_name =String_Toolkit.parseSingleClassName(class_name_temp);
                String var_name = vy_name.substring(vy_name.indexOf(".")+1,vy_name.indexOf(":"));
                
                vy_name = class_name + "." + var_name;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nMove : "+vx_name +" = " + vy_name;
    }

}
