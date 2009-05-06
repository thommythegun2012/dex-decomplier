//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
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
