//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.wendal.java.dex.decomplier.converter.Dex2Java;
import com.wendal.java.dex.decomplier.dexfile.model.DexTaken;
import com.wendal.java.dex.decomplier.dexfile.model.Dex_AbstractClass;
import com.wendal.java.dex.decomplier.toolkit.IO_Tool;

public class Main {


    public static void main(String[] args) throws IOException {
        File file = new File("dex/com/wendal/dex/simple/easy/returnvoid/SimpleReturnVoid.dump.txt");
        ArrayList<String> list = IO_Tool.getFile(file.getPath());
//        System.out.println(list.size());
        ArrayList<Dex_AbstractClass> dex_class_list = DexTaken.getDexClassList(list);
        System.out.println("Totla Class : "+dex_class_list.size());
        System.out.println();
        for (Dex_AbstractClass dex_AbstractClass : dex_class_list) {
            dex_AbstractClass.parse();
            Dex2Java.parseDex(dex_AbstractClass);
        }
    }

}
