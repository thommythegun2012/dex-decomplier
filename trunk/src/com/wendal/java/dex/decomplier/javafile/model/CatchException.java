package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;
import java.util.List;

public class CatchException {
    
    private List<String> src_str_list ;
    
    public String start_offset;
    
    public String end_offset;
    
    public List<CatchException_CatchPoint> points = new ArrayList<CatchException_CatchPoint>();
    
    public CatchException(String src_str_first) {
        this.src_str_list.add(src_str_first);
    }

    public void parseAndAdd(String src_point) {
        String [] data = src_point.split("-->");
        points.add(new CatchException_CatchPoint(data[0].trim(),data[1].trim()));
    }
}
