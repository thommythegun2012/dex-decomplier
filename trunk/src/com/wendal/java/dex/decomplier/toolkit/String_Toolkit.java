//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wendal.java.dex.decomplier.toolkit;

import java.util.ArrayList;
import java.util.List;

public final class String_Toolkit {

    public static String parseType(String src) {
        // 参考
        // /Dex_decomplier/dex/com/wendal/dex/simple/easy/SimpleClass_fields.dump.txt
        String tmp = "";

        // 如果src以[开头,表示其为一个数组
        String srcA = src.replaceAll("\\[", "");
        int tt = countC(src, 0);

        if ("I".equals(srcA))
            tmp = "int";
        if ("S".equals(srcA))
            tmp = "short";
        if ("J".equals(srcA))
            tmp = "long";
        if ("Z".equals(srcA))
            tmp = "boolean";
        if ("C".equals(srcA))
            tmp = "char";
        if ("F".equals(srcA))
            tmp = "float";
        if ("D".equals(srcA))
            tmp = "double";
        if ("B".equals(srcA))
            tmp = "byte";

        // 如果上述还是没有匹配,则src本身就是类名
        if (tmp.equals("")) {
            tmp = srcA;
            if(srcA.startsWith("L")){
                tmp = srcA.substring(1);
            }
        }

        for (int i = 0; i < tt; i++) {
            tmp += "[]";
        }

        // System.out.println(tmp);
        return tmp;
    }
    
    public static List<String> parseParameterList(String p_str) {
        List<String> list = new ArrayList<String>();
        
        String [] p_tmps = p_str.split(";");
        for (String p_tmp : p_tmps) {
            String current_p = "";
            String current_p_tmp = p_tmp;
            while(current_p_tmp.length() > 0){
                
                if(current_p_tmp.startsWith("L")){
                    current_p = current_p_tmp.substring(1).replaceAll("/", ".") + current_p;
                    list.add(current_p);
                    break;
                }
                
                String srcA = current_p_tmp.substring(0,1);

                if ("I".equals(srcA))
                    current_p = "int" + current_p;
                if ("S".equals(srcA))
                    current_p = "short"+ current_p;
                if ("J".equals(srcA))
                    current_p = "long"+ current_p;
                if ("Z".equals(srcA))
                    current_p = "boolean"+ current_p;
                if ("C".equals(srcA))
                    current_p = "char"+ current_p;
                if ("F".equals(srcA))
                    current_p = "float"+ current_p;
                if ("D".equals(srcA))
                    current_p = "double"+ current_p;
                if ("B".equals(srcA))
                    current_p = "byte"+ current_p;
                
                if("[".equals(srcA)){
                    current_p += "[]";
                }
                
                if(current_p.startsWith("[]")){
                    ;
                }else{
                    list.add(current_p);
                    current_p = "";
                }
                current_p_tmp = current_p_tmp.substring(1);
            }
        }
        
        
        return list;
    }
    

    private static int countC(String src, int count) {
        // String tmp = "[]";
        if (src.startsWith("[")) {
            count++;
            return countC(src.substring(1), count);
        } else {
            return count;
        }
    }

    public static String parseSingleClassName(String src_srt) {
            String value_temp = src_srt.trim().replaceAll("'", "");
    //        String value_temp2 = value_temp.replaceAll(";", "");
    //        value_temp2 = value_temp2.replaceAll("\\(", "");
    //        value_temp2 = value_temp2.replaceAll("\\)", "");
    //        if(value_temp2.startsWith("IL")){
    //            value_temp2 = value_temp2.substring(2);
    //        }
            if(value_temp.startsWith("L")){
                value_temp = value_temp.substring(1);
            }
            return value_temp.replaceAll("/", ".");
        }

    
    public static String join(Object [] obj_list , String sp) {
        return "";
    }
}
