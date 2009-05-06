//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_new_array extends PrototypeStatement {
    
    String var_name;
    
    String var_type;
    
    @Override
    public void parse() {
        super.parse();

        var_name = info.substring(info.indexOf(" ")+1,info.indexOf(","));
        var_type = String_Toolkit.parseType(info.substring(info.lastIndexOf(", ")+2).replaceAll(";", "")).replaceAll("/", ".");
    }

    @Override
    public String toString() {
        return super.toString() + "\n"+var_type + " " + var_name ;
    }
}
