package com.wendal.java.dex.decomplier.vm;


public class RegisterUnit {
    
    private int Type = UNKNOWN;

    public static final int CONST = 1 << 2;
    public static final int VAR = 1 << 3;
    public static final int UNKNOWN = 1 << 4;
    public static final int LV = 1 << 5;
    
    private String value ;
    
    public RegisterHistory rh = new RegisterHistory();
    
    public RegisterUnit() {
        rh.addCreateEvent();
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public void cleanHistory(){
        this.rh.clear();
    }
    
    public String getValue() {
        rh.addGETEvent();
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        addSetEvent();
    }
    
    void addSetEvent(){
        rh.addPUTEvent();
    }
    
    public void passIf(){
        rh.addIFEvent();
    }
    
    
    public void passGOTO(){
        rh.addGOTOEvent();
    }

    public void passEndIF() {
        rh.addEndIfEvent();
    }
}
