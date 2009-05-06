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

public class PrototypeStatement_ReturnX extends PrototypeStatement {
    
    public PrototypeStatement_ReturnX() {
        // TODO Auto-generated constructor stub
    }
    
    
    public String return_obj;
    
    public String obj_type;
    
    
    @Override
    public void parse() {
        super.parse();
        return_obj = info.substring(info.indexOf(" ")).trim();
        
        if(info.startsWith("return ")){
            obj_type = "V";
            return;
        }
        if(info.startsWith("return-object")){
            obj_type = "Object";
            return;
        }
        
        if(info.startsWith("return-wide")){
            obj_type = "Wide";
            return;
        }
    }

    @Override
    public String toString() {
        return "return "+return_obj;
    }
}
