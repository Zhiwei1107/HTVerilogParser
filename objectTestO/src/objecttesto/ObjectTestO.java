/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package objecttesto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 *
 * @author reza
 */
public class ObjectTestO {
    
    private static boolean debug = false;
    private static String[] WiresArray;
    private static String[] WiresCountArray;
    private static int totalWireUsageCount;
    private static String gateLevelNetList;
    private static String[] gatesDefinitionLines;
    private static ArrayList<String> usedGateList;
    private static String[] primaryInputs;
    private static String[] primaryOutputs;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
//          String verilogFilePath="/Users/reza/Desktop/s38584_scan.v";
//          parseVerilogFile(verilogFilePath);
//          String Trojans="HADDX1 U1_1_30_zero ( .A0(Clock_counter[30]), .B0(carry_zero[30]), .C1(carry_zero[31]), .SO(N318) );\n" +
//"HADDX1 U1_1_29_zero ( .A0(Clock_counter[29]), .B0(carry_zero[29]), .C1(carry_zero[30]), .SO(N317) );\n" +
//"HADDX1 U1_1_28_zero ( .A0(Clock_counter[28]), .B0(carry_zero[28]), .C1(carry_zero[29]), .SO(N316) );\n" +
//"HADDX1 U1_1_24_zero ( .A0(Clock_counter[24]), .B0(carry_zero[24]), .C1(carry_zero[25]), .SO(N312) );\n" +
//"HADDX1 U1_1_23_zero ( .A0(Clock_counter[23]), .B0(carry_zero[23]), .C1(carry_zero[24]), .SO(N311) );\n" +
//"HADDX1 U1_1_22_zero ( .A0(Clock_counter[22]), .B0(carry_zero[22]), .C1(carry_zero[23]), .SO(N310) );\n" +
//"HADDX1 U1_1_18_zero ( .A0(Clock_counter[18]), .B0(carry_zero[18]), .C1(carry_zero[19]), .SO(N306) );\n" +
//"HADDX1 U1_1_17_zero ( .A0(Clock_counter[17]), .B0(carry_zero[17]), .C1(carry_zero[18]), .SO(N305) );\n" +
//"HADDX1 U1_1_16_zero ( .A0(Clock_counter[16]), .B0(carry_zero[16]), .C1(carry_zero[17]), .SO(N304) );\n" +
//"HADDX1 U1_1_12_zero ( .A0(Clock_counter[12]), .B0(carry_zero[12]), .C1(carry_zero[13]), .SO(N300) );\n" +
//"HADDX1 U1_1_11_zero ( .A0(Clock_counter[11]), .B0(carry_zero[11]), .C1(carry_zero[12]), .SO(N299) );\n" +
//"HADDX1 U1_1_10_zero ( .A0(Clock_counter[10]), .B0(carry_zero[10]), .C1(carry_zero[11]), .SO(N298) );\n" +
//"HADDX1 U1_1_6_zero ( .A0(Clock_counter[6]), .B0(carry_zero[6]), .C1(carry_zero[7]), .SO(N294) );\n" +
//"HADDX1 U1_1_5_zero ( .A0(Clock_counter[5]), .B0(carry_zero[5]), .C1(carry_zero[6]), .SO(N293) );\n" +
//"HADDX1 U1_1_4_zero ( .A0(Clock_counter[4]), .B0(carry_zero[4]), .C1(carry_zero[5]), .SO(N292) );\n" +
//"HADDX1 U1_1_27_zero ( .A0(Clock_counter[27]), .B0(carry_zero[27]), .C1(carry_zero[28]), .SO(N315) );\n" +
//"HADDX1 U1_1_26_zero ( .A0(Clock_counter[26]), .B0(carry_zero[26]), .C1(carry_zero[27]), .SO(N314) );\n" +
//"HADDX1 U1_1_25_zero ( .A0(Clock_counter[25]), .B0(carry_zero[25]), .C1(carry_zero[26]), .SO(N313) );\n" +
//"HADDX1 U1_1_21_zero ( .A0(Clock_counter[21]), .B0(carry_zero[21]), .C1(carry_zero[22]), .SO(N309) );\n" +
//"HADDX1 U1_1_20_zero ( .A0(Clock_counter[20]), .B0(carry_zero[20]), .C1(carry_zero[21]), .SO(N308) );\n" +
//"HADDX1 U1_1_19_zero ( .A0(Clock_counter[19]), .B0(carry_zero[19]), .C1(carry_zero[20]), .SO(N307) );\n" +
//"HADDX1 U1_1_15_zero ( .A0(Clock_counter[15]), .B0(carry_zero[15]), .C1(carry_zero[16]), .SO(N303) );\n" +
//"HADDX1 U1_1_14_zero ( .A0(Clock_counter[14]), .B0(carry_zero[14]), .C1(carry_zero[15]), .SO(N302) );\n" +
//"HADDX1 U1_1_13_zero ( .A0(Clock_counter[13]), .B0(carry_zero[13]), .C1(carry_zero[14]), .SO(N301) );\n" +
//"HADDX1 U1_1_9_zero ( .A0(Clock_counter[9]), .B0(carry_zero[9]), .C1(carry_zero[10]), .SO(N297) );\n" +
//"HADDX1 U1_1_8_zero ( .A0(Clock_counter[8]), .B0(carry_zero[8]), .C1(carry_zero[9]), .SO(N296) );\n" +
//"HADDX1 U1_1_7_zero ( .A0(Clock_counter[7]), .B0(carry_zero[7]), .C1(carry_zero[8]), .SO(N295) );\n" +
//"HADDX1 U1_1_3_zero ( .A0(Clock_counter[3]), .B0(carry_zero[3]), .C1(carry_zero[4]), .SO(N291) );\n" +
//"HADDX1 U1_1_2_zero ( .A0(Clock_counter[2]), .B0(carry_zero[2]), .C1(carry_zero[3]), .SO(N290) );\n" +
//"HADDX1 U1_1_1_zero ( .A0(Clock_counter[1]), .B0(Clock_counter[0]), .C1(carry_zero[2]), .SO(N289) );\n" +
//"INVX0 U1_zero ( .IN(Clock_counter[0]), .QN(N288) );\n" +
//"XOR2X1 U2_zero ( .IN1(carry_zero[31]), .IN2(Clock_counter[31]), .Q(N319) );\n" +
//"\n" +
//"HADDX1 U1_1_30_one ( .A0(In0_counter[30]), .B0(carry_one[30]), .C1(carry_one[31]), .SO(N280) );\n" +
//"HADDX1 U1_1_1_one ( .A0(In0_counter[1]), .B0(In0_counter[0]), .C1(carry_one[2]), .SO(N251) );\n" +
//"HADDX1 U1_1_4_one ( .A0(In0_counter[4]), .B0(carry_one[4]), .C1(carry_one[5]), .SO(N254) );\n" +
//"HADDX1 U1_1_5_one ( .A0(In0_counter[5]), .B0(carry_one[5]), .C1(carry_one[6]), .SO(N255) );\n" +
//"HADDX1 U1_1_6_one ( .A0(In0_counter[6]), .B0(carry_one[6]), .C1(carry_one[7]), .SO(N256) );\n" +
//"HADDX1 U1_1_7_one ( .A0(In0_counter[7]), .B0(carry_one[7]), .C1(carry_one[8]), .SO(N257) );\n" +
//"HADDX1 U1_1_11_one ( .A0(In0_counter[11]), .B0(carry_one[11]), .C1(carry_one[12]), .SO(N261) );\n" +
//"HADDX1 U1_1_12_one ( .A0(In0_counter[12]), .B0(carry_one[12]), .C1(carry_one[13]), .SO(N262) );\n" +
//"HADDX1 U1_1_13_one ( .A0(In0_counter[13]), .B0(carry_one[13]), .C1(carry_one[14]), .SO(N263) );\n" +
//"HADDX1 U1_1_18_one ( .A0(In0_counter[18]), .B0(carry_one[18]), .C1(carry_one[19]), .SO(N268) );\n" +
//"HADDX1 U1_1_19_one ( .A0(In0_counter[19]), .B0(carry_one[19]), .C1(carry_one[20]), .SO(N269) );\n" +
//"HADDX1 U1_1_20_one ( .A0(In0_counter[20]), .B0(carry_one[20]), .C1(carry_one[21]), .SO(N270) );\n" +
//"HADDX1 U1_1_24_one ( .A0(In0_counter[24]), .B0(carry_one[24]), .C1(carry_one[25]), .SO(N274) );\n" +
//"HADDX1 U1_1_25_one ( .A0(In0_counter[25]), .B0(carry_one[25]), .C1(carry_one[26]), .SO(N275) );\n" +
//"HADDX1 U1_1_26_one ( .A0(In0_counter[26]), .B0(carry_one[26]), .C1(carry_one[27]), .SO(N276) );\n" +
//"HADDX1 U1_1_2_one ( .A0(In0_counter[2]), .B0(carry_one[2]), .C1(carry_one[3]), .SO(N252) );\n" +
//"HADDX1 U1_1_3_one ( .A0(In0_counter[3]), .B0(carry_one[3]), .C1(carry_one[4]), .SO(N253) );\n" +
//"HADDX1 U1_1_8_one ( .A0(In0_counter[8]), .B0(carry_one[8]), .C1(carry_one[9]), .SO(N258) );\n" +
//"HADDX1 U1_1_9_one ( .A0(In0_counter[9]), .B0(carry_one[9]), .C1(carry_one[10]), .SO(N259) );\n" +
//"HADDX1 U1_1_10_one ( .A0(In0_counter[10]), .B0(carry_one[10]), .C1(carry_one[11]), .SO(N260) );\n" +
//"HADDX1 U1_1_14_one ( .A0(In0_counter[14]), .B0(carry_one[14]), .C1(carry_one[15]), .SO(N264) );\n" +
//"HADDX1 U1_1_15_one ( .A0(In0_counter[15]), .B0(carry_one[15]), .C1(carry_one[16]), .SO(N265) );\n" +
//"HADDX1 U1_1_16_one ( .A0(In0_counter[16]), .B0(carry_one[16]), .C1(carry_one[17]), .SO(N266) );\n" +
//"HADDX1 U1_1_17_one ( .A0(In0_counter[17]), .B0(carry_one[17]), .C1(carry_one[18]), .SO(N267) );\n" +
//"HADDX1 U1_1_21_one ( .A0(In0_counter[21]), .B0(carry_one[21]), .C1(carry_one[22]), .SO(N271) );\n" +
//"HADDX1 U1_1_22_one ( .A0(In0_counter[22]), .B0(carry_one[22]), .C1(carry_one[23]), .SO(N272) );\n" +
//"HADDX1 U1_1_23_one ( .A0(In0_counter[23]), .B0(carry_one[23]), .C1(carry_one[24]), .SO(N273) );\n" +
//"HADDX1 U1_1_27_one ( .A0(In0_counter[27]), .B0(carry_one[27]), .C1(carry_one[28]), .SO(N277) );\n" +
//"HADDX1 U1_1_28_one ( .A0(In0_counter[28]), .B0(carry_one[28]), .C1(carry_one[29]), .SO(N278) );\n" +
//"HADDX1 U1_1_29_one ( .A0(In0_counter[29]), .B0(carry_one[29]), .C1(carry_one[30]), .SO(N279) );\n" +
//"INVX0 U1_one ( .IN(In0_counter[0]), .QN(N250) );\n" +
//"XOR2X1 U2_one ( .IN1(carry_one[31]), .IN2(In0_counter[31]), .Q(N281) );\n" +
//"\n" +
//"HADDX1 U1_1_30_three ( .A0(In2_counter[30]), .B0(carry_three[30]), .C1(carry_three[31]), .SO(N210) );\n" +
//"HADDX1 U1_1_1_three ( .A0(In2_counter[1]), .B0(In2_counter[0]), .C1(carry_three[2]), .SO(N181) );\n" +
//"HADDX1 U1_1_4_three ( .A0(In2_counter[4]), .B0(carry_three[4]), .C1(carry_three[5]), .SO(N184) );\n" +
//"HADDX1 U1_1_5_three ( .A0(In2_counter[5]), .B0(carry_three[5]), .C1(carry_three[6]), .SO(N185) );\n" +
//"HADDX1 U1_1_6_three ( .A0(In2_counter[6]), .B0(carry_three[6]), .C1(carry_three[7]), .SO(N186) );\n" +
//"HADDX1 U1_1_7_three ( .A0(In2_counter[7]), .B0(carry_three[7]), .C1(carry_three[8]), .SO(N187) );\n" +
//"HADDX1 U1_1_11_three ( .A0(In2_counter[11]), .B0(carry_three[11]), .C1(carry_three[12]), .SO(N191) );\n" +
//"HADDX1 U1_1_12_three ( .A0(In2_counter[12]), .B0(carry_three[12]), .C1(carry_three[13]), .SO(N192) );\n" +
//"HADDX1 U1_1_13_three ( .A0(In2_counter[13]), .B0(carry_three[13]), .C1(carry_three[14]), .SO(N193) );\n" +
//"HADDX1 U1_1_18_three ( .A0(In2_counter[18]), .B0(carry_three[18]), .C1(carry_three[19]), .SO(N198) );\n" +
//"HADDX1 U1_1_19_three ( .A0(In2_counter[19]), .B0(carry_three[19]), .C1(carry_three[20]), .SO(N199) );\n" +
//"HADDX1 U1_1_20_three ( .A0(In2_counter[20]), .B0(carry_three[20]), .C1(carry_three[21]), .SO(N200) );\n" +
//"HADDX1 U1_1_24_three ( .A0(In2_counter[24]), .B0(carry_three[24]), .C1(carry_three[25]), .SO(N204) );\n" +
//"HADDX1 U1_1_25_three ( .A0(In2_counter[25]), .B0(carry_three[25]), .C1(carry_three[26]), .SO(N205) );\n" +
//"HADDX1 U1_1_26_three ( .A0(In2_counter[26]), .B0(carry_three[26]), .C1(carry_three[27]), .SO(N206) );\n" +
//"HADDX1 U1_1_2_three ( .A0(In2_counter[2]), .B0(carry_three[2]), .C1(carry_three[3]), .SO(N182) );\n" +
//"HADDX1 U1_1_3_three ( .A0(In2_counter[3]), .B0(carry_three[3]), .C1(carry_three[4]), .SO(N183) );\n" +
//"HADDX1 U1_1_8_three ( .A0(In2_counter[8]), .B0(carry_three[8]), .C1(carry_three[9]), .SO(N188) );\n" +
//"HADDX1 U1_1_9_three ( .A0(In2_counter[9]), .B0(carry_three[9]), .C1(carry_three[10]), .SO(N189) );\n" +
//"HADDX1 U1_1_10_three ( .A0(In2_counter[10]), .B0(carry_three[10]), .C1(carry_three[11]), .SO(N190) );\n" +
//"HADDX1 U1_1_14_three ( .A0(In2_counter[14]), .B0(carry_three[14]), .C1(carry_three[15]), .SO(N194) );\n" +
//"HADDX1 U1_1_15_three ( .A0(In2_counter[15]), .B0(carry_three[15]), .C1(carry_three[16]), .SO(N195) );\n" +
//"HADDX1 U1_1_16_three ( .A0(In2_counter[16]), .B0(carry_three[16]), .C1(carry_three[17]), .SO(N196) );\n" +
//"HADDX1 U1_1_17_three ( .A0(In2_counter[17]), .B0(carry_three[17]), .C1(carry_three[18]), .SO(N197) );\n" +
//"HADDX1 U1_1_21_three ( .A0(In2_counter[21]), .B0(carry_three[21]), .C1(carry_three[22]), .SO(N201) );\n" +
//"HADDX1 U1_1_22_three ( .A0(In2_counter[22]), .B0(carry_three[22]), .C1(carry_three[23]), .SO(N202) );\n" +
//"HADDX1 U1_1_23_three ( .A0(In2_counter[23]), .B0(carry_three[23]), .C1(carry_three[24]), .SO(N203) );\n" +
//"HADDX1 U1_1_27_three ( .A0(In2_counter[27]), .B0(carry_three[27]), .C1(carry_three[28]), .SO(N207) );\n" +
//"HADDX1 U1_1_28_three ( .A0(In2_counter[28]), .B0(carry_three[28]), .C1(carry_three[29]), .SO(N208) );\n" +
//"HADDX1 U1_1_29_three ( .A0(In2_counter[29]), .B0(carry_three[29]), .C1(carry_three[30]), .SO(N209) );\n" +
//"INVX0 U1_three ( .IN(In2_counter[0]), .QN(N180) );\n" +
//"XOR2X1 U2_three ( .IN1(carry_three[31]), .IN2(In2_counter[31]), .Q(N211) );\n" +
//"\n" +
//"HADDX1 U1_1_30_five ( .A0(In4_counter[30]), .B0(carry_five[30]), .C1(carry_five[31]), .SO(N140) );\n" +
//"HADDX1 U1_1_1_five ( .A0(In4_counter[1]), .B0(In4_counter[0]), .C1(carry_five[2]), .SO(N111) );\n" +
//"HADDX1 U1_1_4_five ( .A0(In4_counter[4]), .B0(carry_five[4]), .C1(carry_five[5]), .SO(N114) );\n" +
//"HADDX1 U1_1_5_five ( .A0(In4_counter[5]), .B0(carry_five[5]), .C1(carry_five[6]), .SO(N115) );\n" +
//"HADDX1 U1_1_6_five ( .A0(In4_counter[6]), .B0(carry_five[6]), .C1(carry_five[7]), .SO(N116) );\n" +
//"HADDX1 U1_1_7_five ( .A0(In4_counter[7]), .B0(carry_five[7]), .C1(carry_five[8]), .SO(N117) );\n" +
//"HADDX1 U1_1_11_five ( .A0(In4_counter[11]), .B0(carry_five[11]), .C1(carry_five[12]), .SO(N121) );\n" +
//"HADDX1 U1_1_12_five ( .A0(In4_counter[12]), .B0(carry_five[12]), .C1(carry_five[13]), .SO(N122) );\n" +
//"HADDX1 U1_1_13_five ( .A0(In4_counter[13]), .B0(carry_five[13]), .C1(carry_five[14]), .SO(N123) );\n" +
//"HADDX1 U1_1_18_five ( .A0(In4_counter[18]), .B0(carry_five[18]), .C1(carry_five[19]), .SO(N128) );\n" +
//"HADDX1 U1_1_19_five ( .A0(In4_counter[19]), .B0(carry_five[19]), .C1(carry_five[20]), .SO(N129) );\n" +
//"HADDX1 U1_1_20_five ( .A0(In4_counter[20]), .B0(carry_five[20]), .C1(carry_five[21]), .SO(N130) );\n" +
//"HADDX1 U1_1_24_five ( .A0(In4_counter[24]), .B0(carry_five[24]), .C1(carry_five[25]), .SO(N134) );\n" +
//"HADDX1 U1_1_25_five ( .A0(In4_counter[25]), .B0(carry_five[25]), .C1(carry_five[26]), .SO(N135) );\n" +
//"HADDX1 U1_1_26_five ( .A0(In4_counter[26]), .B0(carry_five[26]), .C1(carry_five[27]), .SO(N136) );\n" +
//"HADDX1 U1_1_2_five ( .A0(In4_counter[2]), .B0(carry_five[2]), .C1(carry_five[3]), .SO(N112) );\n" +
//"HADDX1 U1_1_3_five ( .A0(In4_counter[3]), .B0(carry_five[3]), .C1(carry_five[4]), .SO(N113) );\n" +
//"HADDX1 U1_1_8_five ( .A0(In4_counter[8]), .B0(carry_five[8]), .C1(carry_five[9]), .SO(N118) );\n" +
//"HADDX1 U1_1_9_five ( .A0(In4_counter[9]), .B0(carry_five[9]), .C1(carry_five[10]), .SO(N119) );\n" +
//"HADDX1 U1_1_10_five ( .A0(In4_counter[10]), .B0(carry_five[10]), .C1(carry_five[11]), .SO(N120) );\n" +
//"HADDX1 U1_1_14_five ( .A0(In4_counter[14]), .B0(carry_five[14]), .C1(carry_five[15]), .SO(N124) );\n" +
//"HADDX1 U1_1_15_five ( .A0(In4_counter[15]), .B0(carry_five[15]), .C1(carry_five[16]), .SO(N125) );\n" +
//"HADDX1 U1_1_16_five ( .A0(In4_counter[16]), .B0(carry_five[16]), .C1(carry_five[17]), .SO(N126) );\n" +
//"HADDX1 U1_1_17_five ( .A0(In4_counter[17]), .B0(carry_five[17]), .C1(carry_five[18]), .SO(N127) );\n" +
//"HADDX1 U1_1_21_five ( .A0(In4_counter[21]), .B0(carry_five[21]), .C1(carry_five[22]), .SO(N131) );\n" +
//"HADDX1 U1_1_22_five ( .A0(In4_counter[22]), .B0(carry_five[22]), .C1(carry_five[23]), .SO(N132) );\n" +
//"HADDX1 U1_1_23_five ( .A0(In4_counter[23]), .B0(carry_five[23]), .C1(carry_five[24]), .SO(N133) );\n" +
//"HADDX1 U1_1_27_five ( .A0(In4_counter[27]), .B0(carry_five[27]), .C1(carry_five[28]), .SO(N137) );\n" +
//"HADDX1 U1_1_28_five ( .A0(In4_counter[28]), .B0(carry_five[28]), .C1(carry_five[29]), .SO(N138) );\n" +
//"HADDX1 U1_1_29_five ( .A0(In4_counter[29]), .B0(carry_five[29]), .C1(carry_five[30]), .SO(N139) );\n" +
//"INVX0 U1_five ( .IN(In4_counter[0]), .QN(N110) );\n" +
//"XOR2X1 U2_five ( .IN1(carry_five[31]), .IN2(In4_counter[31]), .Q(N141) );\n" +
//"\n" +
//"HADDX1 U1_1_1_six ( .A0(In5_counter[1]), .B0(In5_counter[0]), .C1(carry_six[2]), .SO(N76) );\n" +
//"HADDX1 U1_1_4_six ( .A0(In5_counter[4]), .B0(carry_six[4]), .C1(carry_six[5]), .SO(N79) );\n" +
//"HADDX1 U1_1_5_six ( .A0(In5_counter[5]), .B0(carry_six[5]), .C1(carry_six[6]), .SO(N80) );\n" +
//"HADDX1 U1_1_6_six ( .A0(In5_counter[6]), .B0(carry_six[6]), .C1(carry_six[7]), .SO(N81) );\n" +
//"HADDX1 U1_1_7_six ( .A0(In5_counter[7]), .B0(carry_six[7]), .C1(carry_six[8]), .SO(N82) );\n" +
//"HADDX1 U1_1_11_six ( .A0(In5_counter[11]), .B0(carry_six[11]), .C1(carry_six[12]), .SO(N86) );\n" +
//"HADDX1 U1_1_12_six ( .A0(In5_counter[12]), .B0(carry_six[12]), .C1(carry_six[13]), .SO(N87) );\n" +
//"HADDX1 U1_1_13_six ( .A0(In5_counter[13]), .B0(carry_six[13]), .C1(carry_six[14]), .SO(N88) );\n" +
//"HADDX1 U1_1_18_six ( .A0(In5_counter[18]), .B0(carry_six[18]), .C1(carry_six[19]), .SO(N93) );\n" +
//"HADDX1 U1_1_19_six ( .A0(In5_counter[19]), .B0(carry_six[19]), .C1(carry_six[20]), .SO(N94) );\n" +
//"HADDX1 U1_1_20_six ( .A0(In5_counter[20]), .B0(carry_six[20]), .C1(carry_six[21]), .SO(N95) );\n" +
//"HADDX1 U1_1_24_six ( .A0(In5_counter[24]), .B0(carry_six[24]), .C1(carry_six[25]), .SO(N99) );\n" +
//"HADDX1 U1_1_25_six ( .A0(In5_counter[25]), .B0(carry_six[25]), .C1(carry_six[26]), .SO(N100) );\n" +
//"HADDX1 U1_1_26_six ( .A0(In5_counter[26]), .B0(carry_six[26]), .C1(carry_six[27]), .SO(N101) );\n" +
//"HADDX1 U1_1_2_six ( .A0(In5_counter[2]), .B0(carry_six[2]), .C1(carry_six[3]), .SO(N77) );\n" +
//"HADDX1 U1_1_3_six ( .A0(In5_counter[3]), .B0(carry_six[3]), .C1(carry_six[4]), .SO(N78) );\n" +
//"HADDX1 U1_1_8_six ( .A0(In5_counter[8]), .B0(carry_six[8]), .C1(carry_six[9]), .SO(N83) );\n" +
//"HADDX1 U1_1_9_six ( .A0(In5_counter[9]), .B0(carry_six[9]), .C1(carry_six[10]), .SO(N84) );\n" +
//"HADDX1 U1_1_10_six ( .A0(In5_counter[10]), .B0(carry_six[10]), .C1(carry_six[11]), .SO(N85) );\n" +
//"HADDX1 U1_1_14_six ( .A0(In5_counter[14]), .B0(carry_six[14]), .C1(carry_six[15]), .SO(N89) );\n" +
//"HADDX1 U1_1_15_six ( .A0(In5_counter[15]), .B0(carry_six[15]), .C1(carry_six[16]), .SO(N90) );\n" +
//"HADDX1 U1_1_16_six ( .A0(In5_counter[16]), .B0(carry_six[16]), .C1(carry_six[17]), .SO(N91) );\n" +
//"HADDX1 U1_1_17_six ( .A0(In5_counter[17]), .B0(carry_six[17]), .C1(carry_six[18]), .SO(N92) );\n" +
//"HADDX1 U1_1_21_six ( .A0(In5_counter[21]), .B0(carry_six[21]), .C1(carry_six[22]), .SO(N96) );\n" +
//"HADDX1 U1_1_22_six ( .A0(In5_counter[22]), .B0(carry_six[22]), .C1(carry_six[23]), .SO(N97) );\n" +
//"HADDX1 U1_1_23_six ( .A0(In5_counter[23]), .B0(carry_six[23]), .C1(carry_six[24]), .SO(N98) );\n" +
//"HADDX1 U1_1_27_six ( .A0(In5_counter[27]), .B0(carry_six[27]), .C1(carry_six[28]), .SO(N102) );\n" +
//"HADDX1 U1_1_28_six ( .A0(In5_counter[28]), .B0(carry_six[28]), .C1(carry_six[29]), .SO(N103) );\n" +
//"HADDX1 U1_1_29_six ( .A0(In5_counter[29]), .B0(carry_six[29]), .C1(carry_six[30]), .SO(N104) );\n" +
//"INVX0 U1_six ( .IN(In5_counter[0]), .QN(N75) );\n" +
//"XOR2X1 U2_six ( .IN1(carry_six[31]), .IN2(In5_counter[31]), .Q(N106) );\n" +
//"\n" +
//"HADDX1 U1_1_30_eight ( .A0(In7_counter[30]), .B0(carry_eight[30]), .C1(carry_eight[31]), .SO(N35) );\n" +
//"HADDX1 U1_1_1_eight ( .A0(In7_counter[1]), .B0(In7_counter[0]), .C1(carry_eight[2]), .SO(N6) );\n" +
//"HADDX1 U1_1_4_eight ( .A0(In7_counter[4]), .B0(carry_eight[4]), .C1(carry_eight[5]), .SO(N9) );\n" +
//"HADDX1 U1_1_5_eight ( .A0(In7_counter[5]), .B0(carry_eight[5]), .C1(carry_eight[6]), .SO(N10) );\n" +
//"HADDX1 U1_1_6_eight ( .A0(In7_counter[6]), .B0(carry_eight[6]), .C1(carry_eight[7]), .SO(N11) );\n" +
//"HADDX1 U1_1_7_eight ( .A0(In7_counter[7]), .B0(carry_eight[7]), .C1(carry_eight[8]), .SO(N12) );\n" +
//"HADDX1 U1_1_11_eight ( .A0(In7_counter[11]), .B0(carry_eight[11]), .C1(carry_eight[12]), .SO(N16) );\n" +
//"HADDX1 U1_1_12_eight ( .A0(In7_counter[12]), .B0(carry_eight[12]), .C1(carry_eight[13]), .SO(N17) );\n" +
//"HADDX1 U1_1_13_eight ( .A0(In7_counter[13]), .B0(carry_eight[13]), .C1(carry_eight[14]), .SO(N18) );\n" +
//"HADDX1 U1_1_18_eight ( .A0(In7_counter[18]), .B0(carry_eight[18]), .C1(carry_eight[19]), .SO(N23) );\n" +
//"HADDX1 U1_1_19_eight ( .A0(In7_counter[19]), .B0(carry_eight[19]), .C1(carry_eight[20]), .SO(N24) );\n" +
//"HADDX1 U1_1_20_eight ( .A0(In7_counter[20]), .B0(carry_eight[20]), .C1(carry_eight[21]), .SO(N25) );\n" +
//"HADDX1 U1_1_24_eight ( .A0(In7_counter[24]), .B0(carry_eight[24]), .C1(carry_eight[25]), .SO(N29) );\n" +
//"HADDX1 U1_1_25_eight ( .A0(In7_counter[25]), .B0(carry_eight[25]), .C1(carry_eight[26]), .SO(N30) );\n" +
//"HADDX1 U1_1_26_eight ( .A0(In7_counter[26]), .B0(carry_eight[26]), .C1(carry_eight[27]), .SO(N31) );\n" +
//"HADDX1 U1_1_2_eight ( .A0(In7_counter[2]), .B0(carry_eight[2]), .C1(carry_eight[3]), .SO(N7) );\n" +
//"HADDX1 U1_1_3_eight ( .A0(In7_counter[3]), .B0(carry_eight[3]), .C1(carry_eight[4]), .SO(N8) );\n" +
//"HADDX1 U1_1_8_eight ( .A0(In7_counter[8]), .B0(carry_eight[8]), .C1(carry_eight[9]), .SO(N13) );\n" +
//"HADDX1 U1_1_9_eight ( .A0(In7_counter[9]), .B0(carry_eight[9]), .C1(carry_eight[10]), .SO(N14) );\n" +
//"HADDX1 U1_1_10_eight ( .A0(In7_counter[10]), .B0(carry_eight[10]), .C1(carry_eight[11]), .SO(N15) );\n" +
//"HADDX1 U1_1_14_eight ( .A0(In7_counter[14]), .B0(carry_eight[14]), .C1(carry_eight[15]), .SO(N19) );\n" +
//"HADDX1 U1_1_15_eight ( .A0(In7_counter[15]), .B0(carry_eight[15]), .C1(carry_eight[16]), .SO(N20) );\n" +
//"HADDX1 U1_1_16_eight ( .A0(In7_counter[16]), .B0(carry_eight[16]), .C1(carry_eight[17]), .SO(N21) );\n" +
//"HADDX1 U1_1_17_eight ( .A0(In7_counter[17]), .B0(carry_eight[17]), .C1(carry_eight[18]), .SO(N22) );\n" +
//"HADDX1 U1_1_21_eight ( .A0(In7_counter[21]), .B0(carry_eight[21]), .C1(carry_eight[22]), .SO(N26) );\n" +
//"HADDX1 U1_1_22_eight ( .A0(In7_counter[22]), .B0(carry_eight[22]), .C1(carry_eight[23]), .SO(N27) );\n" +
//"HADDX1 U1_1_23_eight ( .A0(In7_counter[23]), .B0(carry_eight[23]), .C1(carry_eight[24]), .SO(N28) );\n" +
//"HADDX1 U1_1_27_eight ( .A0(In7_counter[27]), .B0(carry_eight[27]), .C1(carry_eight[28]), .SO(N32) );\n" +
//"HADDX1 U1_1_28_eight ( .A0(In7_counter[28]), .B0(carry_eight[28]), .C1(carry_eight[29]), .SO(N33) );\n" +
//"HADDX1 U1_1_29_eight ( .A0(In7_counter[29]), .B0(carry_eight[29]), .C1(carry_eight[30]), .SO(N34) );\n" +
//"INVX0 U1_eight ( .IN(In7_counter[0]), .QN(N5) );\n" +
//"XOR2X1 U2_eight ( .IN1(carry_eight[31]), .IN2(In7_counter[31]), .Q(N36) );\n" +
//"\n" +
//"HADDX1 U1_1_30_two ( .A0(In1_counter[30]), .B0(carry_two[30]), .C1(carry_two[31]), .SO(N245) );\n" +
//"HADDX1 U1_1_1_two ( .A0(In1_counter[1]), .B0(In1_counter[0]), .C1(carry_two[2]), .SO(N216) );\n" +
//"HADDX1 U1_1_4_two ( .A0(In1_counter[4]), .B0(carry_two[4]), .C1(carry_two[5]), .SO(N219) );\n" +
//"HADDX1 U1_1_5_two ( .A0(In1_counter[5]), .B0(carry_two[5]), .C1(carry_two[6]), .SO(N220) );\n" +
//"HADDX1 U1_1_6_two ( .A0(In1_counter[6]), .B0(carry_two[6]), .C1(carry_two[7]), .SO(N221) );\n" +
//"HADDX1 U1_1_7_two ( .A0(In1_counter[7]), .B0(carry_two[7]), .C1(carry_two[8]), .SO(N222) );\n" +
//"HADDX1 U1_1_8_two ( .A0(In1_counter[8]), .B0(carry_two[8]), .C1(carry_two[9]), .SO(N223) );\n" +
//"HADDX1 U1_1_12_two ( .A0(In1_counter[12]), .B0(carry_two[12]), .C1(carry_two[13]), .SO(N227) );\n" +
//"HADDX1 U1_1_13_two ( .A0(In1_counter[13]), .B0(carry_two[13]), .C1(carry_two[14]), .SO(N228) );\n" +
//"HADDX1 U1_1_14_two ( .A0(In1_counter[14]), .B0(carry_two[14]), .C1(carry_two[15]), .SO(N229) );\n" +
//"HADDX1 U1_1_19_two ( .A0(In1_counter[19]), .B0(carry_two[19]), .C1(carry_two[20]), .SO(N234) );\n" +
//"HADDX1 U1_1_20_two ( .A0(In1_counter[20]), .B0(carry_two[20]), .C1(carry_two[21]), .SO(N235) );\n" +
//"HADDX1 U1_1_21_two ( .A0(In1_counter[21]), .B0(carry_two[21]), .C1(carry_two[22]), .SO(N236) );\n" +
//"HADDX1 U1_1_25_two ( .A0(In1_counter[25]), .B0(carry_two[25]), .C1(carry_two[26]), .SO(N240) );\n" +
//"HADDX1 U1_1_26_two ( .A0(In1_counter[26]), .B0(carry_two[26]), .C1(carry_two[27]), .SO(N241) );\n" +
//"HADDX1 U1_1_27_two ( .A0(In1_counter[27]), .B0(carry_two[27]), .C1(carry_two[28]), .SO(N242) );\n" +
//"HADDX1 U1_1_2_two ( .A0(In1_counter[2]), .B0(carry_two[2]), .C1(carry_two[3]), .SO(N217) );\n" +
//"HADDX1 U1_1_3_two ( .A0(In1_counter[3]), .B0(carry_two[3]), .C1(carry_two[4]), .SO(N218) );\n" +
//"HADDX1 U1_1_9_two ( .A0(In1_counter[9]), .B0(carry_two[9]), .C1(carry_two[10]), .SO(N224) );\n" +
//"HADDX1 U1_1_10_two ( .A0(In1_counter[10]), .B0(carry_two[10]), .C1(carry_two[11]), .SO(N225) );\n" +
//"HADDX1 U1_1_11_two ( .A0(In1_counter[11]), .B0(carry_two[11]), .C1(carry_two[12]), .SO(N226) );\n" +
//"HADDX1 U1_1_15_two ( .A0(In1_counter[15]), .B0(carry_two[15]), .C1(carry_two[16]), .SO(N230) );\n" +
//"HADDX1 U1_1_16_two ( .A0(In1_counter[16]), .B0(carry_two[16]), .C1(carry_two[17]), .SO(N231) );\n" +
//"HADDX1 U1_1_17_two ( .A0(In1_counter[17]), .B0(carry_two[17]), .C1(carry_two[18]), .SO(N232) );\n" +
//"HADDX1 U1_1_18_two ( .A0(In1_counter[18]), .B0(carry_two[18]), .C1(carry_two[19]), .SO(N233) );\n" +
//"HADDX1 U1_1_22_two ( .A0(In1_counter[22]), .B0(carry_two[22]), .C1(carry_two[23]), .SO(N237) );\n" +
//"HADDX1 U1_1_23_two ( .A0(In1_counter[23]), .B0(carry_two[23]), .C1(carry_two[24]), .SO(N238) );\n" +
//"HADDX1 U1_1_24_two ( .A0(In1_counter[24]), .B0(carry_two[24]), .C1(carry_two[25]), .SO(N239) );\n" +
//"HADDX1 U1_1_28_two ( .A0(In1_counter[28]), .B0(carry_two[28]), .C1(carry_two[29]), .SO(N243) );\n" +
//"HADDX1 U1_1_29_two ( .A0(In1_counter[29]), .B0(carry_two[29]), .C1(carry_two[30]), .SO(N244) );\n" +
//"INVX0 U1_two ( .IN(In1_counter[0]), .QN(N215) );\n" +
//"XOR2X1 U2_two ( .IN1(carry_two[31]), .IN2(In1_counter[31]), .Q(N246) );\n" +
//"\n" +
//"HADDX1 U1_1_30_four ( .A0(In3_counter[30]), .B0(carry_four[30]), .C1(carry_four[31]), .SO(N175) );\n" +
//"HADDX1 U1_1_1_four ( .A0(In3_counter[1]), .B0(In3_counter[0]), .C1(carry_four[2]), .SO(N146) );\n" +
//"HADDX1 U1_1_4_four ( .A0(In3_counter[4]), .B0(carry_four[4]), .C1(carry_four[5]), .SO(N149) );\n" +
//"HADDX1 U1_1_5_four ( .A0(In3_counter[5]), .B0(carry_four[5]), .C1(carry_four[6]), .SO(N150) );\n" +
//"HADDX1 U1_1_6_four ( .A0(In3_counter[6]), .B0(carry_four[6]), .C1(carry_four[7]), .SO(N151) );\n" +
//"HADDX1 U1_1_7_four ( .A0(In3_counter[7]), .B0(carry_four[7]), .C1(carry_four[8]), .SO(N152) );\n" +
//"HADDX1 U1_1_8_four ( .A0(In3_counter[8]), .B0(carry_four[8]), .C1(carry_four[9]), .SO(N153) );\n" +
//"HADDX1 U1_1_12_four ( .A0(In3_counter[12]), .B0(carry_four[12]), .C1(carry_four[13]), .SO(N157) );\n" +
//"HADDX1 U1_1_13_four ( .A0(In3_counter[13]), .B0(carry_four[13]), .C1(carry_four[14]), .SO(N158) );\n" +
//"HADDX1 U1_1_14_four ( .A0(In3_counter[14]), .B0(carry_four[14]), .C1(carry_four[15]), .SO(N159) );\n" +
//"HADDX1 U1_1_19_four ( .A0(In3_counter[19]), .B0(carry_four[19]), .C1(carry_four[20]), .SO(N164) );\n" +
//"HADDX1 U1_1_20_four ( .A0(In3_counter[20]), .B0(carry_four[20]), .C1(carry_four[21]), .SO(N165) );\n" +
//"HADDX1 U1_1_21_four ( .A0(In3_counter[21]), .B0(carry_four[21]), .C1(carry_four[22]), .SO(N166) );\n" +
//"HADDX1 U1_1_25_four ( .A0(In3_counter[25]), .B0(carry_four[25]), .C1(carry_four[26]), .SO(N170) );\n" +
//"HADDX1 U1_1_26_four ( .A0(In3_counter[26]), .B0(carry_four[26]), .C1(carry_four[27]), .SO(N171) );\n" +
//"HADDX1 U1_1_27_four ( .A0(In3_counter[27]), .B0(carry_four[27]), .C1(carry_four[28]), .SO(N172) );\n" +
//"HADDX1 U1_1_2_four ( .A0(In3_counter[2]), .B0(carry_four[2]), .C1(carry_four[3]), .SO(N147) );\n" +
//"HADDX1 U1_1_3_four ( .A0(In3_counter[3]), .B0(carry_four[3]), .C1(carry_four[4]), .SO(N148) );\n" +
//"HADDX1 U1_1_9_four ( .A0(In3_counter[9]), .B0(carry_four[9]), .C1(carry_four[10]), .SO(N154) );\n" +
//"HADDX1 U1_1_10_four ( .A0(In3_counter[10]), .B0(carry_four[10]), .C1(carry_four[11]), .SO(N155) );\n" +
//"HADDX1 U1_1_11_four ( .A0(In3_counter[11]), .B0(carry_four[11]), .C1(carry_four[12]), .SO(N156) );\n" +
//"HADDX1 U1_1_15_four ( .A0(In3_counter[15]), .B0(carry_four[15]), .C1(carry_four[16]), .SO(N160) );\n" +
//"HADDX1 U1_1_16_four ( .A0(In3_counter[16]), .B0(carry_four[16]), .C1(carry_four[17]), .SO(N161) );\n" +
//"HADDX1 U1_1_17_four ( .A0(In3_counter[17]), .B0(carry_four[17]), .C1(carry_four[18]), .SO(N162) );\n" +
//"HADDX1 U1_1_18_four ( .A0(In3_counter[18]), .B0(carry_four[18]), .C1(carry_four[19]), .SO(N163) );\n" +
//"HADDX1 U1_1_22_four ( .A0(In3_counter[22]), .B0(carry_four[22]), .C1(carry_four[23]), .SO(N167) );\n" +
//"HADDX1 U1_1_23_four ( .A0(In3_counter[23]), .B0(carry_four[23]), .C1(carry_four[24]), .SO(N168) );\n" +
//"HADDX1 U1_1_24_four ( .A0(In3_counter[24]), .B0(carry_four[24]), .C1(carry_four[25]), .SO(N169) );\n" +
//"HADDX1 U1_1_28_four ( .A0(In3_counter[28]), .B0(carry_four[28]), .C1(carry_four[29]), .SO(N173) );\n" +
//"HADDX1 U1_1_29_four ( .A0(In3_counter[29]), .B0(carry_four[29]), .C1(carry_four[30]), .SO(N174) );\n" +
//"INVX0 U1_four ( .IN(In3_counter[0]), .QN(N145) );\n" +
//"XOR2X1 U2_four ( .IN1(carry_four[31]), .IN2(In3_counter[31]), .Q(N176) );\n" +
//"\n" +
//"HADDX1 U1_1_30_seven ( .A0(In6_counter[30]), .B0(carry_seven[30]), .C1(carry_seven[31]), .SO(N70) );\n" +
//"HADDX1 U1_1_1_seven ( .A0(In6_counter[1]), .B0(In6_counter[0]), .C1(carry_seven[2]), .SO(N41) );\n" +
//"HADDX1 U1_1_4_seven ( .A0(In6_counter[4]), .B0(carry_seven[4]), .C1(carry_seven[5]), .SO(N44) );\n" +
//"HADDX1 U1_1_5_seven ( .A0(In6_counter[5]), .B0(carry_seven[5]), .C1(carry_seven[6]), .SO(N45) );\n" +
//"HADDX1 U1_1_6_seven ( .A0(In6_counter[6]), .B0(carry_seven[6]), .C1(carry_seven[7]), .SO(N46) );\n" +
//"HADDX1 U1_1_7_seven ( .A0(In6_counter[7]), .B0(carry_seven[7]), .C1(carry_seven[8]), .SO(N47) );\n" +
//"HADDX1 U1_1_8_seven ( .A0(In6_counter[8]), .B0(carry_seven[8]), .C1(carry_seven[9]), .SO(N48) );\n" +
//"HADDX1 U1_1_12_seven ( .A0(In6_counter[12]), .B0(carry_seven[12]), .C1(carry_seven[13]), .SO(N52) );\n" +
//"HADDX1 U1_1_13_seven ( .A0(In6_counter[13]), .B0(carry_seven[13]), .C1(carry_seven[14]), .SO(N53) );\n" +
//"HADDX1 U1_1_14_seven ( .A0(In6_counter[14]), .B0(carry_seven[14]), .C1(carry_seven[15]), .SO(N54) );\n" +
//"HADDX1 U1_1_19_seven ( .A0(In6_counter[19]), .B0(carry_seven[19]), .C1(carry_seven[20]), .SO(N59) );\n" +
//"HADDX1 U1_1_20_seven ( .A0(In6_counter[20]), .B0(carry_seven[20]), .C1(carry_seven[21]), .SO(N60) );\n" +
//"HADDX1 U1_1_21_seven ( .A0(In6_counter[21]), .B0(carry_seven[21]), .C1(carry_seven[22]), .SO(N61) );\n" +
//"HADDX1 U1_1_25_seven ( .A0(In6_counter[25]), .B0(carry_seven[25]), .C1(carry_seven[26]), .SO(N65) );\n" +
//"HADDX1 U1_1_26_seven ( .A0(In6_counter[26]), .B0(carry_seven[26]), .C1(carry_seven[27]), .SO(N66) );\n" +
//"HADDX1 U1_1_27_seven ( .A0(In6_counter[27]), .B0(carry_seven[27]), .C1(carry_seven[28]), .SO(N67) );\n" +
//"HADDX1 U1_1_2_seven ( .A0(In6_counter[2]), .B0(carry_seven[2]), .C1(carry_seven[3]), .SO(N42) );\n" +
//"HADDX1 U1_1_3_seven ( .A0(In6_counter[3]), .B0(carry_seven[3]), .C1(carry_seven[4]), .SO(N43) );\n" +
//"HADDX1 U1_1_9_seven ( .A0(In6_counter[9]), .B0(carry_seven[9]), .C1(carry_seven[10]), .SO(N49) );\n" +
//"HADDX1 U1_1_10_seven ( .A0(In6_counter[10]), .B0(carry_seven[10]), .C1(carry_seven[11]), .SO(N50) );\n" +
//"HADDX1 U1_1_11_seven ( .A0(In6_counter[11]), .B0(carry_seven[11]), .C1(carry_seven[12]), .SO(N51) );\n" +
//"HADDX1 U1_1_15_seven ( .A0(In6_counter[15]), .B0(carry_seven[15]), .C1(carry_seven[16]), .SO(N55) );\n" +
//"HADDX1 U1_1_16_seven ( .A0(In6_counter[16]), .B0(carry_seven[16]), .C1(carry_seven[17]), .SO(N56) );\n" +
//"HADDX1 U1_1_17_seven ( .A0(In6_counter[17]), .B0(carry_seven[17]), .C1(carry_seven[18]), .SO(N57) );\n" +
//"HADDX1 U1_1_18_seven ( .A0(In6_counter[18]), .B0(carry_seven[18]), .C1(carry_seven[19]), .SO(N58) );\n" +
//"HADDX1 U1_1_22_seven ( .A0(In6_counter[22]), .B0(carry_seven[22]), .C1(carry_seven[23]), .SO(N62) );\n" +
//"HADDX1 U1_1_23_seven ( .A0(In6_counter[23]), .B0(carry_seven[23]), .C1(carry_seven[24]), .SO(N63) );\n" +
//"HADDX1 U1_1_24_seven ( .A0(In6_counter[24]), .B0(carry_seven[24]), .C1(carry_seven[25]), .SO(N64) );\n" +
//"HADDX1 U1_1_28_seven ( .A0(In6_counter[28]), .B0(carry_seven[28]), .C1(carry_seven[29]), .SO(N68) );\n" +
//"HADDX1 U1_1_29_seven ( .A0(In6_counter[29]), .B0(carry_seven[29]), .C1(carry_seven[30]), .SO(N69) );\n" +
//"INVX0 U1_seven ( .IN(In6_counter[0]), .QN(N40) );\n" +
//"XOR2X1 U2_seven ( .IN1(carry_seven[31]), .IN2(In6_counter[31]), .Q(N71) );\n" +
//"\n" +
//"DFFARX1 Clock_counter_reg_31_ ( .D(N351), .CLK(CK), .RSTB(n761), .Q(Clock_counter[31]), .QN(n759) );\n" +
//"DFFARX1 In5_counter_reg_31_ ( .D(N106), .CLK(g26888), .RSTB(n761), .Q(In5_counter[31]), .QN(n610) );\n" +
//"DFFARX1 In7_counter_reg_31_ ( .D(N36), .CLK(g34619), .RSTB(n761), .Q(In7_counter[31]), .QN(n594) );\n" +
//"DFFARX1 In0_counter_reg_31_ ( .D(N281), .CLK(g26963), .RSTB(n761), .Q(In0_counter[31]), .QN(n578) );\n" +
//"DFFARX1 In2_counter_reg_31_ ( .D(N211), .CLK(g25757), .RSTB(n761), .Q(In2_counter[31]), .QN(n562) );\n" +
//"DFFARX1 In4_counter_reg_31_ ( .D(N141), .CLK(g34628), .RSTB(n761), .Q(In4_counter[31]), .QN(n546) );\n" +
//"DFFARX1 In3_counter_reg_31_ ( .D(N176), .CLK(g34640), .RSTB(n761), .Q(In3_counter[31]), .QN(n545) );\n" +
//"DFFARX1 In1_counter_reg_31_ ( .D(N246), .CLK(g34629), .RSTB(n761), .Q(In1_counter[31]), .QN(n529) );\n" +
//"DFFARX1 In6_counter_reg_31_ ( .D(N71), .CLK(g34635), .RSTB(n761), .Q(In6_counter[31]), .QN(n513) );\n" +
//"DFFARX1 Clock_counter_reg_1_ ( .D(N321), .CLK(CK), .RSTB(n761), .Q(Clock_counter[1]), .QN(n758) );\n" +
//"DFFARX1 In5_counter_reg_2_ ( .D(N77), .CLK(g26888), .RSTB(n761), .Q(In5_counter[2]), .QN(n625) );\n" +
//"DFFARX1 In5_counter_reg_3_ ( .D(N78), .CLK(g26888), .RSTB(n761), .Q(In5_counter[3]), .QN(n624) );\n" +
//"DFFARX1 In5_counter_reg_8_ ( .D(N83), .CLK(g26888), .RSTB(n761), .Q(In5_counter[8]), .QN(n623) );\n" +
//"DFFARX1 In5_counter_reg_9_ ( .D(N84), .CLK(g26888), .RSTB(n761), .Q(In5_counter[9]), .QN(n622) );\n" +
//"DFFARX1 In5_counter_reg_10_ ( .D(N85), .CLK(g26888), .RSTB(n761), .Q(In5_counter[10]), .QN(n621) );\n" +
//"DFFARX1 In5_counter_reg_14_ ( .D(N89), .CLK(g26888), .RSTB(n761), .Q(In5_counter[14]), .QN(n620) );\n" +
//"DFFARX1 In5_counter_reg_15_ ( .D(N90), .CLK(g26888), .RSTB(n761), .Q(In5_counter[15]), .QN(n619) );\n" +
//"DFFARX1 In5_counter_reg_16_ ( .D(N91), .CLK(g26888), .RSTB(n761), .Q(In5_counter[16]), .QN(n618) );\n" +
//"DFFARX1 In5_counter_reg_17_ ( .D(N92), .CLK(g26888), .RSTB(n761), .Q(In5_counter[17]), .QN(n617) );\n" +
//"DFFARX1 In5_counter_reg_21_ ( .D(N96), .CLK(g26888), .RSTB(n761), .Q(In5_counter[21]), .QN(n616) );\n" +
//"DFFARX1 In5_counter_reg_22_ ( .D(N97), .CLK(g26888), .RSTB(n761), .Q(In5_counter[22]), .QN(n615) );\n" +
//"DFFARX1 In5_counter_reg_23_ ( .D(N98), .CLK(g26888), .RSTB(n761), .Q(In5_counter[23]), .QN(n614) );\n" +
//"DFFARX1 In5_counter_reg_27_ ( .D(N102), .CLK(g26888), .RSTB(n761), .Q(In5_counter[27]), .QN(n613) );\n" +
//"DFFARX1 In5_counter_reg_28_ ( .D(N103), .CLK(g26888), .RSTB(n761), .Q(In5_counter[28]), .QN(n612) );\n" +
//"DFFARX1 In5_counter_reg_29_ ( .D(N104), .CLK(g26888), .RSTB(n761), .Q(In5_counter[29]), .QN(n611) );\n" +
//"DFFARX1 In7_counter_reg_2_ ( .D(N7), .CLK(g34619), .RSTB(n761), .Q(In7_counter[2]), .QN(n609) );\n" +
//"DFFARX1 In7_counter_reg_3_ ( .D(N8), .CLK(g34619), .RSTB(n761), .Q(In7_counter[3]), .QN(n608) );\n" +
//"DFFARX1 In7_counter_reg_8_ ( .D(N13), .CLK(g34619), .RSTB(n761), .Q(In7_counter[8]), .QN(n607) );\n" +
//"DFFARX1 In7_counter_reg_9_ ( .D(N14), .CLK(g34619), .RSTB(n761), .Q(In7_counter[9]), .QN(n606) );\n" +
//"DFFARX1 In7_counter_reg_10_ ( .D(N15), .CLK(g34619), .RSTB(n761), .Q(In7_counter[10]), .QN(n605) );\n" +
//"DFFARX1 In7_counter_reg_14_ ( .D(N19), .CLK(g34619), .RSTB(n761), .Q(In7_counter[14]), .QN(n604) );\n" +
//"DFFARX1 In7_counter_reg_15_ ( .D(N20), .CLK(g34619), .RSTB(n761), .Q(In7_counter[15]), .QN(n603) );\n" +
//"DFFARX1 In7_counter_reg_16_ ( .D(N21), .CLK(g34619), .RSTB(n761), .Q(In7_counter[16]), .QN(n602) );\n" +
//"DFFARX1 In7_counter_reg_17_ ( .D(N22), .CLK(g34619), .RSTB(n761), .Q(In7_counter[17]), .QN(n601) );\n" +
//"DFFARX1 In7_counter_reg_21_ ( .D(N26), .CLK(g34619), .RSTB(n761), .Q(In7_counter[21]), .QN(n600) );\n" +
//"DFFARX1 In7_counter_reg_22_ ( .D(N27), .CLK(g34619), .RSTB(n761), .Q(In7_counter[22]), .QN(n599) );\n" +
//"DFFARX1 In7_counter_reg_23_ ( .D(N28), .CLK(g34619), .RSTB(n761), .Q(In7_counter[23]), .QN(n598) );\n" +
//"DFFARX1 In7_counter_reg_27_ ( .D(N32), .CLK(g34619), .RSTB(n761), .Q(In7_counter[27]), .QN(n597) );\n" +
//"DFFARX1 In7_counter_reg_28_ ( .D(N33), .CLK(g34619), .RSTB(n761), .Q(In7_counter[28]), .QN(n596) );\n" +
//"DFFARX1 In7_counter_reg_29_ ( .D(N34), .CLK(g34619), .RSTB(n761), .Q(In7_counter[29]), .QN(n595) );\n" +
//"DFFARX1 In0_counter_reg_2_ ( .D(N252), .CLK(g26963), .RSTB(n761), .Q(In0_counter[2]), .QN(n593) );\n" +
//"DFFARX1 In0_counter_reg_3_ ( .D(N253), .CLK(g26963), .RSTB(n761), .Q(In0_counter[3]), .QN(n592) );\n" +
//"DFFARX1 In0_counter_reg_8_ ( .D(N258), .CLK(g26963), .RSTB(n761), .Q(In0_counter[8]), .QN(n591) );\n" +
//"DFFARX1 In0_counter_reg_9_ ( .D(N259), .CLK(g26963), .RSTB(n761), .Q(In0_counter[9]), .QN(n590) );\n" +
//"DFFARX1 In0_counter_reg_10_ ( .D(N260), .CLK(g26963), .RSTB(n761), .Q(In0_counter[10]), .QN(n589) );\n" +
//"DFFARX1 In0_counter_reg_14_ ( .D(N264), .CLK(g26963), .RSTB(n761), .Q(In0_counter[14]), .QN(n588) );\n" +
//"DFFARX1 In0_counter_reg_15_ ( .D(N265), .CLK(g26963), .RSTB(n761), .Q(In0_counter[15]), .QN(n587) );\n" +
//"DFFARX1 In0_counter_reg_16_ ( .D(N266), .CLK(g26963), .RSTB(n761), .Q(In0_counter[16]), .QN(n586) );\n" +
//"DFFARX1 In0_counter_reg_17_ ( .D(N267), .CLK(g26963), .RSTB(n761), .Q(In0_counter[17]), .QN(n585) );\n" +
//"DFFARX1 In0_counter_reg_21_ ( .D(N271), .CLK(g26963), .RSTB(n761), .Q(In0_counter[21]), .QN(n584) );\n" +
//"DFFARX1 In0_counter_reg_22_ ( .D(N272), .CLK(g26963), .RSTB(n761), .Q(In0_counter[22]), .QN(n583) );\n" +
//"DFFARX1 In0_counter_reg_23_ ( .D(N273), .CLK(g26963), .RSTB(n761), .Q(In0_counter[23]), .QN(n582) );\n" +
//"DFFARX1 In0_counter_reg_27_ ( .D(N277), .CLK(g26963), .RSTB(n761), .Q(In0_counter[27]), .QN(n581) );\n" +
//"DFFARX1 In0_counter_reg_28_ ( .D(N278), .CLK(g26963), .RSTB(n761), .Q(In0_counter[28]), .QN(n580) );\n" +
//"DFFARX1 In0_counter_reg_29_ ( .D(N279), .CLK(g26963), .RSTB(n761), .Q(In0_counter[29]), .QN(n579) );\n" +
//"DFFARX1 In2_counter_reg_2_ ( .D(N182), .CLK(g25757), .RSTB(n761), .Q(In2_counter[2]), .QN(n577) );\n" +
//"DFFARX1 In2_counter_reg_3_ ( .D(N183), .CLK(g25757), .RSTB(n761), .Q(In2_counter[3]), .QN(n576) );\n" +
//"DFFARX1 In2_counter_reg_8_ ( .D(N188), .CLK(g25757), .RSTB(n761), .Q(In2_counter[8]), .QN(n575) );\n" +
//"DFFARX1 In2_counter_reg_9_ ( .D(N189), .CLK(g25757), .RSTB(n761), .Q(In2_counter[9]), .QN(n574) );\n" +
//"DFFARX1 In2_counter_reg_10_ ( .D(N190), .CLK(g25757), .RSTB(n761), .Q(In2_counter[10]), .QN(n573) );\n" +
//"DFFARX1 In2_counter_reg_14_ ( .D(N194), .CLK(g25757), .RSTB(n761), .Q(In2_counter[14]), .QN(n572) );\n" +
//"DFFARX1 In2_counter_reg_15_ ( .D(N195), .CLK(g25757), .RSTB(n761), .Q(In2_counter[15]), .QN(n571) );\n" +
//"DFFARX1 In2_counter_reg_16_ ( .D(N196), .CLK(g25757), .RSTB(n761), .Q(In2_counter[16]), .QN(n570) );\n" +
//"DFFARX1 In2_counter_reg_17_ ( .D(N197), .CLK(g25757), .RSTB(n761), .Q(In2_counter[17]), .QN(n569) );\n" +
//"DFFARX1 In2_counter_reg_21_ ( .D(N201), .CLK(g25757), .RSTB(n761), .Q(In2_counter[21]), .QN(n568) );\n" +
//"DFFARX1 In2_counter_reg_22_ ( .D(N202), .CLK(g25757), .RSTB(n761), .Q(In2_counter[22]), .QN(n567) );\n" +
//"DFFARX1 In2_counter_reg_23_ ( .D(N203), .CLK(g25757), .RSTB(n761), .Q(In2_counter[23]), .QN(n566) );\n" +
//"DFFARX1 In2_counter_reg_27_ ( .D(N207), .CLK(g25757), .RSTB(n761), .Q(In2_counter[27]), .QN(n565) );\n" +
//"DFFARX1 In2_counter_reg_28_ ( .D(N208), .CLK(g25757), .RSTB(n761), .Q(In2_counter[28]), .QN(n564) );\n" +
//"DFFARX1 In2_counter_reg_29_ ( .D(N209), .CLK(g25757), .RSTB(n761), .Q(In2_counter[29]), .QN(n563) );\n" +
//"DFFARX1 In4_counter_reg_2_ ( .D(N112), .CLK(g34628), .RSTB(n761), .Q(In4_counter[2]), .QN(n561) );\n" +
//"DFFARX1 In4_counter_reg_3_ ( .D(N113), .CLK(g34628), .RSTB(n761), .Q(In4_counter[3]), .QN(n560) );\n" +
//"DFFARX1 In4_counter_reg_8_ ( .D(N118), .CLK(g34628), .RSTB(n761), .Q(In4_counter[8]), .QN(n559) );\n" +
//"DFFARX1 In4_counter_reg_9_ ( .D(N119), .CLK(g34628), .RSTB(n761), .Q(In4_counter[9]), .QN(n558) );\n" +
//"DFFARX1 In4_counter_reg_10_ ( .D(N120), .CLK(g34628), .RSTB(n761), .Q(In4_counter[10]), .QN(n557) );\n" +
//"DFFARX1 In4_counter_reg_14_ ( .D(N124), .CLK(g34628), .RSTB(n761), .Q(In4_counter[14]), .QN(n556) );\n" +
//"DFFARX1 In4_counter_reg_15_ ( .D(N125), .CLK(g34628), .RSTB(n761), .Q(In4_counter[15]), .QN(n555) );\n" +
//"DFFARX1 In4_counter_reg_16_ ( .D(N126), .CLK(g34628), .RSTB(n761), .Q(In4_counter[16]), .QN(n554) );\n" +
//"DFFARX1 In4_counter_reg_17_ ( .D(N127), .CLK(g34628), .RSTB(n761), .Q(In4_counter[17]), .QN(n553) );\n" +
//"DFFARX1 In4_counter_reg_21_ ( .D(N131), .CLK(g34628), .RSTB(n761), .Q(In4_counter[21]), .QN(n552) );\n" +
//"DFFARX1 In4_counter_reg_22_ ( .D(N132), .CLK(g34628), .RSTB(n761), .Q(In4_counter[22]), .QN(n551) );\n" +
//"DFFARX1 In4_counter_reg_23_ ( .D(N133), .CLK(g34628), .RSTB(n761), .Q(In4_counter[23]), .QN(n550) );\n" +
//"DFFARX1 In4_counter_reg_27_ ( .D(N137), .CLK(g34628), .RSTB(n761), .Q(In4_counter[27]), .QN(n549) );\n" +
//"DFFARX1 In4_counter_reg_28_ ( .D(N138), .CLK(g34628), .RSTB(n761), .Q(In4_counter[28]), .QN(n548) );\n" +
//"DFFARX1 In4_counter_reg_29_ ( .D(N139), .CLK(g34628), .RSTB(n761), .Q(In4_counter[29]), .QN(n547) );\n" +
//"DFFARX1 In3_counter_reg_2_ ( .D(N147), .CLK(g34640), .RSTB(n761), .Q(In3_counter[2]), .QN(n544) );\n" +
//"DFFARX1 In3_counter_reg_3_ ( .D(N148), .CLK(g34640), .RSTB(n761), .Q(In3_counter[3]), .QN(n543) );\n" +
//"DFFARX1 In3_counter_reg_9_ ( .D(N154), .CLK(g34640), .RSTB(n761), .Q(In3_counter[9]), .QN(n542) );\n" +
//"DFFARX1 In3_counter_reg_10_ ( .D(N155), .CLK(g34640), .RSTB(n761), .Q(In3_counter[10]), .QN(n541) );\n" +
//"DFFARX1 In3_counter_reg_11_ ( .D(N156), .CLK(g34640), .RSTB(n761), .Q(In3_counter[11]), .QN(n540) );\n" +
//"DFFARX1 In3_counter_reg_15_ ( .D(N160), .CLK(g34640), .RSTB(n761), .Q(In3_counter[15]), .QN(n539) );\n" +
//"DFFARX1 In3_counter_reg_16_ ( .D(N161), .CLK(g34640), .RSTB(n761), .Q(In3_counter[16]), .QN(n538) );\n" +
//"DFFARX1 In3_counter_reg_17_ ( .D(N162), .CLK(g34640), .RSTB(n761), .Q(In3_counter[17]), .QN(n537) );\n" +
//"DFFARX1 In3_counter_reg_18_ ( .D(N163), .CLK(g34640), .RSTB(n761), .Q(In3_counter[18]), .QN(n536) );\n" +
//"DFFARX1 In3_counter_reg_22_ ( .D(N167), .CLK(g34640), .RSTB(n761), .Q(In3_counter[22]), .QN(n535) );\n" +
//"DFFARX1 In3_counter_reg_23_ ( .D(N168), .CLK(g34640), .RSTB(n761), .Q(In3_counter[23]), .QN(n534) );\n" +
//"DFFARX1 In3_counter_reg_24_ ( .D(N169), .CLK(g34640), .RSTB(n761), .Q(In3_counter[24]), .QN(n533) );\n" +
//"DFFARX1 In3_counter_reg_28_ ( .D(N173), .CLK(g34640), .RSTB(n761), .Q(In3_counter[28]), .QN(n532) );\n" +
//"DFFARX1 In3_counter_reg_29_ ( .D(N174), .CLK(g34640), .RSTB(n761), .Q(In3_counter[29]), .QN(n531) );\n" +
//"DFFARX1 In3_counter_reg_30_ ( .D(N175), .CLK(g34640), .RSTB(n761), .Q(In3_counter[30]), .QN(n530) );\n" +
//"DFFARX1 In1_counter_reg_2_ ( .D(N217), .CLK(g34629), .RSTB(n761), .Q(In1_counter[2]), .QN(n528) );\n" +
//"DFFARX1 In1_counter_reg_3_ ( .D(N218), .CLK(g34629), .RSTB(n761), .Q(In1_counter[3]), .QN(n527) );\n" +
//"DFFARX1 In1_counter_reg_9_ ( .D(N224), .CLK(g34629), .RSTB(n761), .Q(In1_counter[9]), .QN(n526) );\n" +
//"DFFARX1 In1_counter_reg_10_ ( .D(N225), .CLK(g34629), .RSTB(n761), .Q(In1_counter[10]), .QN(n525) );\n" +
//"DFFARX1 In1_counter_reg_11_ ( .D(N226), .CLK(g34629), .RSTB(n761), .Q(In1_counter[11]), .QN(n524) );\n" +
//"DFFARX1 In1_counter_reg_15_ ( .D(N230), .CLK(g34629), .RSTB(n761), .Q(In1_counter[15]), .QN(n523) );\n" +
//"DFFARX1 In1_counter_reg_16_ ( .D(N231), .CLK(g34629), .RSTB(n761), .Q(In1_counter[16]), .QN(n522) );\n" +
//"DFFARX1 In1_counter_reg_17_ ( .D(N232), .CLK(g34629), .RSTB(n761), .Q(In1_counter[17]), .QN(n521) );\n" +
//"DFFARX1 In1_counter_reg_18_ ( .D(N233), .CLK(g34629), .RSTB(n761), .Q(In1_counter[18]), .QN(n520) );\n" +
//"DFFARX1 In1_counter_reg_22_ ( .D(N237), .CLK(g34629), .RSTB(n761), .Q(In1_counter[22]), .QN(n519) );\n" +
//"DFFARX1 In1_counter_reg_23_ ( .D(N238), .CLK(g34629), .RSTB(n761), .Q(In1_counter[23]), .QN(n518) );\n" +
//"DFFARX1 In1_counter_reg_24_ ( .D(N239), .CLK(g34629), .RSTB(n761), .Q(In1_counter[24]), .QN(n517) );\n" +
//"DFFARX1 In1_counter_reg_28_ ( .D(N243), .CLK(g34629), .RSTB(n761), .Q(In1_counter[28]), .QN(n516) );\n" +
//"DFFARX1 In1_counter_reg_29_ ( .D(N244), .CLK(g34629), .RSTB(n761), .Q(In1_counter[29]), .QN(n515) );\n" +
//"DFFARX1 In1_counter_reg_30_ ( .D(N245), .CLK(g34629), .RSTB(n761), .Q(In1_counter[30]), .QN(n514) );\n" +
//"DFFARX1 Clock_counter_reg_27_ ( .D(N347), .CLK(CK), .RSTB(n761), .Q(Clock_counter[27]), .QN(n757) );\n" +
//"DFFARX1 Clock_counter_reg_26_ ( .D(N346), .CLK(CK), .RSTB(n761), .Q(Clock_counter[26]), .QN(n756) );\n" +
//"DFFARX1 Clock_counter_reg_25_ ( .D(N345), .CLK(CK), .RSTB(n761), .Q(Clock_counter[25]), .QN(n755) );\n" +
//"DFFARX1 Clock_counter_reg_21_ ( .D(N341), .CLK(CK), .RSTB(n761), .Q(Clock_counter[21]), .QN(n754) );\n" +
//"DFFARX1 Clock_counter_reg_20_ ( .D(N340), .CLK(CK), .RSTB(n761), .Q(Clock_counter[20]), .QN(n753) );\n" +
//"DFFARX1 Clock_counter_reg_19_ ( .D(N339), .CLK(CK), .RSTB(n761), .Q(Clock_counter[19]), .QN(n752) );\n" +
//"DFFARX1 Clock_counter_reg_15_ ( .D(N335), .CLK(CK), .RSTB(n761), .Q(Clock_counter[15]), .QN(n751) );\n" +
//"DFFARX1 Clock_counter_reg_14_ ( .D(N334), .CLK(CK), .RSTB(n761), .Q(Clock_counter[14]), .QN(n750) );\n" +
//"DFFARX1 Clock_counter_reg_13_ ( .D(N333), .CLK(CK), .RSTB(n761), .Q(Clock_counter[13]), .QN(n749) );\n" +
//"DFFARX1 Clock_counter_reg_9_ ( .D(N329), .CLK(CK), .RSTB(n761), .Q(Clock_counter[9]), .QN(n748) );\n" +
//"DFFARX1 Clock_counter_reg_8_ ( .D(N328), .CLK(CK), .RSTB(n761), .Q(Clock_counter[8]), .QN(n747) );\n" +
//"DFFARX1 Clock_counter_reg_7_ ( .D(N327), .CLK(CK), .RSTB(n761), .Q(Clock_counter[7]), .QN(n746) );\n" +
//"DFFARX1 Clock_counter_reg_3_ ( .D(N323), .CLK(CK), .RSTB(n761), .Q(Clock_counter[3]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_2_ ( .D(N322), .CLK(CK), .RSTB(n761), .Q(Clock_counter[2]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_2_ ( .D(N42), .CLK(g34635), .RSTB(n761), .Q(In6_counter[2]), .QN(n512) );\n" +
//"DFFARX1 In6_counter_reg_3_ ( .D(N43), .CLK(g34635), .RSTB(n761), .Q(In6_counter[3]), .QN(n511) );\n" +
//"DFFARX1 In6_counter_reg_9_ ( .D(N49), .CLK(g34635), .RSTB(n761), .Q(In6_counter[9]), .QN(n510) );\n" +
//"DFFARX1 In6_counter_reg_10_ ( .D(N50), .CLK(g34635), .RSTB(n761), .Q(In6_counter[10]), .QN(n509) );\n" +
//"DFFARX1 In6_counter_reg_11_ ( .D(N51), .CLK(g34635), .RSTB(n761), .Q(In6_counter[11]), .QN(n508) );\n" +
//"DFFARX1 In6_counter_reg_15_ ( .D(N55), .CLK(g34635), .RSTB(n761), .Q(In6_counter[15]), .QN(n507) );\n" +
//"DFFARX1 In6_counter_reg_16_ ( .D(N56), .CLK(g34635), .RSTB(n761), .Q(In6_counter[16]), .QN(n506) );\n" +
//"DFFARX1 In6_counter_reg_17_ ( .D(N57), .CLK(g34635), .RSTB(n761), .Q(In6_counter[17]), .QN(n505) );\n" +
//"DFFARX1 In6_counter_reg_18_ ( .D(N58), .CLK(g34635), .RSTB(n761), .Q(In6_counter[18]), .QN(n504) );\n" +
//"DFFARX1 In6_counter_reg_22_ ( .D(N62), .CLK(g34635), .RSTB(n761), .Q(In6_counter[22]), .QN(n503) );\n" +
//"DFFARX1 In6_counter_reg_23_ ( .D(N63), .CLK(g34635), .RSTB(n761), .Q(In6_counter[23]), .QN(n502) );\n" +
//"DFFARX1 In6_counter_reg_24_ ( .D(N64), .CLK(g34635), .RSTB(n761), .Q(In6_counter[24]), .QN(n501) );\n" +
//"DFFARX1 In6_counter_reg_28_ ( .D(N68), .CLK(g34635), .RSTB(n761), .Q(In6_counter[28]), .QN(n500) );\n" +
//"DFFARX1 In6_counter_reg_29_ ( .D(N69), .CLK(g34635), .RSTB(n761), .Q(In6_counter[29]), .QN(n499) );\n" +
//"DFFARX1 In6_counter_reg_30_ ( .D(N70), .CLK(g34635), .RSTB(n761), .Q(In6_counter[30]), .QN(n498) );\n" +
//"DFFARX1 Clock_counter_reg_4_ ( .D(N324), .CLK(CK), .RSTB(n761), .Q(Clock_counter[4]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_1_ ( .D(N76), .CLK(g26888), .RSTB(n761), .Q(In5_counter[1]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_1_ ( .D(N6), .CLK(g34619), .RSTB(n761), .Q(In7_counter[1]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_1_ ( .D(N251), .CLK(g26963), .RSTB(n761), .Q(In0_counter[1]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_1_ ( .D(N181), .CLK(g25757), .RSTB(n761), .Q(In2_counter[1]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_1_ ( .D(N111), .CLK(g34628), .RSTB(n761), .Q(In4_counter[1]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_1_ ( .D(N146), .CLK(g34640), .RSTB(n761), .Q(In3_counter[1]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_1_ ( .D(N216), .CLK(g34629), .RSTB(n761), .Q(In1_counter[1]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_1_ ( .D(N41), .CLK(g34635), .RSTB(n761), .Q(In6_counter[1]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_5_ ( .D(N325), .CLK(CK), .RSTB(n761), .Q(Clock_counter[5]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_6_ ( .D(N326), .CLK(CK), .RSTB(n761), .Q(Clock_counter[6]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_5_ ( .D(N80), .CLK(g26888), .RSTB(n761), .Q(In5_counter[5]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_5_ ( .D(N10), .CLK(g34619), .RSTB(n761), .Q(In7_counter[5]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_5_ ( .D(N255), .CLK(g26963), .RSTB(n761), .Q(In0_counter[5]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_5_ ( .D(N185), .CLK(g25757), .RSTB(n761), .Q(In2_counter[5]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_5_ ( .D(N115), .CLK(g34628), .RSTB(n761), .Q(In4_counter[5]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_5_ ( .D(N150), .CLK(g34640), .RSTB(n761), .Q(In3_counter[5]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_5_ ( .D(N220), .CLK(g34629), .RSTB(n761), .Q(In1_counter[5]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_5_ ( .D(N45), .CLK(g34635), .RSTB(n761), .Q(In6_counter[5]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_4_ ( .D(N79), .CLK(g26888), .RSTB(n761), .Q(In5_counter[4]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_4_ ( .D(N9), .CLK(g34619), .RSTB(n761), .Q(In7_counter[4]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_4_ ( .D(N254), .CLK(g26963), .RSTB(n761), .Q(In0_counter[4]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_4_ ( .D(N184), .CLK(g25757), .RSTB(n761), .Q(In2_counter[4]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_4_ ( .D(N114), .CLK(g34628), .RSTB(n761), .Q(In4_counter[4]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_4_ ( .D(N149), .CLK(g34640), .RSTB(n761), .Q(In3_counter[4]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_4_ ( .D(N219), .CLK(g34629), .RSTB(n761), .Q(In1_counter[4]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_4_ ( .D(N44), .CLK(g34635), .RSTB(n761), .Q(In6_counter[4]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_30_ ( .D(N105), .CLK(g26888), .RSTB(n761), .Q(In5_counter[30]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_11_ ( .D(N86), .CLK(g26888), .RSTB(n761), .Q(In5_counter[11]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_18_ ( .D(N93), .CLK(g26888), .RSTB(n761), .Q(In5_counter[18]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_24_ ( .D(N99), .CLK(g26888), .RSTB(n761), .Q(In5_counter[24]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_30_ ( .D(N35), .CLK(g34619), .RSTB(n761), .Q(In7_counter[30]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_11_ ( .D(N16), .CLK(g34619), .RSTB(n761), .Q(In7_counter[11]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_18_ ( .D(N23), .CLK(g34619), .RSTB(n761), .Q(In7_counter[18]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_24_ ( .D(N29), .CLK(g34619), .RSTB(n761), .Q(In7_counter[24]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_30_ ( .D(N280), .CLK(g26963), .RSTB(n761), .Q(In0_counter[30]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_11_ ( .D(N261), .CLK(g26963), .RSTB(n761), .Q(In0_counter[11]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_18_ ( .D(N268), .CLK(g26963), .RSTB(n761), .Q(In0_counter[18]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_24_ ( .D(N274), .CLK(g26963), .RSTB(n761), .Q(In0_counter[24]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_30_ ( .D(N210), .CLK(g25757), .RSTB(n761), .Q(In2_counter[30]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_11_ ( .D(N191), .CLK(g25757), .RSTB(n761), .Q(In2_counter[11]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_18_ ( .D(N198), .CLK(g25757), .RSTB(n761), .Q(In2_counter[18]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_24_ ( .D(N204), .CLK(g25757), .RSTB(n761), .Q(In2_counter[24]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_30_ ( .D(N140), .CLK(g34628), .RSTB(n761), .Q(In4_counter[30]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_11_ ( .D(N121), .CLK(g34628), .RSTB(n761), .Q(In4_counter[11]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_18_ ( .D(N128), .CLK(g34628), .RSTB(n761), .Q(In4_counter[18]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_24_ ( .D(N134), .CLK(g34628), .RSTB(n761), .Q(In4_counter[24]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_6_ ( .D(N151), .CLK(g34640), .RSTB(n761), .Q(In3_counter[6]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_12_ ( .D(N157), .CLK(g34640), .RSTB(n761), .Q(In3_counter[12]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_19_ ( .D(N164), .CLK(g34640), .RSTB(n761), .Q(In3_counter[19]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_25_ ( .D(N170), .CLK(g34640), .RSTB(n761), .Q(In3_counter[25]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_6_ ( .D(N221), .CLK(g34629), .RSTB(n761), .Q(In1_counter[6]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_12_ ( .D(N227), .CLK(g34629), .RSTB(n761), .Q(In1_counter[12]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_19_ ( .D(N234), .CLK(g34629), .RSTB(n761), .Q(In1_counter[19]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_25_ ( .D(N240), .CLK(g34629), .RSTB(n761), .Q(In1_counter[25]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_30_ ( .D(N350), .CLK(CK), .RSTB(n761), .Q(Clock_counter[30]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_24_ ( .D(N344), .CLK(CK), .RSTB(n761), .Q(Clock_counter[24]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_18_ ( .D(N338), .CLK(CK), .RSTB(n761), .Q(Clock_counter[18]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_12_ ( .D(N332), .CLK(CK), .RSTB(n761), .Q(Clock_counter[12]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_6_ ( .D(N46), .CLK(g34635), .RSTB(n761), .Q(In6_counter[6]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_12_ ( .D(N52), .CLK(g34635), .RSTB(n761), .Q(In6_counter[12]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_19_ ( .D(N59), .CLK(g34635), .RSTB(n761), .Q(In6_counter[19]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_25_ ( .D(N65), .CLK(g34635), .RSTB(n761), .Q(In6_counter[25]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_7_ ( .D(N82), .CLK(g26888), .RSTB(n761), .Q(In5_counter[7]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_13_ ( .D(N88), .CLK(g26888), .RSTB(n761), .Q(In5_counter[13]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_20_ ( .D(N95), .CLK(g26888), .RSTB(n761), .Q(In5_counter[20]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_26_ ( .D(N101), .CLK(g26888), .RSTB(n761), .Q(In5_counter[26]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_7_ ( .D(N12), .CLK(g34619), .RSTB(n761), .Q(In7_counter[7]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_13_ ( .D(N18), .CLK(g34619), .RSTB(n761), .Q(In7_counter[13]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_20_ ( .D(N25), .CLK(g34619), .RSTB(n761), .Q(In7_counter[20]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_26_ ( .D(N31), .CLK(g34619), .RSTB(n761), .Q(In7_counter[26]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_7_ ( .D(N257), .CLK(g26963), .RSTB(n761), .Q(In0_counter[7]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_13_ ( .D(N263), .CLK(g26963), .RSTB(n761), .Q(In0_counter[13]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_20_ ( .D(N270), .CLK(g26963), .RSTB(n761), .Q(In0_counter[20]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_26_ ( .D(N276), .CLK(g26963), .RSTB(n761), .Q(In0_counter[26]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_7_ ( .D(N187), .CLK(g25757), .RSTB(n761), .Q(In2_counter[7]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_13_ ( .D(N193), .CLK(g25757), .RSTB(n761), .Q(In2_counter[13]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_20_ ( .D(N200), .CLK(g25757), .RSTB(n761), .Q(In2_counter[20]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_26_ ( .D(N206), .CLK(g25757), .RSTB(n761), .Q(In2_counter[26]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_7_ ( .D(N117), .CLK(g34628), .RSTB(n761), .Q(In4_counter[7]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_13_ ( .D(N123), .CLK(g34628), .RSTB(n761), .Q(In4_counter[13]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_20_ ( .D(N130), .CLK(g34628), .RSTB(n761), .Q(In4_counter[20]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_26_ ( .D(N136), .CLK(g34628), .RSTB(n761), .Q(In4_counter[26]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_8_ ( .D(N153), .CLK(g34640), .RSTB(n761), .Q(In3_counter[8]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_14_ ( .D(N159), .CLK(g34640), .RSTB(n761), .Q(In3_counter[14]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_21_ ( .D(N166), .CLK(g34640), .RSTB(n761), .Q(In3_counter[21]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_27_ ( .D(N172), .CLK(g34640), .RSTB(n761), .Q(In3_counter[27]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_8_ ( .D(N223), .CLK(g34629), .RSTB(n761), .Q(In1_counter[8]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_14_ ( .D(N229), .CLK(g34629), .RSTB(n761), .Q(In1_counter[14]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_21_ ( .D(N236), .CLK(g34629), .RSTB(n761), .Q(In1_counter[21]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_27_ ( .D(N242), .CLK(g34629), .RSTB(n761), .Q(In1_counter[27]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_28_ ( .D(N348), .CLK(CK), .RSTB(n761), .Q(Clock_counter[28]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_22_ ( .D(N342), .CLK(CK), .RSTB(n761), .Q(Clock_counter[22]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_16_ ( .D(N336), .CLK(CK), .RSTB(n761), .Q(Clock_counter[16]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_10_ ( .D(N330), .CLK(CK), .RSTB(n761), .Q(Clock_counter[10]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_8_ ( .D(N48), .CLK(g34635), .RSTB(n761), .Q(In6_counter[8]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_14_ ( .D(N54), .CLK(g34635), .RSTB(n761), .Q(In6_counter[14]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_21_ ( .D(N61), .CLK(g34635), .RSTB(n761), .Q(In6_counter[21]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_27_ ( .D(N67), .CLK(g34635), .RSTB(n761), .Q(In6_counter[27]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_6_ ( .D(N81), .CLK(g26888), .RSTB(n761), .Q(In5_counter[6]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_12_ ( .D(N87), .CLK(g26888), .RSTB(n761), .Q(In5_counter[12]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_19_ ( .D(N94), .CLK(g26888), .RSTB(n761), .Q(In5_counter[19]), .QN() );\n" +
//"DFFARX1 In5_counter_reg_25_ ( .D(N100), .CLK(g26888), .RSTB(n761), .Q(In5_counter[25]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_6_ ( .D(N11), .CLK(g34619), .RSTB(n761), .Q(In7_counter[6]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_12_ ( .D(N17), .CLK(g34619), .RSTB(n761), .Q(In7_counter[12]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_19_ ( .D(N24), .CLK(g34619), .RSTB(n761), .Q(In7_counter[19]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_25_ ( .D(N30), .CLK(g34619), .RSTB(n761), .Q(In7_counter[25]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_6_ ( .D(N256), .CLK(g26963), .RSTB(n761), .Q(In0_counter[6]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_12_ ( .D(N262), .CLK(g26963), .RSTB(n761), .Q(In0_counter[12]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_19_ ( .D(N269), .CLK(g26963), .RSTB(n761), .Q(In0_counter[19]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_25_ ( .D(N275), .CLK(g26963), .RSTB(n761), .Q(In0_counter[25]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_6_ ( .D(N186), .CLK(g25757), .RSTB(n761), .Q(In2_counter[6]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_12_ ( .D(N192), .CLK(g25757), .RSTB(n761), .Q(In2_counter[12]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_19_ ( .D(N199), .CLK(g25757), .RSTB(n761), .Q(In2_counter[19]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_25_ ( .D(N205), .CLK(g25757), .RSTB(n761), .Q(In2_counter[25]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_6_ ( .D(N116), .CLK(g34628), .RSTB(n761), .Q(In4_counter[6]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_12_ ( .D(N122), .CLK(g34628), .RSTB(n761), .Q(In4_counter[12]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_19_ ( .D(N129), .CLK(g34628), .RSTB(n761), .Q(In4_counter[19]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_25_ ( .D(N135), .CLK(g34628), .RSTB(n761), .Q(In4_counter[25]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_7_ ( .D(N152), .CLK(g34640), .RSTB(n761), .Q(In3_counter[7]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_13_ ( .D(N158), .CLK(g34640), .RSTB(n761), .Q(In3_counter[13]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_20_ ( .D(N165), .CLK(g34640), .RSTB(n761), .Q(In3_counter[20]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_26_ ( .D(N171), .CLK(g34640), .RSTB(n761), .Q(In3_counter[26]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_7_ ( .D(N222), .CLK(g34629), .RSTB(n761), .Q(In1_counter[7]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_13_ ( .D(N228), .CLK(g34629), .RSTB(n761), .Q(In1_counter[13]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_20_ ( .D(N235), .CLK(g34629), .RSTB(n761), .Q(In1_counter[20]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_26_ ( .D(N241), .CLK(g34629), .RSTB(n761), .Q(In1_counter[26]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_29_ ( .D(N349), .CLK(CK), .RSTB(n761), .Q(Clock_counter[29]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_23_ ( .D(N343), .CLK(CK), .RSTB(n761), .Q(Clock_counter[23]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_17_ ( .D(N337), .CLK(CK), .RSTB(n761), .Q(Clock_counter[17]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_11_ ( .D(N331), .CLK(CK), .RSTB(n761), .Q(Clock_counter[11]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_7_ ( .D(N47), .CLK(g34635), .RSTB(n761), .Q(In6_counter[7]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_13_ ( .D(N53), .CLK(g34635), .RSTB(n761), .Q(In6_counter[13]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_20_ ( .D(N60), .CLK(g34635), .RSTB(n761), .Q(In6_counter[20]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_26_ ( .D(N66), .CLK(g34635), .RSTB(n761), .Q(In6_counter[26]), .QN() );\n" +
//"DFFARX1 Clock_counter_reg_0_ ( .D(N320), .CLK(CK), .RSTB(n761), .Q(Clock_counter[0]), .QN(n760) );\n" +
//"DFFARX1 In5_counter_reg_0_ ( .D(N75), .CLK(g26888), .RSTB(n761), .Q(In5_counter[0]), .QN() );\n" +
//"DFFARX1 In7_counter_reg_0_ ( .D(N5), .CLK(g34619), .RSTB(n761), .Q(In7_counter[0]), .QN() );\n" +
//"DFFARX1 In0_counter_reg_0_ ( .D(N250), .CLK(g26963), .RSTB(n761), .Q(In0_counter[0]), .QN() );\n" +
//"DFFARX1 In2_counter_reg_0_ ( .D(N180), .CLK(g25757), .RSTB(n761), .Q(In2_counter[0]), .QN() );\n" +
//"DFFARX1 In4_counter_reg_0_ ( .D(N110), .CLK(g34628), .RSTB(n761), .Q(In4_counter[0]), .QN() );\n" +
//"DFFARX1 In3_counter_reg_0_ ( .D(N145), .CLK(g34640), .RSTB(n761), .Q(In3_counter[0]), .QN() );\n" +
//"DFFARX1 In1_counter_reg_0_ ( .D(N215), .CLK(g34629), .RSTB(n761), .Q(In1_counter[0]), .QN() );\n" +
//"DFFARX1 In6_counter_reg_0_ ( .D(N40), .CLK(g34635), .RSTB(n761), .Q(In6_counter[0]), .QN() );\n" +
//"OA21X1 U64 ( .IN1(n626), .IN2(n627), .IN3(n628), .Q(n761) );\n" +
//"INVX0 U65 ( .IN(n629), .QN(n628) );\n" +
//"OR4X1 U66 ( .IN1(g34629), .IN2(g26963), .IN3(g34640), .IN4(g25757), .Q(n627) );\n" +
//"OR4X1 U67 ( .IN1(g26888), .IN2(g34628), .IN3(g34619), .IN4(g34635), .Q(n626) );\n" +
//"NOR4X0 U68 ( .IN1(n630), .IN2(n631), .IN3(n632), .IN4(n633), .QN(Tj_Trigger) );\n" +
//"NAND4X0 U69 ( .IN1(n610), .IN2(n594), .IN3(n578), .IN4(n562), .QN(n633) );\n" +
//"NAND4X0 U70 ( .IN1(n546), .IN2(n545), .IN3(n529), .IN4(n513), .QN(n632) );\n" +
//"NAND4X0 U71 ( .IN1(n634), .IN2(n635), .IN3(n636), .IN4(n637), .QN(n631) );\n" +
//"NAND4X0 U72 ( .IN1(n638), .IN2(n639), .IN3(n640), .IN4(n641), .QN(n637) );\n" +
//"NOR4X0 U73 ( .IN1(n642), .IN2(In0_counter[24]), .IN3(In0_counter[26]), .IN4(In0_counter[25]), .QN(n641) );\n" +
//"NAND4X0 U74 ( .IN1(n581), .IN2(n580), .IN3(n579), .IN4(n643), .QN(n642) );\n" +
//"NAND3X0 U75 ( .IN1(In0_counter[5]), .IN2(In0_counter[4]), .IN3(n644), .QN(n643) );\n" +
//"NAND3X0 U76 ( .IN1(n592), .IN2(n645), .IN3(n593), .QN(n644) );\n" +
//"NAND2X0 U77 ( .IN1(In0_counter[1]), .IN2(In0_counter[0]), .QN(n645) );\n" +
//"NOR4X0 U78 ( .IN1(n646), .IN2(In0_counter[18]), .IN3(In0_counter[20]), .IN4(In0_counter[19]), .QN(n640) );\n" +
//"NAND3X0 U79 ( .IN1(n583), .IN2(n582), .IN3(n584), .QN(n646) );\n" +
//"NOR4X0 U80 ( .IN1(n647), .IN2(In0_counter[11]), .IN3(In0_counter[13]), .IN4(In0_counter[12]), .QN(n639) );\n" +
//"NAND4X0 U81 ( .IN1(n588), .IN2(n587), .IN3(n586), .IN4(n585), .QN(n647) );\n" +
//"NOR4X0 U82 ( .IN1(n648), .IN2(In0_counter[30]), .IN3(In0_counter[7]), .IN4(In0_counter[6]), .QN(n638) );\n" +
//"NAND3X0 U83 ( .IN1(n590), .IN2(n589), .IN3(n591), .QN(n648) );\n" +
//"NAND4X0 U84 ( .IN1(n649), .IN2(n650), .IN3(n651), .IN4(n652), .QN(n636) );\n" +
//"NOR4X0 U85 ( .IN1(n653), .IN2(In2_counter[24]), .IN3(In2_counter[26]), .IN4(In2_counter[25]), .QN(n652) );\n" +
//"NAND4X0 U86 ( .IN1(n565), .IN2(n564), .IN3(n563), .IN4(n654), .QN(n653) );\n" +
//"NAND3X0 U87 ( .IN1(In2_counter[5]), .IN2(In2_counter[4]), .IN3(n655), .QN(n654) );\n" +
//"NAND3X0 U88 ( .IN1(n576), .IN2(n656), .IN3(n577), .QN(n655) );\n" +
//"NAND2X0 U89 ( .IN1(In2_counter[1]), .IN2(In2_counter[0]), .QN(n656) );\n" +
//"NOR4X0 U90 ( .IN1(n657), .IN2(In2_counter[18]), .IN3(In2_counter[20]), .IN4(In2_counter[19]), .QN(n651) );\n" +
//"NAND3X0 U91 ( .IN1(n567), .IN2(n566), .IN3(n568), .QN(n657) );\n" +
//"NOR4X0 U92 ( .IN1(n658), .IN2(In2_counter[11]), .IN3(In2_counter[13]), .IN4(In2_counter[12]), .QN(n650) );\n" +
//"NAND4X0 U93 ( .IN1(n572), .IN2(n571), .IN3(n570), .IN4(n569), .QN(n658) );\n" +
//"NOR4X0 U94 ( .IN1(n659), .IN2(In2_counter[30]), .IN3(In2_counter[7]), .IN4(In2_counter[6]), .QN(n649) );\n" +
//"NAND3X0 U95 ( .IN1(n574), .IN2(n573), .IN3(n575), .QN(n659) );\n" +
//"NAND4X0 U96 ( .IN1(n660), .IN2(n661), .IN3(n662), .IN4(n663), .QN(n635) );\n" +
//"NOR4X0 U97 ( .IN1(n664), .IN2(In3_counter[25]), .IN3(In3_counter[27]), .IN4(In3_counter[26]), .QN(n663) );\n" +
//"NAND4X0 U98 ( .IN1(n532), .IN2(n531), .IN3(n530), .IN4(n665), .QN(n664) );\n" +
//"NAND3X0 U99 ( .IN1(In3_counter[5]), .IN2(In3_counter[4]), .IN3(n666), .QN(n665) );\n" +
//"NAND3X0 U100 ( .IN1(n543), .IN2(n667), .IN3(n544), .QN(n666) );\n" +
//"NAND2X0 U101 ( .IN1(In3_counter[1]), .IN2(In3_counter[0]), .QN(n667) );\n" +
//"NOR4X0 U102 ( .IN1(n668), .IN2(In3_counter[19]), .IN3(In3_counter[21]), .IN4(In3_counter[20]), .QN(n662) );\n" +
//"NAND3X0 U103 ( .IN1(n534), .IN2(n533), .IN3(n535), .QN(n668) );\n" +
//"NOR4X0 U104 ( .IN1(n669), .IN2(In3_counter[12]), .IN3(In3_counter[14]), .IN4(In3_counter[13]), .QN(n661) );\n" +
//"NAND4X0 U105 ( .IN1(n539), .IN2(n538), .IN3(n537), .IN4(n536), .QN(n669) );\n" +
//"NOR4X0 U106 ( .IN1(n670), .IN2(In3_counter[6]), .IN3(In3_counter[8]), .IN4(In3_counter[7]), .QN(n660) );\n" +
//"NAND3X0 U107 ( .IN1(n541), .IN2(n540), .IN3(n542), .QN(n670) );\n" +
//"OAI21X1 U108 ( .IN1(n671), .IN2(n672), .IN3(n759), .QN(n634) );\n" +
//"OA21X1 U109 ( .IN1(Clock_counter[2]), .IN2(Clock_counter[3]), .IN3(n673), .Q(n671) );\n" +
//"NAND3X0 U110 ( .IN1(n674), .IN2(n675), .IN3(n676), .QN(n630) );\n" +
//"AND3X1 U111 ( .IN1(n677), .IN2(n678), .IN3(n679), .Q(n676) );\n" +
//"NAND4X0 U112 ( .IN1(n680), .IN2(n681), .IN3(n682), .IN4(n683), .QN(n679) );\n" +
//"NOR4X0 U113 ( .IN1(n684), .IN2(In5_counter[24]), .IN3(In5_counter[26]), .IN4(In5_counter[25]), .QN(n683) );\n" +
//"NAND4X0 U114 ( .IN1(n613), .IN2(n612), .IN3(n611), .IN4(n685), .QN(n684) );\n" +
//"NAND3X0 U115 ( .IN1(In5_counter[5]), .IN2(In5_counter[4]), .IN3(n686), .QN(n685) );\n" +
//"NAND3X0 U116 ( .IN1(n624), .IN2(n687), .IN3(n625), .QN(n686) );\n" +
//"NAND2X0 U117 ( .IN1(In5_counter[1]), .IN2(In5_counter[0]), .QN(n687) );\n" +
//"NOR4X0 U118 ( .IN1(n688), .IN2(In5_counter[18]), .IN3(In5_counter[20]), .IN4(In5_counter[19]), .QN(n682) );\n" +
//"NAND3X0 U119 ( .IN1(n615), .IN2(n614), .IN3(n616), .QN(n688) );\n" +
//"NOR4X0 U120 ( .IN1(n689), .IN2(In5_counter[11]), .IN3(In5_counter[13]), .IN4(In5_counter[12]), .QN(n681) );\n" +
//"NAND4X0 U121 ( .IN1(n620), .IN2(n619), .IN3(n618), .IN4(n617), .QN(n689) );\n" +
//"NOR4X0 U122 ( .IN1(n690), .IN2(In5_counter[30]), .IN3(In5_counter[7]), .IN4(In5_counter[6]), .QN(n680) );\n" +
//"NAND3X0 U123 ( .IN1(n622), .IN2(n621), .IN3(n623), .QN(n690) );\n" +
//"NAND4X0 U124 ( .IN1(n691), .IN2(n692), .IN3(n693), .IN4(n694), .QN(n678) );\n" +
//"NOR4X0 U125 ( .IN1(n695), .IN2(In7_counter[24]), .IN3(In7_counter[26]), .IN4(In7_counter[25]), .QN(n694) );\n" +
//"NAND4X0 U126 ( .IN1(n597), .IN2(n596), .IN3(n595), .IN4(n696), .QN(n695) );\n" +
//"NAND3X0 U127 ( .IN1(In7_counter[5]), .IN2(In7_counter[4]), .IN3(n697), .QN(n696) );\n" +
//"NAND3X0 U128 ( .IN1(n608), .IN2(n698), .IN3(n609), .QN(n697) );\n" +
//"NAND2X0 U129 ( .IN1(In7_counter[1]), .IN2(In7_counter[0]), .QN(n698) );\n" +
//"NOR4X0 U130 ( .IN1(n699), .IN2(In7_counter[18]), .IN3(In7_counter[20]), .IN4(In7_counter[19]), .QN(n693) );\n" +
//"NAND3X0 U131 ( .IN1(n599), .IN2(n598), .IN3(n600), .QN(n699) );\n" +
//"NOR4X0 U132 ( .IN1(n700), .IN2(In7_counter[11]), .IN3(In7_counter[13]), .IN4(In7_counter[12]), .QN(n692) );\n" +
//"NAND4X0 U133 ( .IN1(n604), .IN2(n603), .IN3(n602), .IN4(n601), .QN(n700) );\n" +
//"NOR4X0 U134 ( .IN1(n701), .IN2(In7_counter[30]), .IN3(In7_counter[7]), .IN4(In7_counter[6]), .QN(n691) );\n" +
//"NAND3X0 U135 ( .IN1(n606), .IN2(n605), .IN3(n607), .QN(n701) );\n" +
//"NAND4X0 U136 ( .IN1(n702), .IN2(n703), .IN3(n704), .IN4(n705), .QN(n677) );\n" +
//"NOR4X0 U137 ( .IN1(n706), .IN2(In6_counter[25]), .IN3(In6_counter[27]), .IN4(In6_counter[26]), .QN(n705) );\n" +
//"NAND4X0 U138 ( .IN1(n500), .IN2(n499), .IN3(n498), .IN4(n707), .QN(n706) );\n" +
//"NAND3X0 U139 ( .IN1(In6_counter[5]), .IN2(In6_counter[4]), .IN3(n708), .QN(n707) );\n" +
//"NAND3X0 U140 ( .IN1(n511), .IN2(n709), .IN3(n512), .QN(n708) );\n" +
//"NAND2X0 U141 ( .IN1(In6_counter[1]), .IN2(In6_counter[0]), .QN(n709) );\n" +
//"NOR4X0 U142 ( .IN1(n710), .IN2(In6_counter[19]), .IN3(In6_counter[21]), .IN4(In6_counter[20]), .QN(n704) );\n" +
//"NAND3X0 U143 ( .IN1(n502), .IN2(n501), .IN3(n503), .QN(n710) );\n" +
//"NOR4X0 U144 ( .IN1(n711), .IN2(In6_counter[12]), .IN3(In6_counter[14]), .IN4(In6_counter[13]), .QN(n703) );\n" +
//"NAND4X0 U145 ( .IN1(n507), .IN2(n506), .IN3(n505), .IN4(n504), .QN(n711) );\n" +
//"NOR4X0 U146 ( .IN1(n712), .IN2(In6_counter[6]), .IN3(In6_counter[8]), .IN4(In6_counter[7]), .QN(n702) );\n" +
//"NAND3X0 U147 ( .IN1(n509), .IN2(n508), .IN3(n510), .QN(n712) );\n" +
//"NAND4X0 U148 ( .IN1(n713), .IN2(n714), .IN3(n715), .IN4(n716), .QN(n675) );\n" +
//"NOR4X0 U149 ( .IN1(n717), .IN2(In4_counter[24]), .IN3(In4_counter[26]), .IN4(In4_counter[25]), .QN(n716) );\n" +
//"NAND4X0 U150 ( .IN1(n549), .IN2(n548), .IN3(n547), .IN4(n718), .QN(n717) );\n" +
//"NAND3X0 U151 ( .IN1(In4_counter[5]), .IN2(In4_counter[4]), .IN3(n719), .QN(n718) );\n" +
//"NAND3X0 U152 ( .IN1(n560), .IN2(n720), .IN3(n561), .QN(n719) );\n" +
//"NAND2X0 U153 ( .IN1(In4_counter[1]), .IN2(In4_counter[0]), .QN(n720) );\n" +
//"NOR4X0 U154 ( .IN1(n721), .IN2(In4_counter[18]), .IN3(In4_counter[20]), .IN4(In4_counter[19]), .QN(n715) );\n" +
//"NAND3X0 U155 ( .IN1(n551), .IN2(n550), .IN3(n552), .QN(n721) );\n" +
//"NOR4X0 U156 ( .IN1(n722), .IN2(In4_counter[11]), .IN3(In4_counter[13]), .IN4(In4_counter[12]), .QN(n714) );\n" +
//"NAND4X0 U157 ( .IN1(n556), .IN2(n555), .IN3(n554), .IN4(n553), .QN(n722) );\n" +
//"NOR4X0 U158 ( .IN1(n723), .IN2(In4_counter[30]), .IN3(In4_counter[7]), .IN4(In4_counter[6]), .QN(n713) );\n" +
//"NAND3X0 U159 ( .IN1(n558), .IN2(n557), .IN3(n559), .QN(n723) );\n" +
//"NAND4X0 U160 ( .IN1(n724), .IN2(n725), .IN3(n726), .IN4(n727), .QN(n674) );\n" +
//"NOR4X0 U161 ( .IN1(n728), .IN2(In1_counter[25]), .IN3(In1_counter[27]), .IN4(In1_counter[26]), .QN(n727) );\n" +
//"NAND4X0 U162 ( .IN1(n516), .IN2(n515), .IN3(n514), .IN4(n729), .QN(n728) );\n" +
//"NAND3X0 U163 ( .IN1(In1_counter[5]), .IN2(In1_counter[4]), .IN3(n730), .QN(n729) );\n" +
//"NAND3X0 U164 ( .IN1(n527), .IN2(n731), .IN3(n528), .QN(n730) );\n" +
//"NAND2X0 U165 ( .IN1(In1_counter[1]), .IN2(In1_counter[0]), .QN(n731) );\n" +
//"NOR4X0 U166 ( .IN1(n732), .IN2(In1_counter[19]), .IN3(In1_counter[21]), .IN4(In1_counter[20]), .QN(n726) );\n" +
//"NAND3X0 U167 ( .IN1(n518), .IN2(n517), .IN3(n519), .QN(n732) );\n" +
//"NOR4X0 U168 ( .IN1(n733), .IN2(In1_counter[12]), .IN3(In1_counter[14]), .IN4(In1_counter[13]), .QN(n725) );\n" +
//"NAND4X0 U169 ( .IN1(n523), .IN2(n522), .IN3(n521), .IN4(n520), .QN(n733) );\n" +
//"NOR4X0 U170 ( .IN1(n734), .IN2(In1_counter[6]), .IN3(In1_counter[8]), .IN4(In1_counter[7]), .QN(n724) );\n" +
//"NAND3X0 U171 ( .IN1(n525), .IN2(n524), .IN3(n526), .QN(n734) );\n" +
//"ISOLANDX1 U172 ( .D(N319), .ISO(n629), .Q(N351) );\n" +
//"ISOLANDX1 U173 ( .D(N318), .ISO(n629), .Q(N350) );\n" +
//"ISOLANDX1 U174 ( .D(N317), .ISO(n629), .Q(N349) );\n" +
//"ISOLANDX1 U175 ( .D(N316), .ISO(n629), .Q(N348) );\n" +
//"ISOLANDX1 U176 ( .D(N315), .ISO(n629), .Q(N347) );\n" +
//"ISOLANDX1 U177 ( .D(N314), .ISO(n629), .Q(N346) );\n" +
//"ISOLANDX1 U178 ( .D(N313), .ISO(n629), .Q(N345) );\n" +
//"ISOLANDX1 U179 ( .D(N312), .ISO(n629), .Q(N344) );\n" +
//"ISOLANDX1 U180 ( .D(N311), .ISO(n629), .Q(N343) );\n" +
//"ISOLANDX1 U181 ( .D(N310), .ISO(n629), .Q(N342) );\n" +
//"ISOLANDX1 U182 ( .D(N309), .ISO(n629), .Q(N341) );\n" +
//"ISOLANDX1 U183 ( .D(N308), .ISO(n629), .Q(N340) );\n" +
//"ISOLANDX1 U184 ( .D(N307), .ISO(n629), .Q(N339) );\n" +
//"ISOLANDX1 U185 ( .D(N306), .ISO(n629), .Q(N338) );\n" +
//"ISOLANDX1 U186 ( .D(N305), .ISO(n629), .Q(N337) );\n" +
//"ISOLANDX1 U187 ( .D(N304), .ISO(n629), .Q(N336) );\n" +
//"ISOLANDX1 U188 ( .D(N303), .ISO(n629), .Q(N335) );\n" +
//"ISOLANDX1 U189 ( .D(N302), .ISO(n629), .Q(N334) );\n" +
//"ISOLANDX1 U190 ( .D(N301), .ISO(n629), .Q(N333) );\n" +
//"ISOLANDX1 U191 ( .D(N300), .ISO(n629), .Q(N332) );\n";
//          
//       Trojans   += "ISOLANDX1 U192 ( .D(N299), .ISO(n629), .Q(N331) );\n" +
//"ISOLANDX1 U193 ( .D(N298), .ISO(n629), .Q(N330) );\n" +
//"ISOLANDX1 U194 ( .D(N297), .ISO(n629), .Q(N329) );\n" +
//"ISOLANDX1 U195 ( .D(N296), .ISO(n629), .Q(N328) );\n" +
//"ISOLANDX1 U196 ( .D(N295), .ISO(n629), .Q(N327) );\n" +
//"ISOLANDX1 U197 ( .D(N294), .ISO(n629), .Q(N326) );\n" +
//"ISOLANDX1 U198 ( .D(N293), .ISO(n629), .Q(N325) );\n" +
//"ISOLANDX1 U199 ( .D(N292), .ISO(n629), .Q(N324) );\n" +
//"ISOLANDX1 U200 ( .D(N291), .ISO(n629), .Q(N323) );\n" +
//"ISOLANDX1 U201 ( .D(N290), .ISO(n629), .Q(N322) );\n" +
//"ISOLANDX1 U202 ( .D(N289), .ISO(n629), .Q(N321) );\n" +
//"ISOLANDX1 U203 ( .D(N288), .ISO(n629), .Q(N320) );\n" +
//"OA21X1 U204 ( .IN1(n672), .IN2(n735), .IN3(n759), .Q(n629) );\n" +
//"AND4X1 U205 ( .IN1(n673), .IN2(Clock_counter[2]), .IN3(n736), .IN4(Clock_counter[3]), .Q(n735) );\n" +
//"NOR2X0 U206 ( .IN1(n760), .IN2(n758), .QN(n736) );\n" +
//"NAND4X0 U207 ( .IN1(n737), .IN2(n738), .IN3(n739), .IN4(n740), .QN(n672) );\n" +
//"NOR4X0 U208 ( .IN1(n741), .IN2(Clock_counter[12]), .IN3(Clock_counter[10]), .IN4(Clock_counter[11]), .QN(n740) );\n" +
//"NAND4X0 U209 ( .IN1(n748), .IN2(n747), .IN3(n746), .IN4(n742), .QN(n741) );\n" +
//"NAND2X0 U210 ( .IN1(n673), .IN2(Clock_counter[4]), .QN(n742) );\n" +
//"AND2X1 U211 ( .IN1(Clock_counter[5]), .IN2(Clock_counter[6]), .Q(n673) );\n" +
//"NOR4X0 U212 ( .IN1(n743), .IN2(Clock_counter[18]), .IN3(Clock_counter[16]), .IN4(Clock_counter[17]), .QN(n739) );\n" +
//"NAND3X0 U213 ( .IN1(n750), .IN2(n749), .IN3(n751), .QN(n743) );\n" +
//"NOR4X0 U214 ( .IN1(n744), .IN2(Clock_counter[24]), .IN3(Clock_counter[22]), .IN4(Clock_counter[23]), .QN(n738) );\n" +
//"NAND3X0 U215 ( .IN1(n753), .IN2(n752), .IN3(n754), .QN(n744) );\n" +
//"NOR4X0 U216 ( .IN1(n745), .IN2(Clock_counter[30]), .IN3(Clock_counter[28]), .IN4(Clock_counter[29]), .QN(n737) );\n" +
//"NAND3X0 U217 ( .IN1(n756), .IN2(n755), .IN3(n757), .QN(n745) );\n" +
//"MUX21X1 Trojan_Paylod ( .IN1(g10122_Tj), .IN2(g25715), .S(Trigger_out), .Q(g10122) );";
//          GateParser parser = new GateParser(gatesDefinitionLines,WiresArray,primaryInputs,primaryOutputs,Trojans);
//          String benchmarkName = "s38584-T300";
//          parser.saveInFileForScikit("/Users/reza/Desktop/"+benchmarkName+".nomral","/Users/reza/Desktop/"+benchmarkName+".trojan",true);
          
//    Replacer rp = new Replacer();
//    String input =  "  HADDX1 U1_1_30_seven ( .A0(In6_counter[30]), .B0(carry_seven[30]), .C1(carry_seven[31]), .SO(SUM[30]) );\n" +
//"  HADDX1 U1_1_1_seven ( .A0(In6_counter[1]), .B0(In6_counter[0]), .C1(carry_seven[2]), .SO(SUM[1]) );\n" +
//"  HADDX1 U1_1_4_seven ( .A0(In6_counter[4]), .B0(carry_seven[4]), .C1(carry_seven[5]), .SO(SUM[4]) );\n" +
//"  HADDX1 U1_1_5_seven ( .A0(In6_counter[5]), .B0(carry_seven[5]), .C1(carry_seven[6]), .SO(SUM[5]) );\n" +
//"  HADDX1 U1_1_6_seven ( .A0(In6_counter[6]), .B0(carry_seven[6]), .C1(carry_seven[7]), .SO(SUM[6]) );\n" +
//"  HADDX1 U1_1_7_seven ( .A0(In6_counter[7]), .B0(carry_seven[7]), .C1(carry_seven[8]), .SO(SUM[7]) );\n" +
//"  HADDX1 U1_1_8_seven ( .A0(In6_counter[8]), .B0(carry_seven[8]), .C1(carry_seven[9]), .SO(SUM[8]) );\n" +
//"  HADDX1 U1_1_12_seven ( .A0(In6_counter[12]), .B0(carry_seven[12]), .C1(carry_seven[13]), .SO(SUM[12]) );\n" +
//"  HADDX1 U1_1_13_seven ( .A0(In6_counter[13]), .B0(carry_seven[13]), .C1(carry_seven[14]), .SO(SUM[13]) );\n" +
//"  HADDX1 U1_1_14_seven ( .A0(In6_counter[14]), .B0(carry_seven[14]), .C1(carry_seven[15]), .SO(SUM[14]) );\n" +
//"  HADDX1 U1_1_19_seven ( .A0(In6_counter[19]), .B0(carry_seven[19]), .C1(carry_seven[20]), .SO(SUM[19]) );\n" +
//"  HADDX1 U1_1_20_seven ( .A0(In6_counter[20]), .B0(carry_seven[20]), .C1(carry_seven[21]), .SO(SUM[20]) );\n" +
//"  HADDX1 U1_1_21_seven ( .A0(In6_counter[21]), .B0(carry_seven[21]), .C1(carry_seven[22]), .SO(SUM[21]) );\n" +
//"  HADDX1 U1_1_25_seven ( .A0(In6_counter[25]), .B0(carry_seven[25]), .C1(carry_seven[26]), .SO(SUM[25]) );\n" +
//"  HADDX1 U1_1_26_seven ( .A0(In6_counter[26]), .B0(carry_seven[26]), .C1(carry_seven[27]), .SO(SUM[26]) );\n" +
//"  HADDX1 U1_1_27_seven ( .A0(In6_counter[27]), .B0(carry_seven[27]), .C1(carry_seven[28]), .SO(SUM[27]) );\n" +
//"  HADDX1 U1_1_2_seven ( .A0(In6_counter[2]), .B0(carry_seven[2]), .C1(carry_seven[3]), .SO(SUM[2]) );\n" +
//"  HADDX1 U1_1_3_seven ( .A0(In6_counter[3]), .B0(carry_seven[3]), .C1(carry_seven[4]), .SO(SUM[3]) );\n" +
//"  HADDX1 U1_1_9_seven ( .A0(In6_counter[9]), .B0(carry_seven[9]), .C1(carry_seven[10]), .SO(SUM[9]) );\n" +
//"  HADDX1 U1_1_10_seven ( .A0(In6_counter[10]), .B0(carry_seven[10]), .C1(carry_seven[11]), .SO(SUM[10]) );\n" +
//"  HADDX1 U1_1_11_seven ( .A0(In6_counter[11]), .B0(carry_seven[11]), .C1(carry_seven[12]), .SO(SUM[11]) );\n" +
//"  HADDX1 U1_1_15_seven ( .A0(In6_counter[15]), .B0(carry_seven[15]), .C1(carry_seven[16]), .SO(SUM[15]) );\n" +
//"  HADDX1 U1_1_16_seven ( .A0(In6_counter[16]), .B0(carry_seven[16]), .C1(carry_seven[17]), .SO(SUM[16]) );\n" +
//"  HADDX1 U1_1_17_seven ( .A0(In6_counter[17]), .B0(carry_seven[17]), .C1(carry_seven[18]), .SO(SUM[17]) );\n" +
//"  HADDX1 U1_1_18_seven ( .A0(In6_counter[18]), .B0(carry_seven[18]), .C1(carry_seven[19]), .SO(SUM[18]) );\n" +
//"  HADDX1 U1_1_22_seven ( .A0(In6_counter[22]), .B0(carry_seven[22]), .C1(carry_seven[23]), .SO(SUM[22]) );\n" +
//"  HADDX1 U1_1_23_seven ( .A0(In6_counter[23]), .B0(carry_seven[23]), .C1(carry_seven[24]), .SO(SUM[23]) );\n" +
//"  HADDX1 U1_1_24_seven ( .A0(In6_counter[24]), .B0(carry_seven[24]), .C1(carry_seven[25]), .SO(SUM[24]) );\n" +
//"  HADDX1 U1_1_28_seven ( .A0(In6_counter[28]), .B0(carry_seven[28]), .C1(carry_seven[29]), .SO(SUM[28]) );\n" +
//"  HADDX1 U1_1_29_seven ( .A0(In6_counter[29]), .B0(carry_seven[29]), .C1(carry_seven[30]), .SO(SUM[29]) );\n" +
//"  INVX0 U1_seven ( .IN(In6_counter[0]), .QN(SUM[0]) );\n" +
//"  XOR2X1 U2_seven ( .IN1(carry_seven[31]), .IN2(In6_counter[31]), .Q(SUM[31]) );";
//    for(int i=40;i<72;i++)
//        input = rp.replace(input, "SUM["+(i-40)+"]", "N"+i);
//    System.out.println(input);
//
//    String pathList = "/Users/reza/Desktop/Thesis/Results/s15850-T100.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1400.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1500.nomral,/Users/reza/Desktop/Thesis/Results/s38584-T100.nomral,/Users/reza/Desktop/Thesis/Results/s35932-T300.trojan,/Users/reza/Desktop/Thesis/Results/s35932-T200.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1300.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1200.trojan,/Users/reza/Desktop/Thesis/Results/s38417-T100.nomral,/Users/reza/Desktop/Thesis/Results/s38584-T100.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1400.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1500.trojan,/Users/reza/Desktop/Thesis/Results/s15850-T100.trojan,/Users/reza/Desktop/Thesis/Results/s38417-T100.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1300.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1200.nomral,/Users/reza/Desktop/Thesis/Results/s35932-T300.nomral,/Users/reza/Desktop/Thesis/Results/s35932-T200.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1000.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1100.trojan,/Users/reza/Desktop/Thesis/Results/s38417-T200.nomral,/Users/reza/Desktop/Thesis/Results/s38417-T300.nomral,/Users/reza/Desktop/Thesis/Results/s35932-T100.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1600.nomral,/Users/reza/Desktop/Thesis/Results/s35932-T100.nomral,/Users/reza/Desktop/Thesis/Results/s38417-T200.trojan,/Users/reza/Desktop/Thesis/Results/s38417-T300.trojan,/Users/reza/Desktop/Thesis/Results/RS232-T1000.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1100.nomral,/Users/reza/Desktop/Thesis/Results/RS232-T1600.trojan";
//    FilesParser fileparser = new FilesParser(pathList, "/Users/reza/Desktop/finalWithoutProcess.txt");
//    fileparser.balanceTrojans("/Users/reza/Desktop/Thesis/justtrojansunified.txt", "/Users/reza/Desktop/Thesis/finalBalancedTrojans.txt", 9);
//    fileparser.unifyFinalForScikit("/Users/reza/Desktop/Thesis/justtrojans.txt","/Users/reza/Desktop/Thesis/justtrojansunified.txt");
//    fileparser.calculateAverageOfLines("/Users/reza/Desktop/Thesis/justtrojansunified.txt");
//      String path1 = "/Users/reza/Desktop/Results/s15850-T100.nomral,/Users/reza/Desktop/Results/RS232-T1400.nomral,/Users/reza/Desktop/Results/RS232-T1500.nomral,/Users/reza/Desktop/Results/s38584-T100.nomral,/Users/reza/Desktop/Results/s35932-T300.trojan,/Users/reza/Desktop/Results/s35932-T200.trojan,/Users/reza/Desktop/Results/RS232-T1300.trojan,/Users/reza/Desktop/Results/RS232-T1200.trojan,/Users/reza/Desktop/Results/s38417-T100.nomral,/Users/reza/Desktop/Results/s38584-T100.trojan,/Users/reza/Desktop/Results/RS232-T1400.trojan,/Users/reza/Desktop/Results/RS232-T1500.trojan,/Users/reza/Desktop/Results/s15850-T100.trojan,/Users/reza/Desktop/Results/s38417-T100.trojan,/Users/reza/Desktop/Results/RS232-T1300.nomral,/Users/reza/Desktop/Results/RS232-T1200.nomral,/Users/reza/Desktop/Results/s35932-T300.nomral,/Users/reza/Desktop/Results/s35932-T200.nomral,/Users/reza/Desktop/Results/RS232-T1000.trojan,/Users/reza/Desktop/Results/RS232-T1100.trojan,/Users/reza/Desktop/Results/s38417-T200.nomral,/Users/reza/Desktop/Results/s38417-T300.nomral,/Users/reza/Desktop/Results/s35932-T100.trojan,/Users/reza/Desktop/Results/RS232-T1600.nomral,/Users/reza/Desktop/Results/s35932-T100.nomral,/Users/reza/Desktop/Results/s38417-T200.trojan,/Users/reza/Desktop/Results/s38417-T300.trojan,/Users/reza/Desktop/Results/RS232-T1000.nomral,/Users/reza/Desktop/Results/RS232-T1100.nomral,/Users/reza/Desktop/Results/RS232-T1600.trojan";
      String[] modelnames = "RS232-T1000,RS232-T1400,s35932-T100,s38417-T200,RS232-T1100,RS232-T1500,s35932-T200,s38417-T300,RS232-T1200,RS232-T1600,s35932-T300,s38584-T100,s38584-T200,s38584-T300,RS232-T1300,s15850-T100,s38417-T100".split(",");
      String path1 = "/Users/reza/Desktop/Thesis/Results/";
      FilesParser fileparser = new FilesParser(path1, modelnames ,"/Users/reza/Desktop/");



//            String paths="/Users/reza/Desktop/Learneds/RS232-T1300/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1400/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1000/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1200/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1600/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1100/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1500/src/180nm/uart.v,/Users/reza/Desktop/Learneds/s15850-T100/src/TjIn/s15850_scan.v,/Users/reza/Desktop/Learneds/s38417-T100/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s35932-T300/src/TjIn/s35932_scan.v,/Users/reza/Desktop/Learneds/s38417-T200/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s35932-T200/src/TjIn/s35932_scan.v,/Users/reza/Desktop/Learneds/s38417-T300/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s38584-T100/src/TjIn/s38584_scan.v,/Users/reza/Desktop/Learneds/s35932-T100/src/TjIn/s35932_scan.v,/Users/reza/Desktop/Learneds/s38584-T200/src/TjIn/s38584_scan.v,/Users/reza/Desktop/Learneds/s38584-T300/src/TjIn/s38584_scan.v";
////            paths+=",/Users/reza/Downloads/TRIT-TS/s35932_T412/s35932_T412.v,/Users/reza/Downloads/TRIT-TS/s35932_T618/s35932_T618.v,/Users/reza/Downloads/TRIT-TS/s35932_T415/s35932_T415.v,/Users/reza/Downloads/TRIT-TS/s35932_T611/s35932_T611.v,/Users/reza/Downloads/TRIT-TS/s35932_T423/s35932_T423.v,/Users/reza/Downloads/TRIT-TS/s35932_T424/s35932_T424.v,/Users/reza/Downloads/TRIT-TS/s35932_T616/s35932_T616.v,/Users/reza/Downloads/TRIT-TS/s1423_T420/s1423_T420.v,/Users/reza/Downloads/TRIT-TS/s1423_T612/s1423_T612.v,/Users/reza/Downloads/TRIT-TS/s1423_T615/s1423_T615.v,/Users/reza/Downloads/TRIT-TS/s1423_T427/s1423_T427.v,/Users/reza/Downloads/TRIT-TS/s1423_T418/s1423_T418.v,/Users/reza/Downloads/TRIT-TS/s1423_T411/s1423_T411.v,/Users/reza/Downloads/TRIT-TS/s35932_T441/s35932_T441.v,/Users/reza/Downloads/TRIT-TS/s1423_T416/s1423_T416.v,/Users/reza/Downloads/TRIT-TS/s1423_T429/s1423_T429.v,/Users/reza/Downloads/TRIT-TS/s35932_T617/s35932_T617.v,/Users/reza/Downloads/TRIT-TS/s35932_T425/s35932_T425.v,/Users/reza/Downloads/TRIT-TS/s35932_T422/s35932_T422.v,/Users/reza/Downloads/TRIT-TS/s35932_T610/s35932_T610.v,/Users/reza/Downloads/TRIT-TS/s35932_T619/s35932_T619.v,/Users/reza/Downloads/TRIT-TS/s35932_T414/s35932_T414.v,/Users/reza/Downloads/TRIT-TS/s35932_T413/s35932_T413.v,/Users/reza/Downloads/TRIT-TS/s1423_T417/s1423_T417.v,/Users/reza/Downloads/TRIT-TS/s1423_T428/s1423_T428.v,/Users/reza/Downloads/TRIT-TS/s35932_T440/s35932_T440.v,/Users/reza/Downloads/TRIT-TS/s1423_T410/s1423_T410.v,/Users/reza/Downloads/TRIT-TS/s1423_T426/s1423_T426.v,/Users/reza/Downloads/TRIT-TS/s1423_T614/s1423_T614.v,/Users/reza/Downloads/TRIT-TS/s1423_T419/s1423_T419.v,/Users/reza/Downloads/TRIT-TS/s1423_T613/s1423_T613.v,/Users/reza/Downloads/TRIT-TS/s1423_T421/s1423_T421.v,/Users/reza/Downloads/TRIT-TS/s13207_T610/s13207_T610.v,/Users/reza/Downloads/TRIT-TS/s13207_T422/s13207_T422.v,/Users/reza/Downloads/TRIT-TS/s15850_T432/s15850_T432.v,/Users/reza/Downloads/TRIT-TS/s15850_T600/s15850_T600.v,/Users/reza/Downloads/TRIT-TS/s15850_T607/s15850_T607.v,/Users/reza/Downloads/TRIT-TS/s15850_T435/s15850_T435.v,/Users/reza/Downloads/TRIT-TS/s13207_T425/s13207_T425.v,/Users/reza/Downloads/TRIT-TS/s13207_T617/s13207_T617.v,/Users/reza/Downloads/TRIT-TS/s15850_T403/s15850_T403.v,/Users/reza/Downloads/TRIT-TS/s13207_T413/s13207_T413.v,/Users/reza/Downloads/TRIT-TS/s13207_T619/s13207_T619.v,/Users/reza/Downloads/TRIT-TS/s15850_T609/s15850_T609.v,/Users/reza/Downloads/TRIT-TS/s13207_T414/s13207_T414.v,/Users/reza/Downloads/TRIT-TS/s15850_T404/s15850_T404.v,/Users/reza/Downloads/TRIT-TS/s13207_T440/s13207_T440.v,/Users/reza/Downloads/TRIT-TS/s15850_T450/s15850_T450.v,/Users/reza/Downloads/TRIT-TS/s15850_T457/s15850_T457.v,/Users/reza/Downloads/TRIT-TS/s13207_T447/s13207_T447.v,/Users/reza/Downloads/TRIT-TS/s15850_T468/s15850_T468.v,/Users/reza/Downloads/TRIT-TS/s13207_T478/s13207_T478.v,/Users/reza/Downloads/TRIT-TS/s13207_T485/s13207_T485.v,/Users/reza/Downloads/TRIT-TS/s15850_T461/s15850_T461.v,/Users/reza/Downloads/TRIT-TS/s13207_T471/s13207_T471.v,/Users/reza/Downloads/TRIT-TS/s13207_T476/s13207_T476.v,/Users/reza/Downloads/TRIT-TS/s15850_T466/s15850_T466.v,/Users/reza/Downloads/TRIT-TS/s13207_T482/s13207_T482.v,/Users/reza/Downloads/TRIT-TS/s13207_T449/s13207_T449.v,/Users/reza/Downloads/TRIT-TS/s15850_T459/s15850_T459.v,/Users/reza/Downloads/TRIT-TS/s15850_T608/s15850_T608.v,/Users/reza/Downloads/TRIT-TS/s13207_T618/s13207_T618.v,/Users/reza/Downloads/TRIT-TS/s15850_T405/s15850_T405.v,/Users/reza/Downloads/TRIT-TS/s13207_T415/s13207_T415.v,/Users/reza/Downloads/TRIT-TS/s13207_T412/s13207_T412.v,/Users/reza/Downloads/TRIT-TS/s15850_T402/s15850_T402.v,/Users/reza/Downloads/TRIT-TS/s13207_T616/s13207_T616.v,/Users/reza/Downloads/TRIT-TS/s13207_T424/s13207_T424.v,/Users/reza/Downloads/TRIT-TS/s15850_T434/s15850_T434.v,/Users/reza/Downloads/TRIT-TS/s15850_T606/s15850_T606.v,/Users/reza/Downloads/TRIT-TS/s15850_T601/s15850_T601.v,/Users/reza/Downloads/TRIT-TS/s15850_T433/s15850_T433.v,/Users/reza/Downloads/TRIT-TS/s13207_T423/s13207_T423.v,/Users/reza/Downloads/TRIT-TS/s13207_T611/s13207_T611.v,/Users/reza/Downloads/TRIT-TS/s15850_T467/s15850_T467.v,/Users/reza/Downloads/TRIT-TS/s13207_T483/s13207_T483.v,/Users/reza/Downloads/TRIT-TS/s13207_T477/s13207_T477.v,/Users/reza/Downloads/TRIT-TS/s15850_T458/s15850_T458.v,/Users/reza/Downloads/TRIT-TS/s13207_T448/s13207_T448.v,/Users/reza/Downloads/TRIT-TS/s13207_T470/s13207_T470.v,/Users/reza/Downloads/TRIT-TS/s13207_T484/s13207_T484.v,/Users/reza/Downloads/TRIT-TS/s15850_T460/s15850_T460.v,/Users/reza/Downloads/TRIT-TS/s13207_T446/s13207_T446.v,/Users/reza/Downloads/TRIT-TS/s15850_T456/s15850_T456.v,/Users/reza/Downloads/TRIT-TS/s13207_T479/s13207_T479.v,/Users/reza/Downloads/TRIT-TS/s15850_T469/s15850_T469.v,/Users/reza/Downloads/TRIT-TS/s15850_T451/s15850_T451.v,/Users/reza/Downloads/TRIT-TS/s13207_T441/s13207_T441.v,/Users/reza/Downloads/TRIT-TS/s15850_T473/s15850_T473.v,/Users/reza/Downloads/TRIT-TS/s13207_T463/s13207_T463.v,/Users/reza/Downloads/TRIT-TS/s15850_T487/s15850_T487.v,/Users/reza/Downloads/TRIT-TS/s15850_T480/s15850_T480.v,/Users/reza/Downloads/TRIT-TS/s13207_T464/s13207_T464.v,/Users/reza/Downloads/TRIT-TS/s15850_T474/s15850_T474.v,/Users/reza/Downloads/TRIT-TS/s15850_T489/s15850_T489.v,/Users/reza/Downloads/TRIT-TS/s13207_T452/s13207_T452.v,/Users/reza/Downloads/TRIT-TS/s15850_T442/s15850_T442.v,/Users/reza/Downloads/TRIT-TS/s15850_T445/s15850_T445.v,/Users/reza/Downloads/TRIT-TS/s13207_T455/s13207_T455.v,/Users/reza/Downloads/TRIT-TS/s15850_T411/s15850_T411.v,/Users/reza/Downloads/TRIT-TS/s13207_T401/s13207_T401.v,/Users/reza/Downloads/TRIT-TS/s13207_T406/s13207_T406.v,/Users/reza/Downloads/TRIT-TS/s15850_T416/s15850_T416.v,/Users/reza/Downloads/TRIT-TS/s13207_T439/s13207_T439.v,/Users/reza/Downloads/TRIT-TS/s15850_T429/s15850_T429.v,/Users/reza/Downloads/TRIT-TS/s13207_T430/s13207_T430.v,/Users/reza/Downloads/TRIT-TS/s13207_T602/s13207_T602.v,/Users/reza/Downloads/TRIT-TS/s15850_T612/s15850_T612.v,/Users/reza/Downloads/TRIT-TS/s15850_T420/s15850_T420.v,/Users/reza/Downloads/TRIT-TS/s15850_T427/s15850_T427.v,/Users/reza/Downloads/TRIT-TS/s15850_T615/s15850_T615.v,/Users/reza/Downloads/TRIT-TS/s13207_T605/s13207_T605.v,/Users/reza/Downloads/TRIT-TS/s13207_T437/s13207_T437.v,/Users/reza/Downloads/TRIT-TS/s15850_T418/s15850_T418.v,/Users/reza/Downloads/TRIT-TS/s13207_T408/s13207_T408.v,/Users/reza/Downloads/TRIT-TS/s13207_T454/s13207_T454.v,/Users/reza/Downloads/TRIT-TS/s15850_T444/s15850_T444.v,/Users/reza/Downloads/TRIT-TS/s15850_T488/s15850_T488.v,/Users/reza/Downloads/TRIT-TS/s15850_T443/s15850_T443.v,/Users/reza/Downloads/TRIT-TS/s13207_T453/s13207_T453.v,/Users/reza/Downloads/TRIT-TS/s15850_T475/s15850_T475.v,/Users/reza/Downloads/TRIT-TS/s15850_T481/s15850_T481.v,/Users/reza/Downloads/TRIT-TS/s13207_T465/s13207_T465.v,/Users/reza/Downloads/TRIT-TS/s13207_T462/s13207_T462.v,/Users/reza/Downloads/TRIT-TS/s15850_T486/s15850_T486.v,/Users/reza/Downloads/TRIT-TS/s15850_T472/s15850_T472.v,/Users/reza/Downloads/TRIT-TS/s13207_T436/s13207_T436.v,/Users/reza/Downloads/TRIT-TS/s13207_T604/s13207_T604.v,/Users/reza/Downloads/TRIT-TS/s15850_T614/s15850_T614.v,/Users/reza/Downloads/TRIT-TS/s15850_T426/s15850_T426.v,/Users/reza/Downloads/TRIT-TS/s13207_T409/s13207_T409.v,/Users/reza/Downloads/TRIT-TS/s15850_T419/s15850_T419.v,/Users/reza/Downloads/TRIT-TS/s15850_T421/s15850_T421.v,/Users/reza/Downloads/TRIT-TS/s15850_T613/s15850_T613.v,/Users/reza/Downloads/TRIT-TS/s13207_T603/s13207_T603.v,/Users/reza/Downloads/TRIT-TS/s13207_T431/s13207_T431.v,/Users/reza/Downloads/TRIT-TS/s15850_T417/s15850_T417.v,/Users/reza/Downloads/TRIT-TS/s13207_T407/s13207_T407.v,/Users/reza/Downloads/TRIT-TS/s15850_T428/s15850_T428.v,/Users/reza/Downloads/TRIT-TS/s13207_T438/s13207_T438.v,/Users/reza/Downloads/TRIT-TS/s13207_T400/s13207_T400.v,/Users/reza/Downloads/TRIT-TS/s15850_T410/s15850_T410.v,/Users/reza/Downloads/TRIT-TS/s1423_T403/s1423_T403.v,/Users/reza/Downloads/TRIT-TS/s1423_T609/s1423_T609.v,/Users/reza/Downloads/TRIT-TS/s1423_T404/s1423_T404.v,/Users/reza/Downloads/TRIT-TS/s1423_T600/s1423_T600.v,/Users/reza/Downloads/TRIT-TS/s1423_T607/s1423_T607.v,/Users/reza/Downloads/TRIT-TS/s35932_T431/s35932_T431.v,/Users/reza/Downloads/TRIT-TS/s35932_T603/s35932_T603.v,/Users/reza/Downloads/TRIT-TS/s35932_T604/s35932_T604.v,/Users/reza/Downloads/TRIT-TS/s35932_T436/s35932_T436.v,/Users/reza/Downloads/TRIT-TS/s35932_T409/s35932_T409.v,/Users/reza/Downloads/TRIT-TS/s35932_T400/s35932_T400.v,/Users/reza/Downloads/TRIT-TS/s35932_T407/s35932_T407.v,/Users/reza/Downloads/TRIT-TS/s35932_T438/s35932_T438.v,/Users/reza/Downloads/TRIT-TS/s1423_T606/s1423_T606.v,/Users/reza/Downloads/TRIT-TS/s1423_T601/s1423_T601.v,/Users/reza/Downloads/TRIT-TS/s1423_T608/s1423_T608.v,/Users/reza/Downloads/TRIT-TS/s1423_T405/s1423_T405.v,/Users/reza/Downloads/TRIT-TS/s1423_T402/s1423_T402.v,/Users/reza/Downloads/TRIT-TS/s35932_T406/s35932_T406.v,/Users/reza/Downloads/TRIT-TS/s35932_T439/s35932_T439.v,/Users/reza/Downloads/TRIT-TS/s35932_T401/s35932_T401.v,/Users/reza/Downloads/TRIT-TS/s35932_T437/s35932_T437.v,/Users/reza/Downloads/TRIT-TS/s35932_T605/s35932_T605.v,/Users/reza/Downloads/TRIT-TS/s35932_T408/s35932_T408.v,/Users/reza/Downloads/TRIT-TS/s35932_T602/s35932_T602.v,/Users/reza/Downloads/TRIT-TS/s35932_T430/s35932_T430.v,/Users/reza/Downloads/TRIT-TS/s1423_T424/s1423_T424.v,/Users/reza/Downloads/TRIT-TS/s1423_T616/s1423_T616.v,/Users/reza/Downloads/TRIT-TS/s1423_T611/s1423_T611.v,/Users/reza/Downloads/TRIT-TS/s1423_T423/s1423_T423.v,/Users/reza/Downloads/TRIT-TS/s1423_T415/s1423_T415.v,/Users/reza/Downloads/TRIT-TS/s1423_T618/s1423_T618.v,/Users/reza/Downloads/TRIT-TS/s1423_T412/s1423_T412.v,/Users/reza/Downloads/TRIT-TS/s35932_T442/s35932_T442.v,/Users/reza/Downloads/TRIT-TS/s35932_T429/s35932_T429.v,/Users/reza/Downloads/TRIT-TS/s35932_T416/s35932_T416.v,/Users/reza/Downloads/TRIT-TS/s35932_T411/s35932_T411.v,/Users/reza/Downloads/TRIT-TS/s35932_T418/s35932_T418.v,/Users/reza/Downloads/TRIT-TS/s35932_T615/s35932_T615.v,/Users/reza/Downloads/TRIT-TS/s35932_T427/s35932_T427.v,/Users/reza/Downloads/TRIT-TS/s35932_T420/s35932_T420.v,/Users/reza/Downloads/TRIT-TS/s35932_T612/s35932_T612.v,/Users/reza/Downloads/TRIT-TS/s1423_T413/s1423_T413.v,/Users/reza/Downloads/TRIT-TS/s1423_T414/s1423_T414.v,/Users/reza/Downloads/TRIT-TS/s1423_T619/s1423_T619.v,/Users/reza/Downloads/TRIT-TS/s1423_T422/s1423_T422.v,/Users/reza/Downloads/TRIT-TS/s1423_T610/s1423_T610.v,/Users/reza/Downloads/TRIT-TS/s1423_T617/s1423_T617.v,/Users/reza/Downloads/TRIT-TS/s1423_T425/s1423_T425.v,/Users/reza/Downloads/TRIT-TS/s35932_T613/s35932_T613.v,/Users/reza/Downloads/TRIT-TS/s35932_T421/s35932_T421.v,/Users/reza/Downloads/TRIT-TS/s35932_T419/s35932_T419.v,/Users/reza/Downloads/TRIT-TS/s35932_T426/s35932_T426.v,/Users/reza/Downloads/TRIT-TS/s35932_T614/s35932_T614.v,/Users/reza/Downloads/TRIT-TS/s35932_T410/s35932_T410.v,/Users/reza/Downloads/TRIT-TS/s35932_T428/s35932_T428.v,/Users/reza/Downloads/TRIT-TS/s35932_T417/s35932_T417.v,/Users/reza/Downloads/TRIT-TS/s15850_T454/s15850_T454.v,/Users/reza/Downloads/TRIT-TS/s13207_T444/s13207_T444.v,/Users/reza/Downloads/TRIT-TS/s13207_T443/s13207_T443.v,/Users/reza/Downloads/TRIT-TS/s15850_T453/s15850_T453.v,/Users/reza/Downloads/TRIT-TS/s13207_T488/s13207_T488.v,/Users/reza/Downloads/TRIT-TS/s13207_T475/s13207_T475.v,/Users/reza/Downloads/TRIT-TS/s15850_T465/s15850_T465.v,/Users/reza/Downloads/TRIT-TS/s13207_T481/s13207_T481.v,/Users/reza/Downloads/TRIT-TS/s13207_T486/s13207_T486.v,/Users/reza/Downloads/TRIT-TS/s15850_T462/s15850_T462.v,/Users/reza/Downloads/TRIT-TS/s13207_T472/s13207_T472.v,/Users/reza/Downloads/TRIT-TS/s15850_T409/s15850_T409.v,/Users/reza/Downloads/TRIT-TS/s13207_T419/s13207_T419.v,/Users/reza/Downloads/TRIT-TS/s15850_T436/s15850_T436.v,/Users/reza/Downloads/TRIT-TS/s15850_T604/s15850_T604.v,/Users/reza/Downloads/TRIT-TS/s13207_T614/s13207_T614.v,/Users/reza/Downloads/TRIT-TS/s13207_T426/s13207_T426.v,/Users/reza/Downloads/TRIT-TS/s13207_T421/s13207_T421.v,/Users/reza/Downloads/TRIT-TS/s13207_T613/s13207_T613.v,/Users/reza/Downloads/TRIT-TS/s15850_T603/s15850_T603.v,/Users/reza/Downloads/TRIT-TS/s15850_T431/s15850_T431.v,/Users/reza/Downloads/TRIT-TS/s13207_T428/s13207_T428.v,/Users/reza/Downloads/TRIT-TS/s15850_T438/s15850_T438.v,/Users/reza/Downloads/TRIT-TS/s13207_T417/s13207_T417.v,/Users/reza/Downloads/TRIT-TS/s15850_T407/s15850_T407.v,/Users/reza/Downloads/TRIT-TS/s15850_T400/s15850_T400.v,/Users/reza/Downloads/TRIT-TS/s13207_T410/s13207_T410.v,/Users/reza/Downloads/TRIT-TS/s13207_T473/s13207_T473.v,/Users/reza/Downloads/TRIT-TS/s13207_T487/s13207_T487.v,/Users/reza/Downloads/TRIT-TS/s15850_T463/s15850_T463.v,/Users/reza/Downloads/TRIT-TS/s15850_T464/s15850_T464.v,/Users/reza/Downloads/TRIT-TS/s13207_T480/s13207_T480.v,/Users/reza/Downloads/TRIT-TS/s13207_T474/s13207_T474.v,/Users/reza/Downloads/TRIT-TS/s15850_T452/s15850_T452.v,/Users/reza/Downloads/TRIT-TS/s13207_T442/s13207_T442.v,/Users/reza/Downloads/TRIT-TS/s13207_T489/s13207_T489.v,/Users/reza/Downloads/TRIT-TS/s13207_T445/s13207_T445.v,/Users/reza/Downloads/TRIT-TS/s15850_T455/s15850_T455.v,/Users/reza/Downloads/TRIT-TS/s13207_T411/s13207_T411.v,/Users/reza/Downloads/TRIT-TS/s15850_T401/s15850_T401.v,/Users/reza/Downloads/TRIT-TS/s15850_T439/s15850_T439.v,/Users/reza/Downloads/TRIT-TS/s13207_T429/s13207_T429.v,/Users/reza/Downloads/TRIT-TS/s15850_T406/s15850_T406.v,/Users/reza/Downloads/TRIT-TS/s13207_T416/s13207_T416.v,/Users/reza/Downloads/TRIT-TS/s15850_T430/s15850_T430.v,/Users/reza/Downloads/TRIT-TS/s15850_T602/s15850_T602.v,/Users/reza/Downloads/TRIT-TS/s13207_T612/s13207_T612.v,/Users/reza/Downloads/TRIT-TS/s13207_T420/s13207_T420.v,/Users/reza/Downloads/TRIT-TS/s13207_T418/s13207_T418.v,/Users/reza/Downloads/TRIT-TS/s15850_T408/s15850_T408.v,/Users/reza/Downloads/TRIT-TS/s13207_T427/s13207_T427.v,/Users/reza/Downloads/TRIT-TS/s13207_T615/s13207_T615.v,/Users/reza/Downloads/TRIT-TS/s15850_T605/s15850_T605.v,/Users/reza/Downloads/TRIT-TS/s15850_T437/s15850_T437.v,/Users/reza/Downloads/TRIT-TS/s13207_T405/s13207_T405.v,/Users/reza/Downloads/TRIT-TS/s15850_T415/s15850_T415.v,/Users/reza/Downloads/TRIT-TS/s13207_T608/s13207_T608.v,/Users/reza/Downloads/TRIT-TS/s15850_T618/s15850_T618.v,/Users/reza/Downloads/TRIT-TS/s15850_T412/s15850_T412.v,/Users/reza/Downloads/TRIT-TS/s13207_T402/s13207_T402.v,/Users/reza/Downloads/TRIT-TS/s15850_T616/s15850_T616.v,/Users/reza/Downloads/TRIT-TS/s15850_T424/s15850_T424.v,/Users/reza/Downloads/TRIT-TS/s13207_T434/s13207_T434.v,/Users/reza/Downloads/TRIT-TS/s13207_T606/s13207_T606.v,/Users/reza/Downloads/TRIT-TS/s13207_T601/s13207_T601.v,/Users/reza/Downloads/TRIT-TS/s13207_T433/s13207_T433.v,/Users/reza/Downloads/TRIT-TS/s15850_T423/s15850_T423.v,/Users/reza/Downloads/TRIT-TS/s15850_T611/s15850_T611.v,/Users/reza/Downloads/TRIT-TS/s13207_T458/s13207_T458.v,/Users/reza/Downloads/TRIT-TS/s15850_T448/s15850_T448.v,/Users/reza/Downloads/TRIT-TS/s15850_T483/s15850_T483.v,/Users/reza/Downloads/TRIT-TS/s13207_T467/s13207_T467.v,/Users/reza/Downloads/TRIT-TS/s15850_T477/s15850_T477.v,/Users/reza/Downloads/TRIT-TS/s15850_T470/s15850_T470.v,/Users/reza/Downloads/TRIT-TS/s13207_T460/s13207_T460.v,/Users/reza/Downloads/TRIT-TS/s15850_T484/s15850_T484.v,/Users/reza/Downloads/TRIT-TS/s15850_T479/s15850_T479.v,/Users/reza/Downloads/TRIT-TS/s13207_T469/s13207_T469.v,/Users/reza/Downloads/TRIT-TS/s15850_T446/s15850_T446.v,/Users/reza/Downloads/TRIT-TS/s13207_T456/s13207_T456.v,/Users/reza/Downloads/TRIT-TS/s13207_T451/s13207_T451.v,/Users/reza/Downloads/TRIT-TS/s15850_T441/s15850_T441.v,/Users/reza/Downloads/TRIT-TS/s15850_T610/s15850_T610.v,/Users/reza/Downloads/TRIT-TS/s15850_T422/s15850_T422.v,/Users/reza/Downloads/TRIT-TS/s13207_T432/s13207_T432.v,/Users/reza/Downloads/TRIT-TS/s13207_T600/s13207_T600.v,/Users/reza/Downloads/TRIT-TS/s13207_T607/s13207_T607.v,/Users/reza/Downloads/TRIT-TS/s13207_T435/s13207_T435.v,/Users/reza/Downloads/TRIT-TS/s15850_T425/s15850_T425.v,/Users/reza/Downloads/TRIT-TS/s15850_T617/s15850_T617.v,/Users/reza/Downloads/TRIT-TS/s13207_T403/s13207_T403.v,/Users/reza/Downloads/TRIT-TS/s15850_T413/s15850_T413.v,/Users/reza/Downloads/TRIT-TS/s15850_T414/s15850_T414.v,/Users/reza/Downloads/TRIT-TS/s13207_T404/s13207_T404.v,/Users/reza/Downloads/TRIT-TS/s15850_T619/s15850_T619.v,/Users/reza/Downloads/TRIT-TS/s13207_T609/s13207_T609.v,/Users/reza/Downloads/TRIT-TS/s15850_T440/s15850_T440.v,/Users/reza/Downloads/TRIT-TS/s13207_T450/s13207_T450.v,/Users/reza/Downloads/TRIT-TS/s13207_T468/s13207_T468.v,/Users/reza/Downloads/TRIT-TS/s15850_T478/s15850_T478.v,/Users/reza/Downloads/TRIT-TS/s13207_T457/s13207_T457.v,/Users/reza/Downloads/TRIT-TS/s15850_T447/s15850_T447.v,/Users/reza/Downloads/TRIT-TS/s13207_T461/s13207_T461.v,/Users/reza/Downloads/TRIT-TS/s15850_T485/s15850_T485.v,/Users/reza/Downloads/TRIT-TS/s15850_T471/s15850_T471.v,/Users/reza/Downloads/TRIT-TS/s15850_T449/s15850_T449.v,/Users/reza/Downloads/TRIT-TS/s13207_T459/s13207_T459.v,/Users/reza/Downloads/TRIT-TS/s15850_T476/s15850_T476.v,/Users/reza/Downloads/TRIT-TS/s15850_T482/s15850_T482.v,/Users/reza/Downloads/TRIT-TS/s13207_T466/s13207_T466.v,/Users/reza/Downloads/TRIT-TS/s35932_T435/s35932_T435.v,/Users/reza/Downloads/TRIT-TS/s35932_T607/s35932_T607.v,/Users/reza/Downloads/TRIT-TS/s35932_T600/s35932_T600.v,/Users/reza/Downloads/TRIT-TS/s35932_T432/s35932_T432.v,/Users/reza/Downloads/TRIT-TS/s35932_T404/s35932_T404.v,/Users/reza/Downloads/TRIT-TS/s35932_T609/s35932_T609.v,/Users/reza/Downloads/TRIT-TS/s35932_T403/s35932_T403.v,/Users/reza/Downloads/TRIT-TS/s1423_T407/s1423_T407.v,/Users/reza/Downloads/TRIT-TS/s1423_T400/s1423_T400.v,/Users/reza/Downloads/TRIT-TS/s1423_T409/s1423_T409.v,/Users/reza/Downloads/TRIT-TS/s1423_T604/s1423_T604.v,/Users/reza/Downloads/TRIT-TS/s1423_T603/s1423_T603.v,/Users/reza/Downloads/TRIT-TS/s35932_T402/s35932_T402.v,/Users/reza/Downloads/TRIT-TS/s35932_T405/s35932_T405.v,/Users/reza/Downloads/TRIT-TS/s35932_T608/s35932_T608.v,/Users/reza/Downloads/TRIT-TS/s35932_T433/s35932_T433.v,/Users/reza/Downloads/TRIT-TS/s35932_T601/s35932_T601.v,/Users/reza/Downloads/TRIT-TS/s35932_T606/s35932_T606.v,/Users/reza/Downloads/TRIT-TS/s35932_T434/s35932_T434.v,/Users/reza/Downloads/TRIT-TS/s1423_T602/s1423_T602.v,/Users/reza/Downloads/TRIT-TS/s1423_T430/s1423_T430.v,/Users/reza/Downloads/TRIT-TS/s1423_T408/s1423_T408.v,/Users/reza/Downloads/TRIT-TS/s1423_T605/s1423_T605.v,/Users/reza/Downloads/TRIT-TS/s1423_T401/s1423_T401.v,/Users/reza/Downloads/TRIT-TS/s1423_T406/s1423_T406.v";
//            GatesExtracter extracter = new GatesExtracter(paths.split(","));
               
    }
    
    public static void parseVerilogFile(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new  FileReader(new File(path)));
        
        
//        File f1 = new File("/Users/reza/Desktop/tempnet.txt");
//        FileReader fr1  =  new  FileReader(f1);
//        BufferedReader br1 = new BufferedReader(fr1);
        String temp="";
        String mainTemp = "";
        Boolean loop = false;
        
        while((temp=br.readLine())!=null){
            temp = temp.trim(); // remove leading and trailing whitespace
            temp=temp.replaceAll("\\s+", " ");
            mainTemp += temp;
        }       
        String[] lines  =  mainTemp.split(";");
        parseLines(lines);
//        for(int i=0;i<lines.length;i++)
//            System.out.println(lines[i]);
        
//        String[] wires = mainTemp.split(",");
//        
//        for(int j=0;j<wires.length;j++)
//        {
//            wires[j] = wires[j].replaceAll("\\s+", "");
//        }
//        WiresArray = wires;
//        temp="";
//        mainTemp = "";
//        while((temp=br1.readLine())!=null){
//            mainTemp += temp;
//        } 
//        int i=0;
//        int iterator = 0;
//        int counter = 0;
//        String counterMemmory = "";
//        gateLevelNetList = mainTemp;
//        for (i=0;i<wires.length;i++)
//        {
//            iterator = ocCounter(mainTemp, wires[i]);
//            if(iterator > 0){
////                System.out.println(wires[i]+":"+iterator);
//                counterMemmory+=wires[i]+":"+iterator+",";
//                
//            }
//            counter+= iterator;
//        }
//        WiresCountArray  = counterMemmory.split(",");
//        totalWireUsageCount = counter;
//        br.close();
    
    }
    
    public static void parseLines(String[] lines){
        String tempInputs="";
        String tempOutputs="";
        String tempAssigns="";
        String tempWires="";
        String gateDefinitionsTemp="";
        String tempLine="";
        int inputcounter = 0;
        int outputcounter = 0;
        int assignscounter = 0;
        int wirescounter = 0;
        int gateCounter = 0;
                
        int temp=0;
        int location=0;
        for(int i=0;i<lines.length;i++){
            switch(getLineHead(lines[i])){
                case "input":
                    tempLine = lines[i];
                    if(tempLine.contains("["))
                    {
                      location = tempLine.indexOf("]")+1;
                      tempLine = tempLine.substring(location);
                      if(inputcounter==0){
                          tempInputs = tempLine.trim();
                          inputcounter++;
                      } else tempInputs+= "," + tempLine.trim();
                    } else {
                        location = tempLine.trim().indexOf(" ");
                        tempLine = tempLine.substring(location).trim();
                        if(inputcounter==0)
                        {   
                            tempInputs=tempLine;
                            inputcounter++;
                        } else tempInputs+="," +tempLine;
                    }
                    break;
                    
                case "output":
                    tempLine = lines[i];
                    if(tempLine.contains("["))
                    {
                      location = tempLine.indexOf("]")+1;
                      tempLine = tempLine.substring(location);
                      if(outputcounter==0){
                          tempOutputs = tempLine.trim();
                          outputcounter++;
                      } else tempOutputs+= "," + tempLine.trim();
                    } else {
                        location = tempLine.trim().indexOf(" ");
                        tempLine = tempLine.substring(location).trim();
                        if(outputcounter==0)
                        {   
                            tempOutputs=tempLine;
                            outputcounter++;
                        } else tempOutputs+="," +tempLine;
                    }
                    break;
                    
                case "wire":
                    tempLine = lines[i];
                    if(tempLine.contains("["))
                    {
                      location = tempLine.indexOf("]")+1;
                      tempLine = tempLine.substring(location);
                      if(wirescounter==0){
                          tempWires = tempLine.trim();
                          wirescounter++;
                      } else tempWires+= "," + tempLine.trim();
                    } else {
                        location = tempLine.trim().indexOf(" ");
                        tempLine = tempLine.substring(location).trim();
                        if(wirescounter==0)
                        {   
                            tempWires=tempLine;
                            wirescounter++;
                        } else tempWires+="," +tempLine;
                    }
                    break;
                    
                case "assign":
                    break;
                case "endmodule":
                    break;
                case "module":
                    break;
                case "`timescale":
                    break;
                case "comment":
                    break;
                default: 
                    if(gateCounter==0){
                            gateDefinitionsTemp=lines[i];
                            gateCounter++;
                    } else {
                            gateDefinitionsTemp+=";"+lines[i];
                            }
                    
                    break;
                
            }
        }
        gateLevelNetList = gateDefinitionsTemp;
        gatesDefinitionLines = gateDefinitionsTemp.split(";");
        primaryInputs = tempInputs.split(",");
        primaryOutputs = tempOutputs.split(",");
        WiresArray = tempWires.split(",");
        initGates();

    }
    
    public static String getLineHead(String line){ 
        String tempLineHead = line.split(" ")[0].trim();
        if(tempLineHead.indexOf("//")==0)
            tempLineHead="comment";
        return tempLineHead;
    }
    
    public static void getWiresUsageStatistics() throws IOException{
        File f = new File("/Users/reza/Desktop/wires.txt");
        FileReader fr  =  new  FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        
        File f1 = new File("/Users/reza/Desktop/tempnet.txt");
        FileReader fr1  =  new  FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        String temp="";
        String mainTemp = "";
        Boolean loop = false;
        
        while((temp=br.readLine())!=null){
            temp = temp.trim(); // remove leading and trailing whitespace
            temp=temp.replaceAll("\\s+", " ");
            mainTemp += temp;
           

        }        
        
        String[] wires = mainTemp.split(",");
        
        for(int j=0;j<wires.length;j++)
        {
            wires[j] = wires[j].replaceAll("\\s+", "");
        }
        WiresArray = wires;
        temp="";
        mainTemp = "";
        while((temp=br1.readLine())!=null){
            mainTemp += temp;
        } 
        int i=0;
        int iterator = 0;
        int counter = 0;
        String counterMemmory = "";
        gateLevelNetList = mainTemp;
        for (i=0;i<wires.length;i++)
        {
            iterator = ocCounter(mainTemp, wires[i]);
            if(iterator > 0){
//                System.out.println(wires[i]+":"+iterator);
                counterMemmory+=wires[i]+":"+iterator+",";
                
            }
            counter+= iterator;
        }
        WiresCountArray  = counterMemmory.split(",");
        totalWireUsageCount = counter;
        br.close();

    }
    
    public static int ocCounter(String context, String query){
        int number = 0;
        int loc = 0;
        int temp = 0;
        boolean start = false;
        boolean loop  = true;
        while(loop){
            if(start)
                loc = context.indexOf(query,temp+query.length());
            else{
                loc = context.indexOf(query,temp);
                start = true;
            }
            if(loc>=0){
                temp = loc;           
                if(context.charAt(temp-1)=='(' || context.charAt(temp-1)==' ')
                    if(context.charAt(temp+query.length())==')' || context.charAt(temp+query.length())==' ' || context.charAt(temp+query.length())=='[')
                        number++;

            }else{
                loop = false;
            }
        }
        
        return number;
        
    }
    
    public static void printWiresCountArray(){
        for(int i = 0 ; i< WiresCountArray.length; i++)
            System.out.println(WiresCountArray[i]);
    }
    
 
    public static void initGates(){

        String temp = "";
        String[] GateNameArray = new String[gatesDefinitionLines.length];
        
        ArrayList<String> GateNameArrayList = new ArrayList<String>();
                
        for(int j = 0; j< gatesDefinitionLines.length;j++){
            
            GateNameArray[j]= gatesDefinitionLines[j].split(" ")[0].trim();
            if(GateNameArrayList.indexOf(GateNameArray[j])<0)
                GateNameArrayList.add(GateNameArray[j]);
            
        }

        usedGateList = GateNameArrayList;
        
        
        
    }
    
}
