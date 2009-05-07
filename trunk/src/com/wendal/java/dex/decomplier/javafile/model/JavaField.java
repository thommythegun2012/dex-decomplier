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
package com.wendal.java.dex.decomplier.javafile.model;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Field;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class JavaField {
    
    /**
     * public , private , protected , or ""
     */
    public String access_flags = DEFAUFT;

    public boolean isStatic = false;
    public boolean isFinal = false;
    
    public String name;
    
    public String type;

    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";
    
    private Dex_Field dex_field;
    
    public JavaField(Dex_Field dex_field) {
        this.dex_field = dex_field;
    }
    
    public void parse(){
        //处理访问控制符
        String access_flag = dex_field.getAccess_flags();
        if(access_flag.indexOf("PUBLIC") > -1){
            this.access_flags = PUBLIC;
        }else if(access_flag.indexOf("PRIVATE") > -1){
            this.access_flags = PRIVATE;
        }else if(access_flag.indexOf("PROTECTED") > -1){
            this.access_flags = PROTECTED;
        }
        
        if(access_flag.indexOf("FINAL") > -1){
            this.isFinal = true;
        }
        
        this.type = String_Toolkit.parseType(dex_field.getType());
        
        this.name = dex_field.getName();
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(access_flags).append(" ");
        if(isStatic){
            sb.append("static").append(" ");
        }
        if(isFinal){
            sb.append("final").append(" ");
        }
        sb.append(type).append(" ");
        sb.append(name);
        return sb.toString();
    }
}
