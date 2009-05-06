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

public class PrototypeStatement_aput  extends PrototypeStatement{
    public String vx_name;
    
    public String vy_name;

    public String vz_name;

    @Override
    public void parse() {

        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();
        
        vy_name = info.substring(info.indexOf(", ")+2, info.lastIndexOf(","));
        
        vz_name = info.substring(info.lastIndexOf(",")+1).trim();
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "\n"   + vy_name + "[" + vz_name +"]"+" = " + vx_name  ;
    }

}
