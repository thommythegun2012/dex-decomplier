//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.converter;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Method;
import com.wendal.java.dex.decomplier.javafile.model.JavaMethod;

public class DexMethod2JavaMethod {
    
    public static JavaMethod parseDex(Dex_Method dex_Method){
        JavaMethod javaMethod = new JavaMethod(dex_Method);
        javaMethod.parse();
        
        return javaMethod;
    }

}
