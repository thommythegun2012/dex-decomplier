package com.wendal.java.dex.decomplier.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    
    private File tmpFile;
    
    private File destRoot;
    
    private List<String> Dex_dump_str;

    private List<Dex_AbstractClass> dexmodel_list;
    
    private List<JavaClass> javamodel_list = new ArrayList<JavaClass>();
    
    public DexD(CommandLineConfig cc) {
        this.cc = cc;
        this.tmpFile = new File(cc.getFilepath_str());
        this.destRoot = new File(cc.getDest_filepath_str());
    }

    
    public void init() throws IOException{
        IO_Tool.dexdump(tmpFile.getPath()+"/classes.dex");
        this.Dex_dump_str = IO_Tool.getFile(tmpFile.getPath()+"/classes.dex");
    }
    
    public void convert2DexModel(){
        this.dexmodel_list = DexTaken.getDexClassList(this.Dex_dump_str);
        for (Dex_AbstractClass dac : this.dexmodel_list) {
            dac.parse();
        }
    }
    
    public void convert2JavaModel(){
        for (Dex_AbstractClass dac : this.dexmodel_list) {
            JavaClass javaClass = Dex2Java.parseDex(dac);
            javamodel_list.add(javaClass);
        }
    }
    
    public void VM_parse(){
        for (JavaClass javaclass : javamodel_list) {
            for (JavaMethod jm : javaclass.method_list) {
                List<String> source_list = Vm_Main.parse(jm.getPs_list(), jm.getLocals_list(), jm.getCes());
                jm.src_code = source_list;
            }
        }
    }
    
    public void  reshaping(){
        
    }
    
    public void outputSource(){
        for (JavaClass jc : javamodel_list) {
            log.i("\n/** Class Source: */\n", jc.toString());
        }
    }


    public List<String> getDex_dump_str() {
        return Dex_dump_str;
    }


    public void setDex_dump_str(List<String> dex_dump_str) {
        Dex_dump_str = dex_dump_str;
    }
}
