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
package com.wendal.java.dex.decomplier.converter;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Field;
import com.wendal.java.dex.decomplier.javafile.model.JavaField;

public class DexField2JavaField {

    public static JavaField parseDex(Dex_Field dex_field){
        JavaField javaField = new JavaField(dex_field);
        javaField.parse();
        
        return javaField;
    }
}
