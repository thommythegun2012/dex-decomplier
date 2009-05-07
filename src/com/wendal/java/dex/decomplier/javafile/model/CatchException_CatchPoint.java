package com.wendal.java.dex.decomplier.javafile.model;

public class CatchException_CatchPoint {
    
    private String class_name;
    
    private String insert_point_offset;
    
    

    public CatchException_CatchPoint(String class_name,
            String insert_point_offset) {
        super();
        this.class_name = class_name;
        this.insert_point_offset = insert_point_offset;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getInsert_point_offset() {
        return insert_point_offset;
    }

    public void setInsert_point_offset(String insert_point_offset) {
        this.insert_point_offset = insert_point_offset;
    }

}
