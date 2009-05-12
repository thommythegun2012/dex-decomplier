/**
 * 
 */
package com.wendal.java.dex.decomplier.dexfile.model;

import com.wendal.java.dex.decomplier.toolkit.String_Toolkit;

public class LocalVar{
        public int reg;
        public String name;
        public String type;
        
        public String src_name;

        public int start_line_index;
        public int end_line_index;
        
        public LocalVar(String data) {
            {
                String tmp = data.substring(0,data.indexOf(" reg=")).trim();
                String tmp_array [] = tmp.split("-");
                start_line_index = Integer.parseInt(tmp_array[0].trim().substring(2), 16);
                end_line_index = Integer.parseInt(tmp_array[1].trim().substring(2), 16);
            }
            
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