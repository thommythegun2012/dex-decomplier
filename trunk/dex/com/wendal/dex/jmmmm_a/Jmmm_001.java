package com.wendal.dex.jmmmm_a;

public abstract class Jmmm_001 {

    public Jmmm_001() {
        ;
    }
    
    public Jmmm_001(String aaa) {
        ;
    }
    
    public void sayT(){
        System.out.println("Hi");
    }
    
    public String sayF(){
        System.out.println("Hi");
        return "Hi";
    }
    
    public int getI(){
        return 1;
    }
    
    public boolean setI(int i){
        if(i == 1){
            return true;
        }
        return false;
    }
    
    static{
        System.out.println("In Static\n\n\n\nHi\\Hi\\Some carry return\n\n\t here");
    }
    
    static{
        System.out.println("In Static\n\n\n\nHi\\Hi\\Some carry return\n\n\t here");
    }
    
    {
        System.out.println("In Static\n\n\n\n\n\t here");
    }
}
