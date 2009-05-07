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

public class PrototypeStatement_Check_Cast extends PrototypeStatement {
    
    public String check_obj;
    
    public String check_type;
    
    @Override
    public void parse() {
        super.parse();
        
        check_obj = info.substring("check-cast ".length() ,info.indexOf(",")).trim();
        
        check_type = String_Toolkit.parseSingleClassName(info.substring(info.indexOf(",")+1).trim()).replaceAll(";", "");
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +"/* CheckCase : --> (" + check_type+ ")"+check_obj+" /*";
    }
}
