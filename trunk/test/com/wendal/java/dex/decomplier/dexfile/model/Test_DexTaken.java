package com.wendal.java.dex.decomplier.dexfile.model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.wendal.java.dex.decomplier.com.CommandLineConfig;
import com.wendal.java.dex.decomplier.com.DexD;
import com.wendal.java.dex.decomplier.toolkit.IO_Tool;

public class Test_DexTaken {

    @Test
    public void testGetDexClassList() throws IOException {
        String fileList [] = new String[]{
                "dex/com/wendal/dex/simple/multi/xrace_v2.dump.txt"
                ,
                "dex/com/wendal/dex/simple/multi/rec.dump.txt"
                , "dex/com/wendal/dex/simple/empty/EmptyClass.dump.txt"
                ,"dex/com/wendal/dex/simple/empty/EmptyInterface.dump.txt"
                ,"dex/com/wendal/dex/simple/empty/EmptyEnum.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_Abstract.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_fields.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_final.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_SomeInterfaces.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_Static_fields.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/SimpleClass_with_SuperClass.dump.txt"
                ,"dex/com/wendal/dex/simple/easy/methods/Void_String_Method.dump.txt"
                ,
                "dex/com/wendal/dex/simple/easy/methods/Static_Methods.dump.txt"
        };
        for (String string : fileList) {
            List<String> list = IO_Tool.getFile(string);
            assertTrue(list.size() > 0);
            DexD dexD = new DexD(CommandLineConfig.parse(new String[]{"-dir" , "." , "-dest" ,"X:"}));
            dexD.setDex_dump_str(list);
            dexD.convert2DexModel();
            dexD.convert2JavaModel();
            dexD.VM_parse();
            dexD.reshaping();
            dexD.outputSource();
        }
        
    }

}
