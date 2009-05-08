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
package com.wendal.java.dex.decomplier.com;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            CommandLineConfig cc = CommandLineConfig.parse(args);
            if(cc.verify()){
                DexD dexD = new DexD(cc);
                dexD.init();
                dexD.convert2DexModel();
                dexD.convert2JavaModel();
                dexD.VM_parse();
                dexD.reshaping();
                dexD.outputSource();
            }else{
                CommandLineConfig.printHelp();
            }
        }else{
            CommandLineConfig.printHelp();
        }
    }
}
