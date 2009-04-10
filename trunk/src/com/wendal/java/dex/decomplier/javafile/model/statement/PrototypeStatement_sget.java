package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

/**
 * ¾²Ì¬×Ö¶ÎµÄ»ñÈ¡!
 * @author zcchen
 *
 */
public class PrototypeStatement_sget extends PrototypeStatement {
    public String vx_name;

    public String field__name;
    
    public String class_name;
    
    public String vx_type;

    @Override
    public void parse() {

        super.parse();
        
        vx_name = info.substring(info.indexOf(" ")+1, info.indexOf(",")).trim();
        
        String tmp_str = info.substring(info.indexOf(", L")+1, info.indexOf(";"));
        class_name = String_Toolkit.parseSingleClassName(tmp_str).replaceAll(";", "");
        
        field__name = info.substring(info.indexOf(".")+1, info.indexOf(":"));
        

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "\n" + vx_name + " = " + class_name + "."+field__name;
    }
}
