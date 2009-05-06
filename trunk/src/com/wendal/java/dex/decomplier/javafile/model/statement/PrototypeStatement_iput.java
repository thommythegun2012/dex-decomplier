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

public class PrototypeStatement_iput extends PrototypeStatement{

    public String vx_name;
    
    public String vy_name;

    public String field_name;
    
    public String class_name;
    
    public String vx_type;

    @Override
    public void parse() {

        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();
        
        vy_name = info.substring(info.indexOf(", ")+2, info.indexOf(", L"));
        
        field_name = info.substring(info.indexOf(".")+1, info.indexOf(":"));
        

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "\n"  + vy_name + "."+field_name+ " = " + vx_name;
    }
}
