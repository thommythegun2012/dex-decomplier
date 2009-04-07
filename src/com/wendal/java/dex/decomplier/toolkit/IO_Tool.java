package com.wendal.java.dex.decomplier.toolkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class IO_Tool {

    public static ArrayList<String> getFile(String filepath) throws IOException {
        if (filepath != null) {
            File file = new File(filepath);
            if (file.exists() && file.isFile()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                ArrayList<String> list = new ArrayList<String>();
                while (br.ready()) {
                    String str = br.readLine();
                    if (str != null) {
                        list.add(str);
                    }
                }
                br.close();
                return list;
            }
        }
        /*Ä¬ÈÏ·µ»Ønull*/
        return null;
    }

}
