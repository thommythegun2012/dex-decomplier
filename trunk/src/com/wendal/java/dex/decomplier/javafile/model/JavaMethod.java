package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Check_Cast;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Const;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Const_class;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_If;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Instance_Of;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Invoke_Direct;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Invoke_Static;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move_Exception;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move_Result;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnVoid;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnX;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Throw;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_aget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_aput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_array_length;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop_2addr;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop_lit16;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_cmp;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_iget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_iput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_new_array;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_new_instance;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_nop;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_unop;
import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class JavaMethod {

    /**
     * public , private , protected , or ""
     */
    public String access_flags = DEFAUFT;

    public boolean isStatic = false;
    public boolean isFinal = false;
    public boolean isAbstract = false;

    public String name;

    public static String PUBLIC = "public";
    public static String PRIVATE = "private";
    public static String PROTECTED = "protected";
    public static String DEFAUFT = "";

    public String return_value = "void";

    public ArrayList<String> parameter_list = new ArrayList<String>();

    public ArrayList<String> src_code = new ArrayList<String>(100);

    private Dex_Method dex_method;

    private boolean isStaticConstructor = false;
    
    private ArrayList<PrototypeStatement> ps_list;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(access_flags).append(" ");
        if (isStatic) {
            sb.append("static").append(" ");
        }
        if (isFinal) {
            sb.append("final").append(" ");
        }
        sb.append(return_value).append(" ");
        if (isStaticConstructor) {
            ;
        } else {
            sb.append(name);
            sb.append("(");
            for (int i = parameter_list.size() - 1; i > -1; i--) {
                sb.append(parameter_list.get(i));
                if (i > 0) {
                    sb.append(" ,");
                }
            }
            sb.append(")");
        }
        sb.append("\n");
        if (isAbstract) {
            ;
        } else {
            sb.append("{\n");
//            for (String str : src_code) {
//                sb.append(str).append(";\n");
//            }
            for (PrototypeStatement ps : ps_list) {
                sb.append(ps.toString()).append(";\n");
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

    public JavaMethod(Dex_Method dex_method) {
        this.dex_method = dex_method;
    }

    public void parse() {
        // 处理名字
        this.name = this.dex_method.getName();
        // 处理控制符
        String access_flag = this.dex_method.getAccess_flag();
        if (access_flag.indexOf("ABSTRACT") > -1) {
            this.isAbstract = true;
        } else {
            if (access_flag.indexOf("FINAL") > -1) {
                this.isFinal = true;
            }
            if (access_flag.indexOf("STATIC") > -1) {
                this.isStatic = true;
            }
        }

        // 处理访问控制符
        if (access_flag.indexOf("PUBLIC") > -1) {
            this.access_flags = PUBLIC;
        } else if (access_flag.indexOf("PRIVATE") > -1) {
            this.access_flags = PRIVATE;
        } else if (access_flag.indexOf("PROTECTED") > -1) {
            this.access_flags = PROTECTED;
        }

        // 获取返回值
        {
            String src_type = this.dex_method.getType();
            String tmp_type = src_type.substring(src_type.indexOf(")") + 1);
            if (tmp_type.equals("V")) {
                ;
            } else {
                this.return_value = String_Toolkit.parseType(tmp_type)
                        .replaceAll(";", "");
            }
            // 如果包含<init>,则表示其为构造方法,如果为<clinit>则为静态块
            if (this.name.indexOf("<init>") > -1) {
                this.return_value = "";
            } else if (this.name.indexOf("<clinit>") > -1) {
                this.return_value = "";
                this.isStaticConstructor = true;
            }
        }
        // 处理parameter列表
        {
            String src_type = this.dex_method.getType();
            String tmp_type = src_type.substring(1, src_type.indexOf(")"))
                    .trim();

            if (tmp_type.length() < 1) {
                ;// 表示该方法是没有带参数的
            } else {
                // System.out.println("------------------------->>> "+
                // tmp_type);
                ArrayList<LocalVar> cv_list = this.dex_method.getLocals_list();
                String tmp_str = "";
                int count_parameter = 0;
                for (int i = cv_list.size() - 1; i > -1; i--) {
                    tmp_str = cv_list.get(i).src_name + tmp_str;
                    count_parameter++;
                    String parameter_type = String_Toolkit.parseType(
                            cv_list.get(i).type).replaceAll(";", "");
                    String parameter = parameter_type + " "
                            + cv_list.get(i).name;
                    parameter_list.add(parameter);
                    if (tmp_str.equals(tmp_type)) {
                        break;
                    }
                }
            }
        }
        parseOpcode();
    }
    private void parseOpcode() {
        ArrayList<String> opcode_src = this.dex_method.getOpcodes_list();
        opcode_src.remove(0);//去掉第一行,即方法签名
        
        
        ps_list = PrototypeStatement.newInstanceList(opcode_src);
        //登记全部return-void语句,只记录line_index
        ArrayList<String> ps_returnvoid_list = new ArrayList<String>();
        for (PrototypeStatement ps : ps_list) {
            if (ps instanceof PrototypeStatement_ReturnVoid) {
                ps_returnvoid_list.add(ps.line_index);
            }
        }
        //替换掉指向return-void的goto语句
        //System.out.println(ps_returnvoid_list.size());
        for (int i = 0;i < ps_list.size();i++) {
            PrototypeStatement ps = ps_list.get(i);
            if (ps instanceof PrototypeStatement_Goto) {
                PrototypeStatement_Goto ps_goto = (PrototypeStatement_Goto)ps;
                for (String string : ps_returnvoid_list) {
                    if(string.equals(ps_goto.goto_line_index)){
                        //替换成return-void
                        int index = ps_list.indexOf(ps);
                        ps_list.set(index, PrototypeStatement.convertTotype(ps, PrototypeStatement_ReturnVoid.class));
                        
//                        System.out.println("--->替换跳转return-void: " + ps_goto.line_index+" --> "+ps_goto.goto_line_index);
                        break;
                    }
                }
            }
        }
        
        for (int i = 0;i < ps_list.size();i++) {
            PrototypeStatement ps = ps_list.get(i);
            
            if(ps instanceof PrototypeStatement_ReturnVoid){
                continue;
            }
            
            if(ps instanceof PrototypeStatement_Goto){
                continue;
            }

            //处理Return-X
            if(ps.opcodes.startsWith(OpCode_List.Op_Return_Object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Return_V) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Return_Wide)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_ReturnX.class));
                continue;
            }
            
           //处理CheckCase
            if(ps.opcodes.startsWith(OpCode_List.Op_CheckCase)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps,PrototypeStatement_Check_Cast.class));
                continue;
            }
            
            //处理Const
            if(ps.opcodes.startsWith(OpCode_List.Op_Conset4) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_V) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_high16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_wide16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_wide32) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Conset_wide_high16)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Const.class));
                continue;
            }
            
            if(ps.opcodes.startsWith(OpCode_List.Op_Conset_class)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Const_class.class));
                continue;
            }
            
            //处理invoke static
            if(ps.opcodes.startsWith(OpCode_List.Op_Invoke_Static)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Invoke_Static.class));
                continue;
            }
            
            //处理invoke_kinds
            if(ps.opcodes.startsWith(OpCode_List.Op_Invoke_Direct)
                    || ps.opcodes.startsWith(OpCode_List.Op_Invoke_Virtual)
                    || ps.opcodes.startsWith(OpCode_List.Op_Invoke_Interface)
                    || ps.opcodes.startsWith(OpCode_List.Op_Invoke_Super)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Invoke_Direct.class));
                continue;
            }
            
            //处理Move-Result
            if(ps.opcodes.startsWith(OpCode_List.Op_Move_Result) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_Result_Wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_Result_Object)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Move_Result.class));
                continue;
            }
            
          //处理Move-Exception
            if(ps.opcodes.startsWith(OpCode_List.Op_Move_Exception) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Move_Exception.class));
                continue;
            }
            
            //处理Move
            if(ps.opcodes.startsWith(OpCode_List.Op_Move) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_from16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_wide_from16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_wide_16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_object_16) ||
                    ps.opcodes.startsWith(OpCode_List.Op_Move_object_from16)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Move.class));
                continue;
            }
            
            //处理instance of
            if(ps.opcodes.startsWith(OpCode_List.Op_Instance_of)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Instance_Of.class));
                continue;
            }
            
            //处理new_instance
            if(ps.opcodes.startsWith(OpCode_List.Op_new_instance)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_new_instance.class));
                continue;
            }
            
            //处理Throw
            if(ps.opcodes.startsWith(OpCode_List.Op_Throw)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_Throw.class));
                continue;
            }
            
            //处理if
            if(ps.opcodes.startsWith("3")){
                String tmp_str = ps.opcodes.substring(0,2);
                int op_int = Integer.parseInt(tmp_str, 16);
                if(op_int >= 0x32 && op_int <= 0x3d){
                    ps_list.set(ps_list.lastIndexOf(ps), new PrototypeStatement_If(ps));
                    continue;
                }
            }
            
            //处理sget
            if(ps.opcodes.startsWith(OpCode_List.Op_sget) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.op_sget_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sget_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_sget.class));
                continue;
            }
            
          //处理sput
            if(ps.opcodes.startsWith(OpCode_List.Op_sput) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.op_sput_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_sput_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_sput.class));
                continue;
            }
            
          //处理iget
            if(ps.opcodes.startsWith(OpCode_List.Op_iget) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.op_iget_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iget_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_iget.class));
                continue;
            }
            
          //处理iput
            if(ps.opcodes.startsWith(OpCode_List.Op_iput) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_iput_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_iput.class));
                continue;
            }
            
            
          //处理aget
            if(ps.opcodes.startsWith(OpCode_List.Op_aget) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.op_aget_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aget_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_aget.class));
                continue;
            }
            
          //处理aput
            if(ps.opcodes.startsWith(OpCode_List.Op_aput) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_wide) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_object) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_short) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_boolean) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_byte) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_char) ||
                    ps.opcodes.startsWith(OpCode_List.Op_aput_short) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_aput.class));
                continue;
            }
            
            //处理new array
            if(ps.opcodes.startsWith(OpCode_List.Op_new_array)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_new_array.class));
                continue;
            }
            
            //处理cmp
            if(ps.opcodes.startsWith(OpCode_List.Op_cmpl_float) ||
                    ps.opcodes.startsWith(OpCode_List.Op_cmpg_float) ||
                    ps.opcodes.startsWith(OpCode_List.Op_cmpl_double) ||
                    ps.opcodes.startsWith(OpCode_List.Op_cmpg_double) ||
                    ps.opcodes.startsWith(OpCode_List.Op_cmp_long) ){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_cmp.class));
                continue;
            }
            
          //处理 array length
            if(ps.opcodes.startsWith(OpCode_List.Op_array_length)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_array_length.class));
                continue;
            }
            
          //处理 array length
            if(ps.opcodes.startsWith(OpCode_List.Op_nop)){
                ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_nop.class));
                continue;
            }
            
            //处理unop\
            {
            int op_int = Integer.parseInt(ps.opcodes.substring(0, 2), 16);
                if(op_int >= 0x7b && op_int <= 0x8f){
                    ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_unop.class));
                    continue;
            }
            }
            {
            //处理binop
            int op_int = Integer.parseInt(ps.opcodes.substring(0, 2), 16);
                if(op_int >= 0x90 && op_int <= 0xaf){
                    ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_binop.class));
                    continue;
                }
            }
            {
            //处理binnop_2addr
            int op_int = Integer.parseInt(ps.opcodes.substring(0, 2), 16);
                if(op_int >= 0xb0 && op_int <= 0xcf){
                    ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_binop_2addr.class));
                    continue;
                }
            }
            
            {
            //处理binop_lit16 binop_lit8
              int op_int = Integer.parseInt(ps.opcodes.substring(0, 2), 16);
                 if(op_int >= 0xd0 && op_int <= 0xe2){
                        ps_list.set(ps_list.lastIndexOf(ps), PrototypeStatement.convertTotype(ps, PrototypeStatement_binop_lit16.class));
                        continue;
                 }
            }
            
        }
        for (PrototypeStatement ps : ps_list) {
            if(ps.getClass().equals(PrototypeStatement.class)){
                System.err.println(ps);
            }
        }
        
//        //寻找新建对象
//        if(this.name.equals("<init>")){
//            return;
//        }
//        
//        int new_i = 0;
//        int init_i = 0;
//        for (int i = 0;i < ps_list.size();i++) {
//            PrototypeStatement ps = ps_list.get(i);
//            if(ps.opcodes == null) continue;
//            if(ps.opcodes.startsWith("22")) new_i++;
//            if(ps instanceof PrototypeStatement_Invoke_Direct){
//                if(((PrototypeStatement_Invoke_Direct) ps).method_name.equals("<init>")) init_i++;
//            }
//        }
//        System.out.println("----> "+new_i+"  -->"+init_i +"  Eq ?---->>>>" + (new_i == init_i));
    }
    
    
}
