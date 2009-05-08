//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.converter.DexField2JavaField;
import com.wendal.java.dex.decomplier.converter.DexMethod2JavaMethod;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_AbstractClass;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_Field;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method;

public class JavaClass {
    public String class_name;

    public JavaPackage class_package;

    /**
     * public , private , protected , or ""
     */
    public String access_flag = DEFAUFT;

    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";

    public boolean isStatic = false;
    public boolean isFinal = false;
    public boolean isAbstract = false;

    public String superClass = "";

    public ArrayList<String> interface_list = new ArrayList<String>();

    /**
     * class , interface , enum
     */
    public String type = CLASS;
    public static String CLASS = "class";
    public static String INTERFACE = "interface";
    public static String ENUM = "enum";

    public ArrayList<JavaField> field_list = new ArrayList<JavaField>();

    public ArrayList<JavaMethod> method_list = new ArrayList<JavaMethod>();

    private Dex_AbstractClass dex_class;

    public JavaClass(Dex_AbstractClass dex_class) {
        this.dex_class = dex_class;
    }

    public void parse() {
        parseType(); // 判断是一个class , Interface ,或者 enum
        parseClassName();
        if (this.type.equals(CLASS)) {
            parse4Class();
        } else if (this.type.equals(INTERFACE)) {
            parse4Interface();
        } else if (this.type.equals(ENUM)) {
            parse4Enum();
        }
    }

    private void parseType() {
        String access_type = this.dex_class.getAccess_flags();
        if (access_type.indexOf("INTERFACE") > -1) {
            this.type = INTERFACE;
        } else if (access_type.indexOf("ENUM") > -1) {
            this.type = ENUM;
        }

    }

    private void parseClassName() {
        String src = dex_class.getClass_descriptor();

        int index = src.lastIndexOf(".");

        if (index > -1) {
            this.class_package = new JavaPackage();
            class_package.name = src.substring(0, index);
            class_name = src.substring(index + 1);
        }

    }

    private void parse4Class() {
        // 处理父类
        String super_class = this.dex_class.getSuperclass();
        if ("java.lang.Object".equals(super_class)) {
            ;
        } else {
            this.superClass = super_class;
        }
        
        // 处理访问控制符
        String af = dex_class.getAccess_flags();
        if (af.indexOf("PUBLIC") > -1) {
            this.access_flag = PUBLIC;
        } else if (af.indexOf("PRIVATE") > -1) {
            this.access_flag = PRIVATE;
        } else if (af.indexOf("PROTECTED") > -1) {
            this.access_flag = PROTECTED;
        }

        // 处理implementat的接口
        interface_list.addAll(dex_class.getInterface_list());

        // 处理静态变量
        for (Dex_Field dex_Field : dex_class.getStatic_fields_list()) {
            JavaField temp = DexField2JavaField.parseDex(dex_Field);
            temp.isStatic = true;
            field_list.add(temp);
        }

        // 处理实例变量
        for (Dex_Field dex_Field : dex_class.getInstance_fields_list()) {
            JavaField temp = DexField2JavaField.parseDex(dex_Field);
            // temp.isStatic = false;
            field_list.add(temp);
        }

        // 处理Direct_methods
        {
            for (Dex_Method dex_Method : dex_class.getDirect_methods_list()) {
                JavaMethod temp = DexMethod2JavaMethod.parseDex(dex_Method);
                if(temp.name.equals("<init>")){
                    temp.name = this.class_name;
                }else if(temp.name.equals("<clinit>")){
                    temp.name = this.class_name;
                }
                method_list.add(temp);
            }
        }

        // 处理Virtual_methods
        {
            for (Dex_Method dex_Method : dex_class.getVirtual_methods_list()) {
                JavaMethod temp = DexMethod2JavaMethod.parseDex(dex_Method);
                if(temp.name.equals("<init>")){
                    temp.name = this.class_name;
                }else if(temp.name.equals("<clinit>")){
                    temp.name = this.class_name;
                }
                method_list.add(temp);
            }
        }

//        System.out.println(this);
    }

    private void parse4Interface() {

    }

    private void parse4Enum() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (class_package != null) {
            sb.append("package").append(" ");
        }
        sb.append(class_package).append(";").append("\n\n");
        sb.append(access_flag);
        sb.append(" ");
        if (isStatic) {
            sb.append("static").append(" ");
        }
        if (isFinal) {
            sb.append("final").append(" ");
        }else if (isAbstract) {
            sb.append("abstract").append(" ");
        }
        sb.append(type).append(" ");
        sb.append(class_name).append(" ");
        if ("".equals(superClass)) {
            ;
        } else {
            sb.append("extends ").append(superClass).append(" ");
        }

        if (interface_list.size() > 0) {
            sb.append("implements").append(" ");
            for (int i = 0; i < interface_list.size(); i++) {
                sb.append(interface_list.get(i));
                if(i < interface_list.size() -1){
                    sb.append(" ,");
                }
            }
        }

        sb.append("{\n");
        for (JavaField field : field_list) {
            sb.append(field.toString()).append(";\n");
        }
        for (JavaMethod method : method_list) {
            sb.append(method.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
