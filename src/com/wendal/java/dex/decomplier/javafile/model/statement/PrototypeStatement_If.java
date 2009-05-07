//:~
/**
 * $Id$
 * 
 * @author $Author$
 * @version $revision: $
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
package com.wendal.java.dex.decomplier.javafile.model.statement;

import com.wendal.java.dex.decomplier.javafile.model.PrototypeStatement;

/**
 * 一整类判断,与一个值进行对比
 * @author zcchen
 *
 */
public class PrototypeStatement_If extends PrototypeStatement {
    
    public String If_eq = "if-eq";
    public String If_ne =  "if-ne";
    public String If_lt =  "if-lt";
    public String If_ge =  "if-ge";
    public String If_gt =  "if-gt";
    public String If_le =  "if-le";
    
    public PrototypeStatement_If() {
        
    }
    
    public String jump_to ;

    public String value_a = "";
    public String value_b = "";
    public boolean isIf_Zero = false;
    
    public String op;

    @Override
    public void parse() {
        String IF_info = info.substring(0,info.trim().indexOf(" ")+1).trim();
        if(IF_info.endsWith("z")){
            isIf_Zero = true;
        }
        
        if (IF_info.startsWith(If_eq)) {
            op = "==";
        }
        if (IF_info.startsWith(If_ne)) {
            op = "!=";
        }
        if (IF_info.startsWith(If_lt)) {
            op = "<";
        }
        if (IF_info.startsWith(If_ge)) {
            op = ">=";
        }
        if (IF_info.startsWith(If_gt)) {
            op = ">";
        }if (IF_info.startsWith(If_le)) {
            op = "<=";
        }
        
        //取vx
        value_a = info.substring(info.indexOf(" ")+1 ,info.indexOf(",")).trim();
        if(isIf_Zero){
            value_b = "0";
            jump_to = info.substring(info.indexOf(",")+1 ).trim();
        }else{
            value_b = info.substring(info.indexOf(",")+1 , info.lastIndexOf(",")).trim();
            jump_to = info.substring(info.lastIndexOf(",")+1 ).trim();
        }
        
        super.parse();
    }
    
    @Override
    public String toString() {
        
        return "if("+value_a+ " "+op + " "+ value_b +") jump_to " +jump_to;
    }
}
