//:~
/**
 * $Id$
 * 
 * @author $Author$
 * @version $revision: $
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

public class PrototypeStatement_String extends PrototypeStatement {
    
    public PrototypeStatement_String(PrototypeStatement ps) {
//        this.type = STRING;
        this.info = ps.info;
        this.dex_offset = ps.dex_offset;
        this.note = ps.note;
        this.opcodes = ps.opcodes;
    }
    
    /**
     * °üº¬Ë«ÒýºÅ
     */
    public String var_values;
    
    public String vx_name;
    
    public void parse() {
        vx_name = info.substring("const-string ".length() , info.indexOf(",")).trim();
        var_values = info.substring(info.indexOf("\""), info.lastIndexOf("\"")+1);
    }

    @Override
    public String toString() {
        return vx_name + " = " + var_values;
    }
}
