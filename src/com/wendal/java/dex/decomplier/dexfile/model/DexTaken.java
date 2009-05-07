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
package com.wendal.java.dex.decomplier.dexfile.model;

import java.util.ArrayList;

public final class DexTaken {
    
    public static final ArrayList<Dex_AbstractClass> getDexClassList(ArrayList<String> str_list){
        if(str_list != null){
            ArrayList<Dex_AbstractClass> dex_class_list = new ArrayList<Dex_AbstractClass>();
            ArrayList<String> data = null;
            for (String string : str_list) {
                if(string != null && string.startsWith(StringTaken_Dex.Class_Taken)){
                    Dex_AbstractClass dex_ac = new Dex_AbstractClass();
                    data = new ArrayList<String>();
                    dex_class_list.add(dex_ac);
                    dex_ac.setDex_data(data);
                    
                    String str_array [] = string.split("#");
                    int index = Integer.parseInt(str_array[1].replace('-', ' ').trim());
                    dex_ac.setClass_index(index);
//                    System.out.println(index);
                }
                if(string != null && data != null){
                    data.add(string);
                }
//                if(data != null){
//                    System.out.println(data.size());
//                }
            }
//            System.out.println(dex_class_list.size());
            return dex_class_list;
        }
        return null;
    }

}
