package com.wendal.dex.jmmmm_a;

public class Jmmm_002 implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("Just print");;
        }
    }
    
    public Jmmm_002(String a) {
        ;
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        
        try {
            Thread.sleep(1000, 10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public Jmmm_002() throws Throwable {
        super();
        throw new Throwable(){

            /**
             * 
             */
            private static final long serialVersionUID = 8121868207251372602L;
            
        };
    }
    
    public void th() throws Throwable{
        Object obj = new Object(){
          @SuppressWarnings("unused")
        public void syso() {
            Jmmm_002.class.getClass();
        }  
        };
        
        System.out.println(obj);
        throw new Throwable();
    }
}
