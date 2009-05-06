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
