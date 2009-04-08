package com.wendal.java.dex.decomplier.javafile.model;

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
