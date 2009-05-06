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

public class PrototypeStatement_fill_array_data extends PrototypeStatement {
    public String vx_name;
    
    public String data_offset;

    @Override
    public void parse() {

        super.parse();

        vx_name = info.substring(info.indexOf(" ") + 1 , info.indexOf(",")).trim();

        data_offset = info.substring(info.indexOf(",")+1).trim();
    }

    @Override
    public String toString() {
        return super.toString() + "\n/* fill data from line_offset " + data_offset + " into "+ vx_name+" /*";
    }
}
