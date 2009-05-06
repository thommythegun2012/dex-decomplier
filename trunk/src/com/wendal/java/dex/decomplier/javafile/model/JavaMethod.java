//:~
/**
 * $Id: $
 * 
 * @author $Author: $
 * @version $revision: $
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model;

import java.util.ArrayList;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method.LocalVar;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnVoid;
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

            String str_tmp = ps.opcodes.substring(0, 2);
            Class<? extends PrototypeStatement> ps_class = OpCode_List.getByOpcode(str_tmp);
            if(ps_class != null){
                ps_list.set(i, PrototypeStatement.convertTotype(ps, ps_class));
            }
            
        }
        for (PrototypeStatement ps : ps_list) {
            if(ps.getClass().equals(PrototypeStatement.class)){
                throw new RuntimeException("Some unknown opcode found!");
            }
        }
        
    }
    
    
}
