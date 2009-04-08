package com.wendal.java.dex.decomplier.dexfile.model;

import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class Dex_Field {
    private String type;
    private String name;
    private String access_flags;
    
    public Dex_Field(String src_name , String src_type , String src_access) {
        String[] data_array = src_name.split(":");
        this.name = data_array[1].replaceAll("'", "").trim();
        
        String[] data_array_2 = src_type.split(":");
            this.type = String_Toolkit.parseSingleClassName(data_array_2[1]).replaceAll(";", "");
        String[] data_array_3 = src_access.split(":");
        this.access_flags = data_array_3[1];
    }
    
    @Override
    public String toString() {
        return "<"+access_flags+"> #"+type + "# "+name;
    }
    
    
    /**************Get Set Methods**************************/
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(String access_flags) {
        this.access_flags = access_flags;
    }
    
    
}
