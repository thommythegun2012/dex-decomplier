package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnVoid;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_String;

public class PrototypeStatement {

    public String dex_offset;
    public String opcodes;
    public String line_index;

    public String info;

    public String note;
    
    protected PrototypeStatement(){}

    /**
     * 已知格式 dex_offset: opcodes |line_index info note
     * 
     * @param src_statement
     * @return
     */
    public static ArrayList<PrototypeStatement> newInstanceList(
            ArrayList<String> opcode_src) {
        ArrayList<PrototypeStatement> ps_list = new ArrayList<PrototypeStatement>();
        for (int i = 0; i < opcode_src.size(); i++) {
            String src_statement = opcode_src.get(i);
            PrototypeStatement ps = new PrototypeStatement();

            // 按照现在的理解,前6位为偏移
            ps.dex_offset = src_statement.substring(0, 6);

            int index_1 = src_statement.indexOf("|");

            ps.opcodes = src_statement.substring(7, index_1).trim();
            
            //String的opcode为1a03
            //由于String本身可能包含换行,所以要额外处理
            if(ps.opcodes.startsWith("1a")){
                PrototypeStatement ps_new = new PrototypeStatement_String(ps);
//                ps_new.dex_offset = ps.dex_offset;
//                ps_new.opcodes = ps.opcodes;
                ps = ps_new;
                int len = src_statement.indexOf("\"");
                int tag_i = i;
                String src_tmp = src_statement.substring(len);
                while(true){
                    if(src_tmp.matches("\\\".*\\\" // string@[0-9a-z]{4}$")){
                        break;
                    }else{
                        tag_i++;
                        src_tmp+=opcode_src.get(tag_i);
                    }
                }
                if(tag_i != i){
                    for (; i < tag_i; i++) {
                        src_statement+= "\n";
                        src_statement+=opcode_src.get(i);
                    }
                }
            }

            ps.line_index = src_statement.substring(index_1 + 1, index_1 + 5);

            if (src_statement.lastIndexOf("//") > -1) {
                ps.info = src_statement.substring(index_1 + 6, src_statement
                        .lastIndexOf("//")).trim();
                ps.note = src_statement.substring(src_statement
                        .lastIndexOf("//") + 3).trim();
            }else{
                ps.info = src_statement.substring(index_1 + 6).trim();
            }
            
            //处理类型
            if(ps.opcodes.startsWith(OpCode_List.Op_Goto)
                    || ps.opcodes.startsWith(OpCode_List.Op_Goto16)){
                ps = PrototypeStatement.convertTotype(ps, PrototypeStatement_Goto.class);
            }
            if(ps.opcodes.startsWith(OpCode_List.Op_Return_Void)){
                ps = new PrototypeStatement_ReturnVoid(ps.line_index);
            }
            if(ps instanceof PrototypeStatement_String){
                ((PrototypeStatement_String) ps).parse();
            }
            ps_list.add(ps);
        }
        return ps_list;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("/* ");
        sb.append(line_index).append(" :  ");
        sb.append(this.info);
        if(this.info == null){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dex_offset);
        }
        sb.append(" */");
        return sb.toString();
    }
    
    public static PrototypeStatement convertTotype(PrototypeStatement ps_src , Class<? extends PrototypeStatement> totype) {
        try {
            PrototypeStatement ps_new = totype.newInstance();
            ps_new.dex_offset = ps_src.dex_offset;
            ps_new.opcodes = ps_src.opcodes;
            ps_new.info = ps_src.info.trim();
            ps_new.line_index = ps_src.line_index;
            ps_new.note = ps_src.note;
            ps_new.parse();
            return ps_new;
        } catch (InstantiationException e) {
            
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            
            e.printStackTrace();
        }
        return null;
    }
    
    public void parse() {
        ;
    }
}
