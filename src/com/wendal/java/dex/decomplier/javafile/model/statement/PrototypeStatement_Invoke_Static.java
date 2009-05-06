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
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class PrototypeStatement_Invoke_Static extends PrototypeStatement {

    public String class_name;
    
    public String method_name;
    
    public String [] parameters;
    
    public boolean hasRetrun = false;
    
    public PrototypeStatement_Invoke_Static() {
        
    }
    
    @Override
    public void parse() {
        super.parse();
        String tmp = info.substring(info.indexOf("{") + 1, info.indexOf("}"));
        parameters = tmp.split(",");
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].trim();
        }
        
        String tmp_str = info.substring(info.indexOf(", L")+1, info.indexOf(";"));
        class_name = String_Toolkit.parseSingleClassName(tmp_str).replaceAll(";", "");
        
        method_name = info.substring(info.indexOf(".")+1, info.indexOf(":"));
        
        if(info.trim().endsWith(")V")){
            hasRetrun = false;
        }else{
            hasRetrun = true;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
//        sb.append(super.toString()).append("\n");
        
        sb.append(class_name).append(".").append(method_name);
        sb.append("(");
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i]);
            if(i < parameters.length -1){
                sb.append(" ,");
            }
        }
        sb.append(")");
//        sb.append("\n/*hasRetrun -->").append(hasRetrun).append("*/");
        return sb.toString();
    }
}
