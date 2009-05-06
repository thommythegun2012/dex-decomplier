//:~
/**
 * $Id$
 * 
 * @author $Author$
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.dexfile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class Dex_Method {
    
    private String name;
    private String type; //返回值
    private String access_flag;
    
//    private ArrayList<String> code_list;

//    private int registers;
//    private int ins;
//    private int outs;
//    private String insns_size;
    
    private ArrayList<String> opcodes_list = new ArrayList<String>();

    private ArrayList<String> catches_list = new ArrayList<String>();
//    private ArrayList<String> positions_list = new ArrayList<String>();
    private ArrayList<LocalVar> locals_list = new ArrayList<LocalVar>();
    
    private ArrayList<String> method_data;
    
    public void parse() {
        if(method_data != null){
            {
                String[] data_array = method_data.get(0).split(":");
                this.name = data_array[1].replaceAll("'", "").trim();
            }
            {
                
                String[] data_array = method_data.get(1).split(":");
                this.type = String_Toolkit.parseSingleClassName(data_array[1]);
            }
            {
                String[] data_array = method_data.get(2).split(":");
                this.access_flag = data_array[1];
            }
                
            {
                //从第9行开始为opcodes
                for (int i = 8; i < method_data.size(); i++) {
                    if(method_data.get(i).startsWith(StringTaken_Dex.catches)) break;
                    opcodes_list.add(method_data.get(i));
                }
            }
            
            {
                catches_list.clear();
                boolean flag = false;
                for (int i = 8; i < method_data.size(); i++) {
                    if(method_data.get(i).startsWith(StringTaken_Dex.catches)) {
                        flag = true;
                        continue;
                    }
                    if (method_data.get(i).startsWith(StringTaken_Dex.locals)) {
                        break;
                    }
                    if(flag){
                        catches_list.add(method_data.get(i));
                    }
                } 
            }
            
            //处理locals
            {
                locals_list.clear();
                boolean flag = false;
                for (int i = 8; i < method_data.size(); i++) {
                    String tmp = method_data.get(i);
                    if(tmp.startsWith(StringTaken_Dex.locals)) {
                        flag = true;
                        continue;
                    }
                    if(tmp.startsWith(StringTaken_Dex.source_file_Taken)){
                        break;
                    }
                    if(flag && tmp.trim().length() > 0){
                        locals_list.add(new LocalVar(tmp));
                    }
                } 
            }
        }
    }
    
    public ArrayList<String> getMethod_data() {
        return method_data;
    }

    public void setMethod_data(ArrayList<String> method_data) {
        this.method_data = method_data;
    }

    @Override
    public String toString() {
        return "<"+access_flag+"> #"+type + "# "+name + " <-- "+opcodes_list.size();
    }
    
    /**************Get Set Methods**************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess_flag() {
        return access_flag;
    }

    public void setAccess_flag(String access_flag) {
        this.access_flag = access_flag;
    }

    public ArrayList<String> getOpcodes_list() {
        return opcodes_list;
    }

    public void setOpcodes_list(ArrayList<String> opcodes_list) {
        this.opcodes_list = opcodes_list;
    }

    public ArrayList<String> getCatches_list() {
        return catches_list;
    }

    public void setCatches_list(ArrayList<String> catches_list) {
        this.catches_list = catches_list;
    }

    public ArrayList<LocalVar> getLocals_list() {
        return locals_list;
    }

    public void setLocals_list(ArrayList<LocalVar> locals_list) {
        this.locals_list = locals_list;
    }
    
    public static class LocalVar{
        public int reg;
        public String name;
        public String type;
        
        public String src_name;
        
        public LocalVar(String data) {
            //Get regs
            int index = data.indexOf("reg=");
            {
                String tmp = data.substring(index + 4);
                String tmp2 =  tmp.substring(0, tmp.indexOf(" "));
                this.reg = Integer.parseInt(tmp2);
            }
            {
                String tmp_str = "reg="+reg;
                String tmp = data.substring(index + tmp_str.length()+1);
                String tmp2 =  tmp.substring(0, tmp.indexOf(" "));
                this.name = tmp2;
            }
            {
                String tmp_str = "reg="+reg+" "+name;
                String tmp = data.substring(index + tmp_str.length()+ 1).trim();
                this.src_name = tmp.replaceAll("/", ".");
//                System.out.println("---+++>>>>>>>>>>>>>> "+src_name);
                this.type = String_Toolkit.parseSingleClassName(tmp).replace(';', ' ').trim();
//                System.out.println("++++>+++>>>>+++>>>"+type);
            }
        }
    }
}
