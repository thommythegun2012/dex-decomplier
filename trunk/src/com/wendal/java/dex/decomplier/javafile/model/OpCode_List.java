package com.wendal.java.dex.decomplier.javafile.model;

public class OpCode_List {

    public static final String Op_Goto = "28";
    public static final String Op_Goto16 = "29";
//    public static final String Op_Goto32 = "2a";
    
    public static final String Op_Return_Void = "0e";

    public static final String Op_Invoke_Static = "71";
    public static final String Op_Invoke_Direct = "70";
    public static final String Op_Invoke_Virtual = "6e";
    public static final String Op_Invoke_Interface = "72";
    

    public static final String Op_Throw = "27";

    public static final String Op_Return_Object = "11";
    public static final String Op_Return_V = "0f";
    public static final String Op_Return_Wide = "10";
    

    public static final String Op_CheckCase = "1f";


    public String Op_If_eq =  "32";
    public String Op_If_ne =  "33";
    public String Op_If_lt =  "34";
    public String Op_If_ge =  "35";
    public String Op_If_gt =  "36";
    public String Op_If_le =  "37";
    
    public String Op_If_eqz =  "38";
    public String Op_If_nez =  "39";
    public String Op_If_ltz =  "3a";
    public String Op_If_gez =  "3b";
    public String Op_If_gtz =  "3c";
    public String Op_If_lez =  "3d";
    
    

    public static final String Op_Conset4 = "12";
    public static final String Op_Conset16 = "13";
    public static final String Op_Conset_V = "14";
    public static final String Op_Conset_high16 = "15";
    public static final String Op_Conset_wide16 = "16";
    public static final String Op_Conset_wide32 = "17";
    public static final String Op_Conset_wide = "18";
    public static final String Op_Conset_wide_high16 = "19";
}
