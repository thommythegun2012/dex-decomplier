//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.converter;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_AbstractClass;
import com.wendal.java.dex.decomplier.javafile.model.JavaClass;

public class Dex2Java {
    
    public static JavaClass parseDex(Dex_AbstractClass dex_class){
        JavaClass javaClass = new JavaClass(dex_class);
        javaClass.parse();
        return javaClass;
    }

}
