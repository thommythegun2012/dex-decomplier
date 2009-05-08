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
        
        list_bad.add(new String[]{""});
        list_bad.add(new String[]{"","","","",""});
        list_bad.add(new String[]{"-dir"});
        list_bad.add(new String[]{"-dest"});
        list_bad.add(new String[]{"v"});
        list_bad.add(new String[]{"dir"});
        list_bad.add(new String[]{"dest"});
        list_bad.add(new String[]{"134"});
        list_bad.add(new String[]{"-v" ,"C:\\ssf"});
        list_bad.add(new String[]{"-dest" , "."});
        list_bad.add(new String[]{"-dir" , "."});
        list_bad.add(new String[]{"-v","-dest"});
        list_bad.add(new String[]{"-v","-dir"});
        list_bad.add(new String[]{"-v","dir","dest"});
        list_bad.add(new String[]{"-v"});
        
        list_verbose.add(new String[]{"-v","-dir",".","-dest","."});
        list_verbose.add(new String[]{"-dir",".","-v","-dest","."});
        list_verbose.add(new String[]{"-dir",".","-dest",".","-v"});
        
        list_non_verbose.add(new String[]{"-dir",".","-dest","."});
        
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
