package com.wendal.java.dex.decomplier.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wendal.java.dex.decomplier.converter.Dex2Java;
import com.wendal.java.dex.decomplier.dexfile.model.DexTaken;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_AbstractClass;
import com.wendal.java.dex.decomplier.javafile.model.JavaClass;
import com.wendal.java.dex.decomplier.javafile.model.JavaMethod;
import com.wendal.java.dex.decomplier.toolkit.IO_Tool;
import com.wendal.java.dex.decomplier.toolkit.Logger;
import com.wendal.java.dex.decomplier.vm.Vm_Main;

public class DexD {
    
    private Logger log = Logger.getLogger();
    
    private CommandLineConfig cc;
    
    private File dexFile;
    
    private File destRoot;
    
    private List<String> Dex_dump_str;

    private List<Dex_AbstractClass> dexmodel_list;
    
    private List<JavaClass> javamodel_list = new ArrayList<JavaClass>();
    
    public DexD(CommandLineConfig cc) {
        this.cc = cc;
        this.dexFile = new File(cc.getDex_filepath_str());
        this.destRoot = new File(cc.getDest_filepath_str());
    }

    
    public void init() throws IOException{
//        IO_Tool.dexdump(dexFile.getPath());
        this.Dex_dump_str = IO_Tool.getFile(dexFile.getPath());
        if(cc.isVerbose()){
            log.i("DexD", "Init done.");
        }
    }
    
    public void convert2DexModel(){
        this.dexmodel_list = DexTaken.getDexClassList(this.Dex_dump_str);
        for (Dex_AbstractClass dac : this.dexmodel_list) {
            dac.parse();
        }
        if(cc.isVerbose()){
            log.i("DexD", "convert2DexModel.");
        }
    }
    
    public void convert2JavaModel(){
        for (Dex_AbstractClass dac : this.dexmodel_list) {
            JavaClass javaClass = Dex2Java.parseDex(dac);
            javamodel_list.add(javaClass);
        }
        if(cc.isVerbose()){
            log.i("DexD", "convert2JavaModel.");
        }
    }
    
    public void VM_parse(){
        for (JavaClass javaclass : javamodel_list) {
            for (JavaMethod jm : javaclass.method_list) {
                List<String> source_list = new Vm_Main().parse(jm.getPs_list(), jm.getLocals_list(), jm.getCes());
                jm.src_code = source_list;
            }
        }
        if(cc.isVerbose()){
            log.i("DexD", "VM_parse.");
        }
    }
    
    public void  reshaping(){
        //Parse Inner Class
//        List<String> known_class_name = new ArrayList<String>(javamodel_list.size());
        Map<String,JavaClass> maps = new HashMap<String, JavaClass>();
        for (JavaClass jc : javamodel_list) {
                maps.put(jc.class_package + "." + jc.class_name , jc);
        }
//        System.out.println("here");
        flag : while(true){
            for (int i = 0; i < javamodel_list.size(); i++) {
                JavaClass jc = javamodel_list.get(i);
                if(jc.class_name.indexOf("$") > -1){
                    String tmp_str = jc.class_package +"."+ jc.class_name;
                    String father_classname = tmp_str.substring(0, tmp_str.lastIndexOf("$"));
                    JavaClass jc_father = maps.get(father_classname);
                    if(jc_father !=null){
                        jc.class_name = tmp_str.substring(tmp_str.lastIndexOf("$")+1);
                        jc_father.addInnerClass(jc);
                        javamodel_list.remove(jc);
//                        System.out.println("--->>>");
                        continue flag;
                    }
                }
            }
            break;
        }
    }
    
    /**
     * Just for development use
     */
    private static boolean print2Console = false; 
    
    public void outputSource(){
        for (JavaClass jc : javamodel_list) {
            if(print2Console){
                log.i("\n/** Class Source: */\n", jc.toString());
            }
            try {
                IO_Tool.write2File(destRoot.getPath(), jc.class_package.name, jc.class_name+".java", jc.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.i("DexD", "ALL done.");
    }


    public List<String> getDex_dump_str() {
        return Dex_dump_str;
    }


    public void setDex_dump_str(List<String> dex_dump_str) {
        Dex_dump_str = dex_dump_str;
    }
}
