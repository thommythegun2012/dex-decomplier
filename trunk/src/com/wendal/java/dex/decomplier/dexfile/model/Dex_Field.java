package com.wendal.java.dex.decomplier.dexfile.model;

public class Dex_Field {
    String type;
    String name;
    String access_flags;
    
    public Dex_Field(String src_name , String src_type , String src_access) {
        String[] data_array = src_name.split(":");
        this.name = data_array[1].replaceAll("'", "").trim();
        
        String[] data_array_2 = src_type.split(":");
            this.type = Dex_AbstractClass.parseSingleClassName(data_array_2[1]);
        String[] data_array_3 = src_access.split(":");
        this.access_flags = data_array_3[1];
    }
    
    @Override
    public String toString() {
        return "<"+access_flags+"> #"+type + "# "+name;
    }
}
