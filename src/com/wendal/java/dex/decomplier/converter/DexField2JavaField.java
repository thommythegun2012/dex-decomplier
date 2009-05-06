//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
package com.wendal.java.dex.decomplier.converter;

import com.wendal.java.dex.decomplier.dexfile.model.Dex_Field;
import com.wendal.java.dex.decomplier.javafile.model.JavaField;

public class DexField2JavaField {

    public static JavaField parseDex(Dex_Field dex_field){
        JavaField javaField = new JavaField(dex_field);
        javaField.parse();
        
        return javaField;
    }
}
