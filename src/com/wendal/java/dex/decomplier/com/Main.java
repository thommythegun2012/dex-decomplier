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
