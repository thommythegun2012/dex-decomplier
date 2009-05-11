package com.wendal.java.dex.decomplier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.wendal.java.dex.decomplier.com.CommandLineConfig;

public class CommandLineConfig_Test extends TestCase{

    private List<String[]> list_good = new ArrayList<String []>();
    private List<String[]> list_bad = new ArrayList<String []>();

    private List<String[]> list_verbose = new ArrayList<String []>();
    private List<String[]> list_non_verbose = new ArrayList<String []>();
    
    private File tmp_file ;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        tmp_file = new File("classes.dex");
        tmp_file.createNewFile();
        
        list_good.add(new String[]{""});
        list_good.add(new String[]{"","","","",""});
        list_bad.add(new String[]{"-dex"});
        list_bad.add(new String[]{"-dest"});
        list_good.add(new String[]{"v"});
        list_good.add(new String[]{"dest"});
        list_good.add(new String[]{"dex"});
        list_good.add(new String[]{"134"});
        list_bad.add(new String[]{"-dex" , "."});
        list_bad.add(new String[]{"-v","-dex"});
        list_bad.add(new String[]{"-v","-dest"});
        list_bad.add(new String[]{"-v","-dest","-dex"});
        
        list_verbose.add(new String[]{"-v"});
        
        list_good.add(new String[]{"-dest" , "."});
        
        list_good.add(new String[]{"-v" ,"C:\\ssf"});
        
        list_verbose.add(new String[]{"-v","-dest",".","-dex","classes.dex"});
        list_verbose.add(new String[]{"-dest",".","-v","-dex","classes.dex"});
        list_verbose.add(new String[]{"-dest",".","-dex","classes.dex","-v"});
        
        list_non_verbose.add(new String[]{"-dest",".","-dex","classes.dex"});
        
        list_good.addAll(list_verbose);
        
        list_good.addAll(list_non_verbose);
    }
    
    @Override
    protected void tearDown() throws Exception {
        if(tmp_file != null){
            tmp_file.delete();
        }
        super.tearDown();
    }

    @Test
    public void testParse() {
        for (String [] args : list_good) {
            CommandLineConfig.parse(args);
        }
        for (String [] args : list_bad) {
            CommandLineConfig.parse(args);
        }
    }

    @Test
    public void testVerify() {
        for (String [] args : list_good) {
            assertTrue(CommandLineConfig.parse(args).verify());
        }
        for (String [] args : list_bad) {
            if(CommandLineConfig.parse(args).verify()){
                System.out.println(args[0]);
            }
            assertFalse(CommandLineConfig.parse(args).verify());
        }
    }

    @Test
    public void testIsVerbose() {
        for (String [] args : list_verbose) {
            assertTrue(CommandLineConfig.parse(args).isVerbose());
        }
        for (String [] args : list_non_verbose) {
            assertFalse(CommandLineConfig.parse(args).isVerbose());
        }
    }

    @Test
    public void testSetVerbose() {
        for (String [] args : list_verbose) {
            CommandLineConfig cc = CommandLineConfig.parse(args);
            cc.setVerbose(false);
            assertFalse(cc.isVerbose());
        }
        for (String [] args : list_non_verbose) {
            CommandLineConfig cc = CommandLineConfig.parse(args);
            cc.setVerbose(true);
            assertTrue(cc.isVerbose());
        }
    }

}
