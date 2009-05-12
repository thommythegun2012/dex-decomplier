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

public class PrototypeStatement_ReturnX extends PrototypeStatement {
    
    public PrototypeStatement_ReturnX() {
        // TODO Auto-generated constructor stub
    }
    
    
    public String vx_name;
    
    public String obj_type;
    
    
    @Override
    public void parse() {
        super.parse();
        vx_name = info.substring(info.indexOf(" ")).trim();
        
        if(info.startsWith("return ")){
            obj_type = "V";
            return;
        }
        if(info.startsWith("return-object")){
            obj_type = "Object";
            return;
        }
        
        if(info.startsWith("return-wide")){
            obj_type = "Wide";
            return;
        }
    }

    @Override
    public String toString() {
        return "return "+vx_name;
    }
}
