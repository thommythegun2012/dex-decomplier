package com.wendal.java.dex.decomplier.com;

import java.io.File;

public class CommandLineConfig {

    private CommandLineConfig() {
        ;
    }

    private boolean verbose = false;

    private String filepath_str = null;

    private String dest_filepath_str = ".";

    public static CommandLineConfig parse(String[] args) {
        CommandLineConfig cc = new CommandLineConfig();

        for (int i = 0; i < args.length; i++) {
            String str = args[i];

            if ("-v".equals(str)) {
                cc.verbose = true;
                continue;
            }
            if ("-dir".equals(str) && (i + 1) < args.length) {
                i++;
                cc.filepath_str = args[i];
                continue;
            }
            if ("-dest".equals(str) && (i + 1) < args.length) {
                i++;
                cc.filepath_str = args[i];
                continue;
            }
        }

        return cc;
    }

    public boolean verify() {
        if (filepath_str != null) {
            File tmp_file = new File(filepath_str);
            if (tmp_file.exists() && tmp_file.isDirectory()) {

                // Search classes.dex
                File dex_file = new File(tmp_file.getPath() + "/classes.dex");
                if (dex_file.exists()) {
                    File tmp_file2 = new File(dest_filepath_str);
                    if (tmp_file2.exists() && tmp_file2.isDirectory()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getFilepath_str() {
        return filepath_str;
    }

    public void setFilepath_str(String filepath_str) {
        this.filepath_str = filepath_str;
    }

    public String getDest_filepath_str() {
        return dest_filepath_str;
    }

    public void setDest_filepath_str(String dest_filepath_str) {
        this.dest_filepath_str = dest_filepath_str;
    }

}
