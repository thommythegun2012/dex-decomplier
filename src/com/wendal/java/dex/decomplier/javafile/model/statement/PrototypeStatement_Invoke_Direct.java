package com.wendal.java.dex.decomplier.javafile.model.statement;

import java.util.Arrays;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

public class PrototypeStatement_Invoke_Direct extends PrototypeStatement {
    
    public String object_var;
    
    public String method_name;
    
    public String [] parameters;
    
    public boolean hasRetrun = false;
    
    public PrototypeStatement_Invoke_Direct(PrototypeStatement ps) {
        this.dex_offset = ps.dex_offset;
        this.note = ps.note;
        this.info = ps.info;
        this.opcodes = ps.opcodes.trim();
        
        {
            String tmp = info.substring(info.indexOf("{") + 1, info.indexOf("}"));
            parameters = tmp.split(",");
            for (int i = 0; i < parameters.length; i++) {
                parameters[i] = parameters[i].trim();
            }
            //第一应该为object_var
            object_var = parameters[0];
//            System.out.println(parameters.length);
            if(parameters.length > 1){
                parameters = Arrays.copyOfRange(parameters, 1, parameters.length );
            }else{
                parameters = new String[]{};
            }
            
//            System.out.println(parameters.length);
            
            
            method_name = info.substring(info.indexOf(".")+1, info.indexOf(":"));
            
            if(info.trim().endsWith(")V")){
                hasRetrun = false;
            }else{
                hasRetrun = true;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
//        sb.append(super.toString()).append("\n");
        
        sb.append(object_var).append(".").append(method_name);
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
