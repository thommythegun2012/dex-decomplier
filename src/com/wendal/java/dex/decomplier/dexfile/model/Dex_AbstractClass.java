//:~
/**
 * $Id: $
 * 
 * @author $Author: $
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.dexfile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class Dex_AbstractClass {

    private int class_index;

    /**
     * 类的全名,包括包路径
     */
    private String Class_descriptor;
    /**
     * 访问控制符,如public , private , proctoed , final , static , abstract
     */
    private String Access_flags;
    /**
     * 父类
     */
    private String Superclass;
    /**
     * 已实现的接口的列表
     */
    private ArrayList<String> interface_list = new ArrayList<String>();
    /**
     * 静态字段的列表
     */
    private ArrayList<Dex_Field> Static_fields_list = new ArrayList<Dex_Field>();
    /**
     * 实例字段的列表
     */
    private ArrayList<Dex_Field> Instance_fields_list = new ArrayList<Dex_Field>();
    /**
     * 直接方法的列表
     */
    private ArrayList<Dex_Method> Direct_methods_list = new ArrayList<Dex_Method>();
    /**
     * 虚拟方法的列表
     */
    private ArrayList<Dex_Method> Virtual_methods_list = new ArrayList<Dex_Method>();
    /**
     * 源文件名,不包含路径
     */
    private String source_file;

    private ArrayList<String> dex_data;

    /**
     * 无参数的工作方法
     */
    public Dex_AbstractClass() {
    }

    /**
     * 全部数据
     */
    public void parse() {
        if (dex_data != null) {
            for (String data_str : dex_data) {
                // System.out.println(data_str);
                if (data_str.indexOf(StringTaken_Dex.Class_descriptor_Taken) > -1) {
                    parseClassDescriptor(data_str);
                    continue;
                }
                if (data_str.indexOf(StringTaken_Dex.Access_flags_Taken) > -1) {
                    parseAccessFlags(data_str);
                    continue;
                }
                if (data_str.indexOf(StringTaken_Dex.Superclass_Taken) > -1) {
                    parseSuperclass(data_str);
                    continue;
                }

                if (data_str.indexOf(StringTaken_Dex.source_file_Taken) > -1) {
                    parseSource_file(data_str);
                    continue;
                }

            }
//            System.out.println("Class #" + class_index);
//            System.out.println(Class_descriptor);
//            System.out.println(Access_flags);
//            System.out.println(Superclass);
//            System.out.println(source_file);

            // 开始处理implemente的Interface
            {
//                ArrayList<String> if_list = new ArrayList<String>();
                interface_list.clear();
                boolean flag = false;
                for (String data_str_2 : dex_data) {
                    if (data_str_2.indexOf(StringTaken_Dex.Interfaces_Taken) > -1) {
                        flag = true;
                        continue;
                    }
                    if (data_str_2.indexOf(StringTaken_Dex.Static_fields_Taken) > -1) {
                        break;
                    }
                    if (flag) {
                        String[] data_array_2 = data_str_2.split(":");
                        String dest = String_Toolkit.parseSingleClassName(data_array_2[1]).replaceAll(";", "");
                        interface_list.add(dest);
                    }
                }
//                interface_list = if_list;
//                System.out.println("Interfaces --> " + interface_list.size());
//                for (String string : interface_list) {
//                    System.out.println(" -- " + string);
//                }
            }

            // 开始处理静态字段
            {
                Static_fields_list.clear();
                boolean flag = false;
                for (int i = 0; i < dex_data.size(); i++) {
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Static_fields_Taken) > -1) {
                        flag = true;
                        continue;
                    }
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Instance_fields_Taken) > -1) {
                        break;
                    }
                    if (flag) {
                        if (dex_data.get(i).startsWith("    #")) {
                            Dex_Field dex_Field = new Dex_Field(
                                    dex_data.get(i + 1),
                                    dex_data.get(i + 2),
                                    dex_data.get(i + 3)
                                    );
                            Static_fields_list.add(dex_Field);
                        }
                    }
                }
//                System.out.println("Static_fields --> " + Static_fields_list.size());
//                for (Dex_Field field : Static_fields_list) {
//                    System.out.println(" -- " + field);
//                }
            }
            
         // 开始处理实例字段
            {
                Instance_fields_list.clear();
                boolean flag = false;
                for (int i = 0; i < dex_data.size(); i++) {
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Instance_fields_Taken) > -1) {
                        flag = true;
                        continue;
                    }
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Direct_methods_Taken) > -1) {
                        break;
                    }
                    if (flag) {
                        if (dex_data.get(i).startsWith("    #")) {
                            Dex_Field dex_Field = new Dex_Field(
                                    dex_data.get(i + 1),
                                    dex_data.get(i + 2),
                                    dex_data.get(i + 3)
                                    );
                            Instance_fields_list.add(dex_Field);
                        }
                    }
                }
//                System.out.println("Instance_fields --> " + Instance_fields_list.size());
//                for (Dex_Field field : Instance_fields_list) {
//                    System.out.println(" -- " + field);
//                }
            }
            //开始处理Direct_methods
            {
                Direct_methods_list.clear();
                Dex_Method currentMethod = null;
                ArrayList<String> data = null;
                boolean flag = false;
                for (int i = 0; i < dex_data.size(); i++) {
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Direct_methods_Taken) > -1) {
                        flag = true;
                        continue;
                    }
                    if (dex_data.get(i).indexOf(StringTaken_Dex.Virtual_methods_Taken) > -1) {
                        break;
                    }
                    if(flag && dex_data.get(i).startsWith("    #")){
                        currentMethod = new Dex_Method();
                        data = new ArrayList<String>();
                        currentMethod.setMethod_data(data);
                        Direct_methods_list.add(currentMethod);
                        continue;
                    }
                    if(flag && data != null){
                        data.add(dex_data.get(i));
                    }
                }
//                System.out.println("Direct_methods --> " + Direct_methods_list.size());
                for (Dex_Method method : Direct_methods_list) {
                    method.parse();
//                    System.out.println(" -- " + method);
                }
            }
            
          //开始处理Virtual_methods
            {
                Virtual_methods_list.clear();
                Dex_Method currentMethod = null;
                ArrayList<String> data = null;
                boolean flag = false;
                for (int i = 0; i < dex_data.size(); i++) {
                    if (dex_data.get(i).indexOf(
                            StringTaken_Dex.Virtual_methods_Taken) > -1) {
                        flag = true;
                        continue;
                    }
//                    if (dex_data.get(i).indexOf(StringTaken_Dex.Virtual_methods_Taken) > -1) {
//                        break;
//                    }
                    if(flag && dex_data.get(i).startsWith("    #")){
                        currentMethod = new Dex_Method();
                        data = new ArrayList<String>();
                        currentMethod.setMethod_data(data);
                        Virtual_methods_list.add(currentMethod);
                        continue;
                    }
                    if(flag && data != null){
                        data.add(dex_data.get(i));
                    }
                }
//                System.out.println("Virtual_methods --> " + Virtual_methods_list.size());
                for (Dex_Method method : Virtual_methods_list) {
                    method.parse();
//                    System.out.println(" -- " + method);
                }
            }

        }
//        System.out.println("----------------------------");
    }


    private void parseClassDescriptor(String str) {
        String[] str_array = str.split(":");
        if (str_array[1] != null) {
            String value_temp = str_array[1].trim().replaceAll("'", "");
            String value_temp2 = value_temp.substring(1,
                    value_temp.length() - 1);
            Class_descriptor = value_temp2.replaceAll("/", ".");
        }
    }

    private void parseAccessFlags(String str) {
        String[] str_array = str.split(":");
        if (str_array[1] != null) {
            String value_temp = str_array[1].trim();
            Access_flags = value_temp;
        }
    }

    private void parseSuperclass(String str) {
        String[] str_array = str.split(":");
        if (str_array[1] != null) {
            String value_temp = str_array[1].trim().replaceAll("'", "");
            String value_temp2 = value_temp.substring(1,
                    value_temp.length() - 1);
            Superclass = value_temp2.replaceAll("/", ".");
        }
    }

    private void parseSource_file(String str) {
        String[] str_array = str.split(":");
        if (str_array[1] != null) {
            int index_1 = str_array[1].indexOf("(");
            int index_2 = str_array[1].indexOf(")");

            source_file = str_array[1].substring(index_1 + 1, index_2);
        }
    }

    public ArrayList<String> getDex_data() {
        return dex_data;
    }

    public void setDex_data(ArrayList<String> dex_data) {
        this.dex_data = dex_data;
    }

    public int getClass_index() {
        return class_index;
    }

    public void setClass_index(int class_index) {
        this.class_index = class_index;
    }

    @Override
    public String toString() {

        return super.toString();
    }


    /**************Get Set Methods**************************/
    
    
    public String getClass_descriptor() {
        return Class_descriptor;
    }

    public void setClass_descriptor(String class_descriptor) {
        Class_descriptor = class_descriptor;
    }

    public String getAccess_flags() {
        return Access_flags;
    }

    public void setAccess_flags(String access_flags) {
        Access_flags = access_flags;
    }

    public String getSuperclass() {
        return Superclass;
    }

    public void setSuperclass(String superclass) {
        Superclass = superclass;
    }

    public ArrayList<String> getInterface_list() {
        return interface_list;
    }

    public void setInterface_list(ArrayList<String> interface_list) {
        this.interface_list = interface_list;
    }

    public ArrayList<Dex_Field> getStatic_fields_list() {
        return Static_fields_list;
    }

    public void setStatic_fields_list(ArrayList<Dex_Field> static_fields_list) {
        Static_fields_list = static_fields_list;
    }

    public ArrayList<Dex_Field> getInstance_fields_list() {
        return Instance_fields_list;
    }

    public void setInstance_fields_list(ArrayList<Dex_Field> instance_fields_list) {
        Instance_fields_list = instance_fields_list;
    }

    public ArrayList<Dex_Method> getDirect_methods_list() {
        return Direct_methods_list;
    }

    public void setDirect_methods_list(ArrayList<Dex_Method> direct_methods_list) {
        Direct_methods_list = direct_methods_list;
    }

    public ArrayList<Dex_Method> getVirtual_methods_list() {
        return Virtual_methods_list;
    }

    public void setVirtual_methods_list(ArrayList<Dex_Method> virtual_methods_list) {
        Virtual_methods_list = virtual_methods_list;
    }

    public String getSource_file() {
        return source_file;
    }

    public void setSource_file(String source_file) {
        this.source_file = source_file;
    }

    
}
