package com.wendal.java.dex.decomplier.dexfile.model;

import java.util.ArrayList;

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
            System.out.println("Class #" + class_index);
            System.out.println(Class_descriptor);
            System.out.println(Access_flags);
            System.out.println(Superclass);
            System.out.println(source_file);

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
                        String dest = parseSingleClassName(data_array_2[1]);
                        interface_list.add(dest);
                    }
                }
//                interface_list = if_list;
                System.out.println("Interfaces --> " + interface_list.size());
                for (String string : interface_list) {
                    System.out.println(" -- " + string);
                }
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
                System.out.println("Static_fields --> " + Static_fields_list.size());
                for (Dex_Field field : Static_fields_list) {
                    System.out.println(" -- " + field);
                }
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
                System.out.println("Instance_fields --> " + Instance_fields_list.size());
                for (Dex_Field field : Instance_fields_list) {
                    System.out.println(" -- " + field);
                }
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
                System.out.println("Direct_methods --> " + Direct_methods_list.size());
                for (Dex_Method method : Direct_methods_list) {
                    method.parse();
                    System.out.println(" -- " + method);
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
                System.out.println("Virtual_methods --> " + Virtual_methods_list.size());
                for (Dex_Method method : Virtual_methods_list) {
                    method.parse();
                    System.out.println(" -- " + method);
                }
            }

        }
        System.out.println("----------------------------");
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

    static String parseSingleClassName(String src_srt) {
        String value_temp = src_srt.trim().replaceAll("'", "");
        String value_temp2 = value_temp.replaceAll(";", "");
//        value_temp2 = value_temp2.replaceAll("\\(", "");
//        value_temp2 = value_temp2.replaceAll("\\)", "");
//        if(value_temp2.startsWith("IL")){
//            value_temp2 = value_temp2.substring(2);
//        }
        if(value_temp2.startsWith("L")){
            value_temp2 = value_temp2.substring(1);
        }
        return value_temp2.replaceAll("/", ".");
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

    
}
