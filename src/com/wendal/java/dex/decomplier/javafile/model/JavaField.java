package com.wendal.java.dex.decomplier.javafile.model;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Field;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class JavaField {
    
    /**
     * public , private , protected , or ""
     */
    public String access_flags = DEFAUFT;

    public boolean isStatic = false;
    public boolean isFinal = false;
    
    public String name;
    
    public String type;

    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";
    
    private Dex_Field dex_field;
    
    public JavaField(Dex_Field dex_field) {
        this.dex_field = dex_field;
    }
    
    public void parse(){
        //�������ʿ��Ʒ�
        String access_flag = dex_field.getAccess_flags();
        if(access_flag.indexOf("PUBLIC") > -1){
            this.access_flags = PUBLIC;
        }else if(access_flag.indexOf("PRIVATE") > -1){
            this.access_flags = PRIVATE;
        }else if(access_flag.indexOf("PROTECTED") > -1){
            this.access_flags = PROTECTED;
        }
        
        if(access_flag.indexOf("FINAL") > -1){
            this.isFinal = true;
        }
        
        this.type = String_Toolkit.parseType(dex_field.getType());
        
        this.name = dex_field.getName();
    }
    
    
    
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
        sb.append(type).append(" ");
        sb.append(name);
        return sb.toString();
    }
}
