mkdir _dexd_tmp
unzip_apk.bat %1 _dexd_tmp
java -jar DexD.jar -dir _dexd_tmp %2 %3 %4 %5 %6 %7 %8