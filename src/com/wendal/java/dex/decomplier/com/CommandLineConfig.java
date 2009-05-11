package com.wendal.java.dex.decomplier.com;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineConfig {
    
    private static Options options;

    private CommandLineConfig() {
        
    }
    
    static {
        Option verbose_option = new Option("v",false,"show verbose message");
        Option dex_Option = new Option("dex",true,"dex filename");
        Option dest_Option = new Option("dest",true,"output dir");
        Option help_Option = new Option("help",true,"print usage");
        
        verbose_option.setRequired(false);
        dex_Option.setRequired(false);
        dest_Option.setRequired(false);
        help_Option.setRequired(false);

        verbose_option.setArgs(0);
        dest_Option.setArgs(1);
        dex_Option.setArgs(1);
        help_Option.setArgs(0);
        
        dex_Option.setArgName("filename.dex");
        dest_Option.setArgName("output_dir");
        
        options = new Options();
        
        options.addOption(verbose_option);
        options.addOption(dest_Option);
        options.addOption(dex_Option);
        options.addOption(help_Option);
    }
    

    private boolean verbose = false;

    private String dex_filepath_str = "classes.dex";

    private String dest_filepath_str = "src";
    
    private boolean needHelp = false;

    public static CommandLineConfig parse(String[] args) {
        CommandLineConfig cc = new CommandLineConfig();
        try {
            CommandLine cmd = new GnuParser().parse(CommandLineConfig.options, args);
            if(cmd.hasOption("v")){
                cc.verbose = true;
            }
            if(cmd.hasOption("help")){
                cc.needHelp = true;
            }
            if(cmd.getOptionValue("dex") != null){
                cc.dex_filepath_str = cmd.getOptionValue("dex");
            }
            if(cmd.getOptionValue("dest") != null){
                cc.dest_filepath_str = cmd.getOptionValue("dest");
            }
        } catch (ParseException exp) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            cc.dex_filepath_str = null;
        }
        return cc;
    }

    public boolean verify() {
        if (dex_filepath_str != null) {
            File tmp_file = new File(dex_filepath_str);
            if (tmp_file.exists() && tmp_file.isFile()) {
                    File tmp_file2 = new File(dest_filepath_str);
                    if (tmp_file2.exists() && tmp_file2.isDirectory()) {
                        return true;
                    }
            }
        }
        return false;
    }
    
    public static void printHelp(){
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp("dexD", options);
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getDex_filepath_str() {
        return dex_filepath_str;
    }

    public void setDex_filepath_str(String dex_filepath_str) {
        this.dex_filepath_str = dex_filepath_str;
    }

    public String getDest_filepath_str() {
        return dest_filepath_str;
    }

    public void setDest_filepath_str(String dest_filepath_str) {
        this.dest_filepath_str = dest_filepath_str;
    }

    public boolean isNeedHelp() {
        return needHelp;
    }

    public void setNeedHelp(boolean needHelp) {
        this.needHelp = needHelp;
    }

}
