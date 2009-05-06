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

public class PrototypeStatement_Goto extends PrototypeStatement {

    public String goto_line_index;

    public PrototypeStatement_Goto() {

    }

    @Override
    public void parse() {
        super.parse();
        if (info.startsWith("goto ")) {
            this.goto_line_index = info.substring(info.indexOf(" ")).trim();
        }else{
            String tmp_goto_index = info.substring(info.indexOf("#")+1).trim().substring(4);
            int tmp_index = Integer.parseInt(tmp_goto_index, 16) - 0x10000;
            int my_index = Integer.parseInt(line_index,16);
            String tmp_go = Integer.toHexString(my_index + tmp_index);
            while(tmp_go.length() < 4){
                tmp_go = "0"+tmp_go;
            }
            goto_line_index = tmp_go;
        }

    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Goto : --> " +goto_line_index;
    }
}
