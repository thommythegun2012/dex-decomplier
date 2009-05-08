//:~
/**
 * $Id$
 * 
 * $LastChangedBy$
 * @version $Revision$
 * 
 */
/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.wendal.java.dex.decomplier.toolkit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public final class IO_Tool {

    public static List<String> getFile(String filepath) throws IOException {
        if (filepath != null) {
            File file = new File(filepath);
            if (file.exists() && file.isFile()) {
                return FileUtils.readLines(file);
            }
        }
        /*Ä¬ÈÏ·µ»Ønull*/
        return null;
    }

    public static void dexdump(String filepath) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("dexdump", new String[]{filepath , ">classes.txt"} );
    }
    
    public static void write2File(String rootDir , String package_name , String filename , String data) throws IOException{
        String tmp []  = package_name.split("[.]");
        String tmp_str = rootDir;
        for (int i = 0; i < tmp.length; i++) {
            tmp_str += "\\";
            tmp_str += tmp[i];
        }
        File dir_file = new File(tmp_str + "\\" );
        dir_file.mkdirs();
        
        File src_file = new File(dir_file.getPath()+ "\\"+filename);
        src_file.createNewFile();
        FileUtils.writeStringToFile(src_file, data);
    }
}
