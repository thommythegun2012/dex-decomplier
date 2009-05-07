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
package com.wendal.java.dex.decomplier.dexfile.model;

import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class Dex_Field {
    private String type;
    private String name;
    private String access_flags;
    
    public Dex_Field(String src_name , String src_type , String src_access) {
        String[] data_array = src_name.split(":");
        this.name = data_array[1].replaceAll("'", "").trim();
        
        String[] data_array_2 = src_type.split(":");
            this.type = String_Toolkit.parseSingleClassName(data_array_2[1]).replaceAll(";", "");
        String[] data_array_3 = src_access.split(":");
        this.access_flags = data_array_3[1];
    }
    
    @Override
    public String toString() {
        return "<"+access_flags+"> #"+type + "# "+name;
    }
    
    
    /**************Get Set Methods**************************/
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(String access_flags) {
        this.access_flags = access_flags;
    }
    
    
}
