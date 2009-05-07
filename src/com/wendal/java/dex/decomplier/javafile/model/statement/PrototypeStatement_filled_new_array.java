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

public class PrototypeStatement_filled_new_array extends PrototypeStatement {
    public String [] v_data;
    
    public String vx_type;

    @Override
    public void parse() {

        super.parse();

        v_data = info.substring(info.indexOf("{")+1 , info.indexOf("}")).split(",");
        for (int i = 0; i < v_data.length; i++) {
            v_data[i] = v_data[i].trim();
        }

        vx_type = String_Toolkit.parseType(info.substring(info.indexOf("[")+1).trim());
    }

    @Override
    public String toString() {
        String tmp = "";
        for (int i = 0; i < v_data.length; i++) {
            tmp = tmp + " ," + v_data[i];
        }
        return super.toString() + "\n new " + vx_type + " {"+ tmp +"} /*";
    }
}
