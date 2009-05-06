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

public class PrototypeStatement_ReturnVoid extends PrototypeStatement {

    public PrototypeStatement_ReturnVoid(String line_index) {
//        this.type = PrototypeStatement.RETRUN_VOID;
        this.line_index = line_index;
    }
    
    public PrototypeStatement_ReturnVoid() {
    }

    @Override
    public String toString() {
        return "return";
    }
}
