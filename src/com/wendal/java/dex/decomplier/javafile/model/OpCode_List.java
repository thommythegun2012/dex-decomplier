package com.wendal.java.dex.decomplier.javafile.model;

import java.util.HashMap;

import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Check_Cast;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Const;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Const_class;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Goto;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_If;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Instance_Of;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Invoke_Direct;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Invoke_Static;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move_Exception;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Move_Result;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnVoid;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_ReturnX;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_Throw;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_aget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_aput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_array_length;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop_2addr;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_binop_lit16;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_cmp;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_iget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_iput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_new_array;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_new_instance;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_nop;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sget;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_sput;
import com.wendal.java.dex.decomplier.javafile.model.statement.PrototypeStatement_unop;

public class OpCode_List {

    private static final String Op_nop = "00";

    public static final String Op_Goto = "28";
    public static final String Op_Goto16 = "29";
//    private static final String Op_Goto32 = "2a";
    
    public static final String Op_Return_Void = "0e";

    private static final String Op_Invoke_Static = "71";
    private static final String Op_Invoke_Direct = "70";
    private static final String Op_Invoke_Virtual = "6e";
    private static final String Op_Invoke_Interface = "72";
    private static final String Op_Invoke_Super = "6f";
    

    private static final String Op_Throw = "27";

    private static final String Op_Return_Object = "11";
    private static final String Op_Return_V = "0f";
    private static final String Op_Return_Wide = "10";
    

    private static final String Op_CheckCase = "1f";


    private static final String Op_If_eq =  "32";
    private static final String Op_If_ne =  "33";
    private static final String Op_If_lt =  "34";
    private static final String Op_If_ge =  "35";
    private static final String Op_If_gt =  "36";
    private static final String Op_If_le =  "37";
    
    private static final String Op_If_eqz =  "38";
    private static final String Op_If_nez =  "39";
    private static final String Op_If_ltz =  "3a";
    private static final String Op_If_gez =  "3b";
    private static final String Op_If_gtz =  "3c";
    private static final String Op_If_lez =  "3d";
    
    

    private static final String Op_Conset4 = "12";
    private static final String Op_Conset16 = "13";
    private static final String Op_Conset_V = "14";
    private static final String Op_Conset_high16 = "15";
    private static final String Op_Conset_wide16 = "16";
    private static final String Op_Conset_wide32 = "17";
    private static final String Op_Conset_wide = "18";
    private static final String Op_Conset_wide_high16 = "19";

    private static final String Op_Conset_class = "1c";
    

    private static final String Op_Move_Result = "0a";
    private static final String Op_Move_Result_Wide = "0b";
    private static final String Op_Move_Result_Object = "0c";
    
    

    private static final String Op_Move = "01";
    private static final String Op_Move_from16 = "02";
    private static final String Op_Move_16 = "03";
    private static final String Op_Move_wide = "04";
    private static final String Op_Move_wide_from16 = "05";
    private static final String Op_Move_wide_16 = "06";
    private static final String Op_Move_object = "07";
    private static final String Op_Move_object_from16 = "08";
    private static final String Op_Move_object_16 = "09";
    
    private static final String Op_Move_Exception = "0d";
    
    private static final String Op_Instance_of = "20";
    
    private static final String Op_aget = "44";
    private static final String Op_aget_wide = "45";
    private static final String Op_aget_object = "46";
    private static final String Op_aget_boolean = "47";
    private static final String op_aget_byte = "48";
    private static final String Op_aget_char = "49";
    private static final String Op_aget_short = "4a";
    
    private static final String Op_aput = "4b";
    private static final String Op_aput_wide = "4c";
    private static final String Op_aput_object = "4d";
    private static final String Op_aput_boolean = "4e";
    private static final String Op_aput_byte = "4f";
    private static final String Op_aput_char = "50";
    private static final String Op_aput_short = "51";
    
    
    private static final String Op_iget = "52";
    private static final String Op_iget_wide = "53";
    private static final String Op_iget_object = "54";
    private static final String Op_iget_boolean = "55";
    private static final String op_iget_byte = "56";
    private static final String Op_iget_char = "57";
    private static final String Op_iget_short = "58";
    
    private static final String Op_iput = "59";
    private static final String Op_iput_wide = "5a";
    private static final String Op_iput_object = "5b";
    private static final String Op_iput_boolean = "5c";
    private static final String Op_iput_byte = "5d";
    private static final String Op_iput_char = "5e";
    private static final String Op_iput_short = "5f";

    private static final String Op_sget = "60";
    private static final String Op_sget_wide = "61";
    private static final String Op_sget_object = "62";
    private static final String Op_sget_boolean = "63";
    private static final String op_sget_byte = "64";
    private static final String Op_sget_char = "65";
    private static final String Op_sget_short = "66";
    
    private static final String Op_sput = "67";
    private static final String Op_sput_wide = "68";
    private static final String Op_sput_object = "69";
    private static final String Op_sput_boolean = "6a";
    private static final String op_sput_byte = "6b";
    private static final String Op_sput_char = "6c";
    private static final String Op_sput_short = "6d";
    
    private static final String Op_new_instance = "22";
    
    private static final String Op_new_array = "23";
    
    private static final String Op_array_length = "21";

    private static final String Op_cmpl_float = "2d";
    private static final String Op_cmpg_float = "2e";
    private static final String Op_cmpl_double = "2f";
    private static final String Op_cmpg_double = "30";
    private static final String Op_cmp_long = "31";
    

    private static final String Op_neg_int = "7b";
    private static final String Op_not_int = "7c";
    private static final String Op_neg_long = "7d";
    private static final String Op_not_long = "7e";
    private static final String Op_neg_float = "7f";
    private static final String Op_neg_double = "80";
    private static final String Op_int_to_long = "81";
    private static final String Op_int_to_float = "82";
    private static final String Op_int_to_double = "83";
    private static final String Op_long_to_int = "84";
    private static final String Op_long_to_float = "85";
    private static final String Op_long_to_double = "86";
    private static final String Op_float_to_int = "87";
    private static final String Op_float_to_long = "88";
    private static final String Op_float_to_double = "89";
    private static final String Op_double_to_int = "8a";
    private static final String Op_double_to_long = "8b";
    private static final String Op_double_to_float = "8c";
    private static final String Op_int_to_byte = "8d";
    private static final String Op_int_to_char = "8e";
    private static final String Op_int_to_short = "8f";
    

    private static final String Op_add_int = "90";
    private static final String Op_sub_int = "91";
    private static final String Op_mul_int = "92";
    private static final String Op_div_int = "93";
    private static final String Op_rem_int = "94";
    private static final String Op_and_int = "95";
    private static final String Op_or_int = "96";
    private static final String Op_xor_int = "97";
    private static final String Op_shl_int = "98";
    private static final String Op_shr_int = "99";
    private static final String Op_ushr_int = "9a";
    private static final String Op_add_long = "9b";
    private static final String Op_sub_long = "9c";
    private static final String Op_mul_long = "9d";
    private static final String Op_div_long = "9e";
    private static final String Op_rem_long = "9f";
    private static final String Op_and_long = "a0";
    private static final String Op_or_long = "a1";
    private static final String Op_xor_long = "a2";
    private static final String Op_shl_long = "a3";
    private static final String Op_shr_long = "a4";
    private static final String Op_ushr_long = "a5";
    private static final String Op_add_float = "a6";
    private static final String Op_sub_float = "a7";
    private static final String Op_mul_float = "a8";
    private static final String Op_div_float = "a9";
    private static final String Op_rem_float = "aa";
    private static final String Op_add_double = "ab";
    private static final String Op_sub_double = "ac";
    private static final String Op_mul_double = "ad";
    private static final String Op_div_double = "ae";
    private static final String Op_rem_double = "af";
    
    
    private static final String Op_add_int_2addr = "b0";
    private static final String Op_sub_int_2addr = "b1";
    private static final String Op_mul_int_2addr = "b2";
    private static final String Op_div_int_2addr = "b3";
    private static final String Op_rem_int_2addr = "b4";
    private static final String Op_and_int_2addr = "b5";
    private static final String Op_or_int_2addr = "b6";
    private static final String Op_xor_int_2addr = "b7";
    private static final String Op_shl_int_2addr = "b8";
    private static final String Op_shr_int_2addr = "b9";
    private static final String Op_ushr_int_2addr = "ba";
    private static final String Op_add_long_2addr = "bb";
    private static final String Op_sub_long_2addr = "bc";
    private static final String Op_mul_long_2addr = "bd";
    private static final String Op_div_long_2addr = "be";
    private static final String Op_rem_long_2addr = "bf";
    private static final String Op_and_long_2addr = "c0";
    private static final String Op_or_long_2addr = "c1";
    private static final String Op_xor_long_2addr = "c2";
    private static final String Op_shl_long_2addr = "c3";
    private static final String Op_shr_long_2addr = "c4";
    private static final String Op_ushr_long_2addr = "c5";
    private static final String Op_add_float_2addr = "c6";
    private static final String Op_sub_float_2addr = "c7";
    private static final String Op_mul_float_2addr = "c8";
    private static final String Op_div_float_2addr = "c9";
    private static final String Op_rem_float_2addr = "ca";
    private static final String Op_add_double_2addr = "cb";
    private static final String Op_sub_double_2addr = "cc";
    private static final String Op_mul_double_2addr = "cd";
    private static final String Op_div_double_2addr = "ce";
    private static final String Op_rem_double_2addr = "cf";
    

    private static final String Op_add_int_lit16 = "d0";
    private static final String Op_rsub_int = "d1";
    private static final String Op_mul_int_lit16 = "d2";
    private static final String Op_div_int_lit16 = "d3";
    private static final String Op_rem_int_lit16 = "d4";
    private static final String Op_and_int_lit16 = "d5";
    private static final String Op_or_int_lit16 = "d6";
    private static final String Op_xor_int_lit16  = "d7";

    private static final String Op_add_int_lit8 = "d8";
    private static final String Op_rsub_int_lit8 = "d9";
    private static final String Op_mul_int_lit8 = "da";
    private static final String Op_div_int_lit8 = "db";
    private static final String Op_rem_int_lit8 = "dc";
    private static final String Op_and_int_lit8 = "dd";
    private static final String Op_or_int_lit8 = "de";
    private static final String Op_xor_int_lit8 = "df";
    private static final String Op_shl_int_lit8 = "e0";
    private static final String Op_shr_int_lit8 = "e1";
    private static final String Op_ushr_int_lit8 = "e2";
    
    
    private static final HashMap<String, Class<? extends PrototypeStatement>> map = new HashMap<String, Class<? extends PrototypeStatement>>();
    
    
    
    static {
        map.put(Op_nop, PrototypeStatement_nop.class);

        map.put(Op_Goto, PrototypeStatement_Goto.class);
        map.put(Op_Goto16, PrototypeStatement_Goto.class);
        
        map.put(Op_Return_Void, PrototypeStatement_ReturnVoid.class);
        map.put(Op_Return_Object, PrototypeStatement_ReturnX.class);
        map.put(Op_Return_V, PrototypeStatement_ReturnX.class);
        map.put(Op_Return_Wide, PrototypeStatement_ReturnX.class);

        map.put(Op_Invoke_Static, PrototypeStatement_Invoke_Static.class);
        map.put(Op_Invoke_Direct, PrototypeStatement_Invoke_Direct.class);
        map.put(Op_Invoke_Interface, PrototypeStatement_Invoke_Direct.class);
        map.put(Op_Invoke_Super, PrototypeStatement_Invoke_Direct.class);
        map.put(Op_Invoke_Virtual, PrototypeStatement_Invoke_Direct.class);
        
        map.put(Op_Throw, PrototypeStatement_Throw.class);
        
        map.put(Op_CheckCase, PrototypeStatement_Check_Cast.class);

        map.put(Op_If_eq, PrototypeStatement_If.class);
        map.put(Op_If_ne, PrototypeStatement_If.class);
        map.put(Op_If_lt, PrototypeStatement_If.class);
        map.put(Op_If_ge, PrototypeStatement_If.class);
        map.put(Op_If_gt, PrototypeStatement_If.class);
        map.put(Op_If_le, PrototypeStatement_If.class);
        
        map.put(Op_If_eqz, PrototypeStatement_If.class);
        map.put(Op_If_nez, PrototypeStatement_If.class);
        map.put(Op_If_ltz, PrototypeStatement_If.class);
        map.put(Op_If_gez, PrototypeStatement_If.class);
        map.put(Op_If_gtz, PrototypeStatement_If.class);
        map.put(Op_If_lez, PrototypeStatement_If.class);

        map.put(Op_Conset4, PrototypeStatement_Const.class);
        map.put(Op_Conset16, PrototypeStatement_Const.class);
        map.put(Op_Conset_V, PrototypeStatement_Const.class);
        map.put(Op_Conset_high16, PrototypeStatement_Const.class);
        map.put(Op_Conset_wide16, PrototypeStatement_Const.class);
        map.put(Op_Conset_wide32, PrototypeStatement_Const.class);
        map.put(Op_Conset_wide, PrototypeStatement_Const.class);
        map.put(Op_Conset_wide_high16, PrototypeStatement_Const.class);
        
        map.put(Op_Conset_class, PrototypeStatement_Const_class.class);
        

        map.put(Op_Move_Result, PrototypeStatement_Move_Result.class);
        map.put(Op_Move_Result_Object, PrototypeStatement_Move_Result.class);
        map.put(Op_Move_Result_Wide, PrototypeStatement_Move_Result.class);

        map.put(Op_Move, PrototypeStatement_Move.class);
        map.put(Op_Move_from16, PrototypeStatement_Move.class);
        map.put(Op_Move_16, PrototypeStatement_Move.class);
        map.put(Op_Move_wide, PrototypeStatement_Move.class);
        map.put(Op_Move_wide_from16, PrototypeStatement_Move.class);
        map.put(Op_Move_wide_16, PrototypeStatement_Move.class);
        map.put(Op_Move_object, PrototypeStatement_Move.class);
        map.put(Op_Move_object_from16, PrototypeStatement_Move.class);
        map.put(Op_Move_object_16, PrototypeStatement_Move.class);
        
        map.put(Op_Move_Exception, PrototypeStatement_Move_Exception.class);
        
        map.put(Op_Instance_of, PrototypeStatement_Instance_Of.class);

        map.put(Op_aget, PrototypeStatement_aget.class);
        map.put(Op_aget_boolean, PrototypeStatement_aget.class);
        map.put(Op_aget_char, PrototypeStatement_aget.class);
        map.put(Op_aget_object, PrototypeStatement_aget.class);
        map.put(Op_aget_short, PrototypeStatement_aget.class);
        map.put(Op_aget_wide, PrototypeStatement_aget.class);
        map.put(op_aget_byte, PrototypeStatement_aget.class);
        
        map.put(Op_aput, PrototypeStatement_aput.class);
        map.put(Op_aput_boolean, PrototypeStatement_aput.class);
        map.put(Op_aput_char, PrototypeStatement_aput.class);
        map.put(Op_aput_object, PrototypeStatement_aput.class);
        map.put(Op_aput_short, PrototypeStatement_aput.class);
        map.put(Op_aput_wide, PrototypeStatement_aput.class);
        map.put(Op_aput_byte, PrototypeStatement_aput.class);
        
        map.put(Op_iget, PrototypeStatement_iget.class);
        map.put(Op_iget_boolean, PrototypeStatement_iget.class);
        map.put(Op_iget_char, PrototypeStatement_iget.class);
        map.put(Op_iget_object, PrototypeStatement_iget.class);
        map.put(Op_iget_short, PrototypeStatement_iget.class);
        map.put(Op_iget_wide, PrototypeStatement_iget.class);
        map.put(op_iget_byte, PrototypeStatement_iget.class);
        
        map.put(Op_iput, PrototypeStatement_iput.class);
        map.put(Op_iput_boolean, PrototypeStatement_iput.class);
        map.put(Op_iput_char, PrototypeStatement_iput.class);
        map.put(Op_iput_object, PrototypeStatement_iput.class);
        map.put(Op_iput_short, PrototypeStatement_iput.class);
        map.put(Op_iput_wide, PrototypeStatement_iput.class);
        map.put(Op_iput_byte, PrototypeStatement_iput.class);
        
        map.put(Op_sget, PrototypeStatement_sget.class);
        map.put(Op_sget_boolean, PrototypeStatement_sget.class);
        map.put(Op_sget_char, PrototypeStatement_sget.class);
        map.put(Op_sget_object, PrototypeStatement_sget.class);
        map.put(Op_sget_short, PrototypeStatement_sget.class);
        map.put(Op_sget_wide, PrototypeStatement_sget.class);
        map.put(op_sget_byte, PrototypeStatement_sget.class);
        
        map.put(Op_sput, PrototypeStatement_sput.class);
        map.put(Op_sput_boolean, PrototypeStatement_sput.class);
        map.put(Op_sput_char, PrototypeStatement_sput.class);
        map.put(Op_sput_object, PrototypeStatement_sput.class);
        map.put(Op_sput_short, PrototypeStatement_sput.class);
        map.put(Op_sput_wide, PrototypeStatement_sput.class);
        map.put(op_sput_byte, PrototypeStatement_sput.class);
        
        map.put(Op_new_instance, PrototypeStatement_new_instance.class);

        map.put(Op_new_array, PrototypeStatement_new_array.class);
        
        map.put(Op_array_length, PrototypeStatement_array_length.class);

        map.put(Op_cmp_long, PrototypeStatement_cmp.class);
        map.put(Op_cmpg_double, PrototypeStatement_cmp.class);
        map.put(Op_cmpl_double, PrototypeStatement_cmp.class);
        map.put(Op_cmpg_float, PrototypeStatement_cmp.class);
        map.put(Op_cmpl_float, PrototypeStatement_cmp.class);
        
        
        map.put(Op_neg_int, PrototypeStatement_unop.class);
        map.put(Op_not_int , PrototypeStatement_unop.class);
        map.put(Op_neg_long , PrototypeStatement_unop.class);
        map.put(Op_not_long , PrototypeStatement_unop.class);
        map.put(Op_neg_float , PrototypeStatement_unop.class);
        map.put(Op_neg_double , PrototypeStatement_unop.class);
        map.put(Op_int_to_long , PrototypeStatement_unop.class);
        map.put(Op_int_to_float , PrototypeStatement_unop.class);
        map.put(Op_int_to_double , PrototypeStatement_unop.class);
        map.put(Op_long_to_int , PrototypeStatement_unop.class);
        map.put(Op_long_to_float , PrototypeStatement_unop.class);
        map.put(Op_long_to_double , PrototypeStatement_unop.class);
        map.put(Op_float_to_int , PrototypeStatement_unop.class);
        map.put(Op_float_to_long , PrototypeStatement_unop.class);
        map.put(Op_float_to_double , PrototypeStatement_unop.class);
        map.put(Op_double_to_int , PrototypeStatement_unop.class);
        map.put(Op_double_to_long , PrototypeStatement_unop.class);
        map.put(Op_double_to_float , PrototypeStatement_unop.class);
        map.put(Op_int_to_byte , PrototypeStatement_unop.class);
        map.put(Op_int_to_char , PrototypeStatement_unop.class);
        map.put(Op_int_to_short , PrototypeStatement_unop.class);
        
        
        map.put(Op_add_int , PrototypeStatement_binop.class);
        map.put(Op_sub_int , PrototypeStatement_binop.class);
        map.put(Op_mul_int , PrototypeStatement_binop.class);
        map.put(Op_div_int , PrototypeStatement_binop.class);
        map.put(Op_rem_int , PrototypeStatement_binop.class);
        map.put(Op_and_int , PrototypeStatement_binop.class);
        map.put(Op_or_int , PrototypeStatement_binop.class);
        map.put(Op_xor_int , PrototypeStatement_binop.class);
        map.put(Op_shl_int , PrototypeStatement_binop.class);
        map.put(Op_shr_int , PrototypeStatement_binop.class);
        map.put(Op_ushr_int , PrototypeStatement_binop.class);
        map.put(Op_add_long , PrototypeStatement_binop.class);
        map.put(Op_sub_long , PrototypeStatement_binop.class);
        map.put(Op_mul_long , PrototypeStatement_binop.class);
        map.put(Op_div_long , PrototypeStatement_binop.class);
        map.put(Op_rem_long , PrototypeStatement_binop.class);
        map.put(Op_and_long , PrototypeStatement_binop.class);
        map.put(Op_or_long , PrototypeStatement_binop.class);
        map.put(Op_xor_long , PrototypeStatement_binop.class);
        map.put(Op_shl_long , PrototypeStatement_binop.class);
        map.put(Op_shr_long , PrototypeStatement_binop.class);
        map.put(Op_ushr_long , PrototypeStatement_binop.class);
        map.put(Op_add_float , PrototypeStatement_binop.class);
        map.put(Op_sub_float , PrototypeStatement_binop.class);
        map.put(Op_mul_float , PrototypeStatement_binop.class);
        map.put(Op_div_float , PrototypeStatement_binop.class);
        map.put(Op_rem_float , PrototypeStatement_binop.class);
        map.put(Op_add_double , PrototypeStatement_binop.class);
        map.put(Op_sub_double , PrototypeStatement_binop.class);
        map.put(Op_mul_double , PrototypeStatement_binop.class);
        map.put(Op_div_double , PrototypeStatement_binop.class);
        map.put(Op_rem_double , PrototypeStatement_binop.class);
        
        map.put(Op_add_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_sub_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_mul_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_div_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_rem_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_and_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_or_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_xor_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_shl_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_shr_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_ushr_int_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_add_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_sub_long_2addr , PrototypeStatement_binop_2addr.class);;
        map.put(Op_mul_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_div_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_rem_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_and_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_or_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_xor_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_shl_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_shr_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_ushr_long_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_add_float_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_sub_float_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_mul_float_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_div_float_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_rem_float_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_add_double_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_sub_double_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_mul_double_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_div_double_2addr , PrototypeStatement_binop_2addr.class);
        map.put(Op_rem_double_2addr , PrototypeStatement_binop_2addr.class);
        
        map.put(Op_add_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_rsub_int , PrototypeStatement_binop_lit16.class);
        map.put(Op_mul_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_div_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_rem_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_and_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_or_int_lit16 , PrototypeStatement_binop_lit16.class);
        map.put(Op_xor_int_lit16  , PrototypeStatement_binop_lit16.class);

        map.put(Op_add_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_rsub_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_mul_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_div_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_rem_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_and_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_or_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_xor_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_shl_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_shr_int_lit8 , PrototypeStatement_binop_lit16.class);
        map.put(Op_ushr_int_lit8 , PrototypeStatement_binop_lit16.class);
    }
    
    public static Class<? extends PrototypeStatement> getByOpcode(String op_pre){
        if(op_pre == null || op_pre.length() != 2){
            throw new RuntimeException();
        }
        return map.get(op_pre);
    }
}




























