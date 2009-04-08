package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method;

public class JavaMethod {
    
    /**
     * public , private , protected , or ""
     */
    public String access_flags = DEFAUFT;
    
    public boolean isStatic = false;
    public boolean isFinal = false;
    
    public String name;

    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";
    
    public String return_value = "Void";
    
    public ArrayList<String> parameter_list = new ArrayList<String>();
    
    public ArrayList<String> src_code = new ArrayList<String>(100);
    
    private Dex_Method dex_method;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(access_flags).append(" ");
        if(isStatic){
            sb.append("static").append(" ");
        }
        if(isFinal){
            sb.append("final").append(" ");
        }
        sb.append(return_value).append(" ");
        sb.append(name).append("()");
        
        sb.append("\n");
        sb.append("{\n");
        for (String str : src_code) {
            sb.append(str).append(";\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public JavaMethod(Dex_Method dex_method) {
        this.dex_method = dex_method;
    }
    
    public void parse(){

    }
}
