//:~
/**
 * $Id: $
 * 
 * @author $Author: $
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_array_length extends PrototypeStatement {
    
    String vx;
    
    String vy;
    
    @Override
    public void parse() {
        super.parse();

        vx = info.substring(info.indexOf(" ")+1,info.indexOf(","));
        vy = info.substring(info.indexOf(",")+1);
    }

    @Override
    public String toString() {
        return vx + " = " + vy + ".length";
    }

}
