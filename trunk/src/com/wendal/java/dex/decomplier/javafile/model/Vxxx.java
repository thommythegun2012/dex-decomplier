/**
 * 
 */
package com.wendal.java.dex.decomplier.javafile.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zcchen
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) 
public @interface Vxxx {
    public static enum Type{
        GET , PUT
    }
    
    Type type() default Type.GET;
}
