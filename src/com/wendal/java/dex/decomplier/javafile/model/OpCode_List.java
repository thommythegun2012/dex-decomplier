package com.wendal.java.dex.decomplier.javafile.model;

public class OpCode_List {

    public static final String Op_nop = "00";

    public static final String Op_Goto = "28";
    public static final String Op_Goto16 = "29";
//    public static final String Op_Goto32 = "2a";
    
    public static final String Op_Return_Void = "0e";

    public static final String Op_Invoke_Static = "71";
    public static final String Op_Invoke_Direct = "70";
    public static final String Op_Invoke_Virtual = "6e";
    public static final String Op_Invoke_Interface = "72";
    public static final String Op_Invoke_Super = "6f";
    

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

    public static final String Op_Move_Result = "0a";
    public static final String Op_Move_Result_Wide = "0b";
    public static final String Op_Move_Result_Object = "0c";
    
    

    public static final String Op_Move = "01";
    public static final String Op_Move_from16 = "02";
    public static final String Op_Move_16 = "03";
    public static final String Op_Move_wide = "04";
    public static final String Op_Move_wide_from16 = "05";
    public static final String Op_Move_wide_16 = "06";
    public static final String Op_Move_object = "07";
    public static final String Op_Move_object_from16 = "08";
    public static final String Op_Move_object_16 = "09";
    
    public static final String Op_Instance_of = "20";
    
    public static final String Op_aget = "44";
    public static final String Op_aget_wide = "45";
    public static final String Op_aget_object = "46";
    public static final String Op_aget_boolean = "47";
    public static final String op_aget_byte = "48";
    public static final String Op_aget_char = "49";
    public static final String Op_aget_short = "4a";
    
    public static final String Op_aput = "4b";
    public static final String Op_aput_wide = "4c";
    public static final String Op_aput_object = "4d";
    public static final String Op_aput_boolean = "4e";
    public static final String Op_aput_byte = "4f";
    public static final String Op_aput_char = "50";
    public static final String Op_aput_short = "51";
    
    
    public static final String Op_iget = "52";
    public static final String Op_iget_wide = "53";
    public static final String Op_iget_object = "54";
    public static final String Op_iget_boolean = "55";
    public static final String op_iget_byte = "56";
    public static final String Op_iget_char = "57";
    public static final String Op_iget_short = "58";
    
    public static final String Op_iput = "59";
    public static final String Op_iput_wide = "5a";
    public static final String Op_iput_object = "5b";
    public static final String Op_iput_boolean = "5c";
    public static final String Op_iput_byte = "5d";
    public static final String Op_iput_char = "5e";
    public static final String Op_iput_short = "5f";

    public static final String Op_sget = "60";
    public static final String Op_sget_wide = "61";
    public static final String Op_sget_object = "62";
    public static final String Op_sget_boolean = "63";
    public static final String op_sget_byte = "64";
    public static final String Op_sget_char = "65";
    public static final String Op_sget_short = "66";
    
    public static final String Op_sput = "67";
    public static final String Op_sput_wide = "68";
    public static final String Op_sput_object = "69";
    public static final String Op_sput_boolean = "6a";
    public static final String op_sput_byte = "6b";
    public static final String Op_sput_char = "6c";
    public static final String Op_sput_short = "6d";
    
    public static final String Op_new_instance = "22";
    
    public static final String Op_new_array = "23";
    
    public static final String Op_array_length = "21";

    public static final String Op_cmpl_float = "2d";
    public static final String Op_cmpg_float = "2e";
    public static final String Op_cmpl_double = "2f";
    public static final String Op_cmpg_double = "30";
    public static final String Op_cmp_long = "31";
    
}
