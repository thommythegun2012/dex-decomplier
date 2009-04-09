package com.wendal.dex.simple.easy.returnvoid;

public class SimpleReturnVoid {
    
    void getA(int p){
        int i = 1;
        i++;
        int b = 100;
        i += b;
        if(i == p){
            System.out.println(p);
            return;
        }else{
            System.out.println(i + b);
            return;
        }
    }

}
