package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_AbstractClass;

public class JavaClass {
    public String class_name;
    
    public JavaPackage class_package;
    
    /**
     * public , private , protected , or ""
     */
    public String access_flags = DEFAUFT;
    
    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";
    
    public boolean isStatic = false;
    public boolean isFinal = false;
    public boolean isAbstract = false;
    
    /**
     * class , interface , enum
     */
    public String type = CLASS;
    public static String CLASS = "class";
    public static String INTERFACE = "interface";
    public static String ENUM = "enum";
    
    
    public ArrayList<JavaField> field_list = new ArrayList<JavaField>();
    
    public ArrayList<JavaMethod> method_list = new ArrayList<JavaMethod>();
    
    private Dex_AbstractClass dex_class ;
    
    public JavaClass(Dex_AbstractClass dex_class) {
        this.dex_class = dex_class;
    }
    
    public void parse() {
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("package").append(" ");
        sb.append(class_package).append(";").append("\n\n");
        if(isStatic){
            sb.append("static").append(" ");
        }
        if(isFinal){
            sb.append("final").append(" ");
        }
        if(isAbstract){
            sb.append("abstract").append("");
        }
        sb.append(type);
        sb.append("{\n");
        for (JavaField field : field_list) {
            sb.append(field.toString()).append(";\n");
        }
        for (JavaMethod method : method_list) {
            sb.append(method.toString()).append("\n");
        }
        return sb.toString();
    }
    
}
