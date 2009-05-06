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


    public static final String Op_If_eq =  "32";
    public static final String Op_If_ne =  "33";
    public static final String Op_If_lt =  "34";
    public static final String Op_If_ge =  "35";
    public static final String Op_If_gt =  "36";
    public static final String Op_If_le =  "37";
    
    public static final String Op_If_eqz =  "38";
    public static final String Op_If_nez =  "39";
    public static final String Op_If_ltz =  "3a";
    public static final String Op_If_gez =  "3b";
    public static final String Op_If_gtz =  "3c";
    public static final String Op_If_lez =  "3d";
    
    

    public static final String Op_Conset4 = "12";
    public static final String Op_Conset16 = "13";
    public static final String Op_Conset_V = "14";
    public static final String Op_Conset_high16 = "15";
    public static final String Op_Conset_wide16 = "16";
    public static final String Op_Conset_wide32 = "17";
    public static final String Op_Conset_wide = "18";
    public static final String Op_Conset_wide_high16 = "19";

    public static final String Op_Conset_class = "1c";
    

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
    
    public static final String Op_Move_Exception = "0d";
    
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
    

    public static final String Op_neg_int = "7b";
    public static final String Op_not_int = "7c";
    public static final String Op_neg_long = "7d";
    public static final String Op_not_long = "7e";
    public static final String Op_neg_float = "7f";
    public static final String Op_neg_double = "80";
    public static final String Op_int_to_long = "81";
    public static final String Op_int_to_float = "82";
    public static final String Op_int_to_double = "83";
    public static final String Op_long_to_int = "84";
    public static final String Op_long_to_float = "85";
    public static final String Op_long_to_double = "86";
    public static final String Op_float_to_int = "87";
    public static final String Op_float_to_long = "88";
    public static final String Op_float_to_double = "89";
    public static final String Op_double_to_int = "8a";
    public static final String Op_double_to_long = "8b";
    public static final String Op_double_to_float = "8c";
    public static final String Op_int_to_byte = "8d";
    public static final String Op_int_to_char = "8e";
    public static final String Op_int_to_short = "8f";
    

    public static final String Op_add_int = "90";
    public static final String Op_sub_int = "91";
    public static final String Op_mul_int = "92";
    public static final String Op_div_int = "93";
    public static final String Op_rem_int = "94";
    public static final String Op_and_int = "95";
    public static final String Op_or_int = "96";
    public static final String Op_xor_int = "97";
    public static final String Op_shl_int = "98";
    public static final String Op_shr_int = "99";
    public static final String Op_ushr_int = "9a";
    public static final String Op_add_long = "9b";
    public static final String Op_sub_long = "9c";
    public static final String Op_mul_long = "9d";
    public static final String Op_div_long = "9e";
    public static final String Op_rem_long = "9f";
    public static final String Op_and_long = "a0";
    public static final String Op_or_long = "a1";
    public static final String Op_xor_long = "a2";
    public static final String Op_shl_long = "a3";
    public static final String Op_shr_long = "a4";
    public static final String Op_ushr_long = "a5";
    public static final String Op_add_float = "a6";
    public static final String Op_sub_float = "a7";
    public static final String Op_mul_float = "a8";
    public static final String Op_div_float = "a9";
    public static final String Op_rem_float = "aa";
    public static final String Op_add_double = "ab";
    public static final String Op_sub_double = "ac";
    public static final String Op_mul_double = "ad";
    public static final String Op_div_double = "ae";
    public static final String Op_rem_double = "af";
    
    
    public static final String Op_add_int_2addr = "b0";
    public static final String Op_sub_int_2addr = "b1";
    public static final String Op_mul_int_2addr = "b2";
    public static final String Op_div_int_2addr = "b3";
    public static final String Op_rem_int_2addr = "b4";
    public static final String Op_and_int_2addr = "b5";
    public static final String Op_or_int_2addr = "b6";
    public static final String Op_xor_int_2addr = "b7";
    public static final String Op_shl_int_2addr = "b8";
    public static final String Op_shr_int_2addr = "b9";
    public static final String Op_ushr_int_2addr = "ba";
    public static final String Op_add_long_2addr = "bb";
    public static final String Op_sub_long_2addr = "bc";
    public static final String Op_mul_long_2addr = "bd";
    public static final String Op_div_long_2addr = "be";
    public static final String Op_rem_long_2addr = "bf";
    public static final String Op_and_long_2addr = "c0";
    public static final String Op_or_long_2addr = "c1";
    public static final String Op_xor_long_2addr = "c2";
    public static final String Op_shl_long_2addr = "c3";
    public static final String Op_shr_long_2addr = "c4";
    public static final String Op_ushr_long_2addr = "c5";
    public static final String Op_add_float_2addr = "c6";
    public static final String Op_sub_float_2addr = "c7";
    public static final String Op_mul_float_2addr = "c8";
    public static final String Op_div_float_2addr = "c9";
    public static final String Op_rem_float_2addr = "ca";
    public static final String Op_add_double_2addr = "cb";
    public static final String Op_sub_double_2addr = "cc";
    public static final String Op_mul_double_2addr = "cd";
    public static final String Op_div_double_2addr = "ce";
    public static final String Op_rem_double_2addr = "cf";
    

    public static final String Op_add_int_lit16 = "d0";
    public static final String Op_rsub_int = "d1";
    public static final String Op_mul_int_lit16 = "d2";
    public static final String Op_div_int_lit16 = "d3";
    public static final String Op_rem_int_lit16 = "d4";
    public static final String Op_and_int_lit16 = "d5";
    public static final String Op_or_int_lit16 = "d6";
    public static final String Op_xor_int_lit16  = "d7";

    public static final String Op_add_int_lit8 = "d8";
    public static final String Op_rsub_int_lit8 = "d9";
    public static final String Op_mul_int_lit8 = "da";
    public static final String Op_div_int_lit8 = "db";
    public static final String Op_rem_int_lit8 = "dc";
    public static final String Op_and_int_lit8 = "dd";
    public static final String Op_or_int_lit8 = "de";
    public static final String Op_xor_int_lit8 = "df";
    public static final String Op_shl_int_lit8 = "e0";
    public static final String Op_shr_int_lit8 = "e1";
    public static final String Op_ushr_int_lit8 = "e2";
}
