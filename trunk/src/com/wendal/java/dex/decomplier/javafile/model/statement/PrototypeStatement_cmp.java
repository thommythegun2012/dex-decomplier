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

public class PrototypeStatement_cmp extends PrototypeStatement {
    String vx;

    String vy;
    
    String vz;
    
    String type = "";

    @Override
    public void parse() {
        super.parse();
        
        type = info.substring(info.indexOf("cmp")+3 , info.indexOf(" "));

        vx = info.substring(info.indexOf(" ") + 1, info.indexOf(","));
        vy = info.substring(info.indexOf(", ")+2 , info.lastIndexOf(","));
        vz = info.substring(info.lastIndexOf(",")+1).trim();
        
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + vx + " = (" +vy + " > " + vz+")";
    }

}
