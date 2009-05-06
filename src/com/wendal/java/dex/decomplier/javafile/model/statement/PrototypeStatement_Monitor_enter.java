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

public class PrototypeStatement_Monitor_enter extends PrototypeStatement {
    public String vx_name;

    @Override
    public void parse() {

        super.parse();

        vx_name = info.substring(info.indexOf(" ") + 1).trim();

    }

    @Override
    public String toString() {
        return super.toString() + "\n/* Monitor_enter -->" + vx_name + " /*";
    }

}
