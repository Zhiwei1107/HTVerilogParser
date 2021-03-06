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
          
          parseVerilogFile("/Users/reza/Desktop/Learneds/RS232-T1300/src/180nm/uart.v");
          String Trojans="	NAND4X1 U293(.A(n251),.B(n239),.C(n242),.D(n246),.Y(iXMIT_N_CTRL_1_));\n" +
"	NAND4X1 U294(.A(iXMIT_N28),.B(iXMIT_N27),.C(iXMIT_N26),.D(iXMIT_N25),.Y(iXMIT_N_CTRL_2_));\n" +
"	NAND4X1 U295(.A(iXMIT_N24),.B(iXMIT_xmit_ShiftRegH_7_),.C(iXMIT_xmit_ShiftRegH_6_),.D(iXMIT_xmit_ShiftRegH_5_),.Y(iXMIT_xmit_CTRL));\n" +
"	OR4X1   U296(.A(1'b0),.B(iXMIT_N_CTRL_1_),.C(iXMIT_N_CTRL_2_),.D(iXMIT_xmit_CTRL),.Y(iXMIT_CRTL));  \n" +
"	NAND4X1 U297(.A(iRECEIVER_next_state_2_),.B(iRECEIVER_state_0_),.C(iRECEIVER_state_1_),.D(iRECEIVER_state_2_),.Y(iRECEIVER_state_CTRL));\n" +
"	NAND4X1 U298(.A(iRECEIVER_N28),.B(iRECEIVER_N27),.C(iRECEIVER_N26),.D(iRECEIVER_N23),.Y(iRECEIVER_N_CTRL_1_));\n" +
"	NAND4X1 U299(.A(iRECEIVER_N22),.B(iRECEIVER_N21),.C(iRECEIVER_N20),.D(iRECEIVER_N19),.Y(iRECEIVER_N_CTRL_2_));\n" +
"	NAND4X1 U300(.A(iRECEIVER_N18),.B(iRECEIVER_N17),.C(iRECEIVER_bitCell_cntrH_0_),.D(iRECEIVER_bitCell_cntrH_1_),.Y(iRECEIVER_bitCell_CTRL));\n" +
"	OR4X1   U301(.A(iRECEIVER_state_CTRL),.B(iRECEIVER_N_CTRL_1_),.C(iRECEIVER_N_CTRL_2_),.D(iRECEIVER_bitCell_CTRL),.Y(iRECEIVER_CTRL));\n" +
"	OR2X1   U302(.A(iXMIT_CRTL),.B(iRECEIVER_CTRL),.Y(iCTRL));\n" +
"	AND2X1  U303(.A(iCTRL),.B(xmit_doneH_temp),.Y(xmit_doneH));\n" +
"	AND2X1  U304(.A(iCTRL),.B(rec_dataH_rec_0_temp),.Y(rec_dataH_rec[0]));\n" +
"	AND2X1  U305(.A(iCTRL),.B(iXMIT_state_1_temp),.Y(iXMIT_state_1_));";
          GateParser parser = new GateParser(gatesDefinitionLines,WiresArray,primaryInputs,primaryOutputs,Trojans);
          parser.processInOuts();
          
//            String paths="/Users/reza/Desktop/Learneds/RS232-T1300/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1400/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1000/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1200/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1600/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1100/src/180nm/uart.v,/Users/reza/Desktop/Learneds/RS232-T1500/src/180nm/uart.v,/Users/reza/Desktop/Learneds/s15850-T100/src/TjIn/s15850_scan.v,/Users/reza/Desktop/Learneds/s38417-T100/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s35932-T300/src/TjIn/s35932_scan.v,/Users/reza/Desktop/Learneds/s38417-T200/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s35932-T200/src/TjIn/s35932_scan.v,/Users/reza/Desktop/Learneds/s38417-T300/src/TjIn/s38417_scan.v,/Users/reza/Desktop/Learneds/s38584-T100/src/TjIn/s38584_scan.v,/Users/reza/Desktop/Learneds/s35932-T100/src/TjIn/s35932_scan.v";
//            paths+=",/Users/reza/Downloads/TRIT-TS/s35932_T412/s35932_T412.v,/Users/reza/Downloads/TRIT-TS/s35932_T618/s35932_T618.v,/Users/reza/Downloads/TRIT-TS/s35932_T415/s35932_T415.v,/Users/reza/Downloads/TRIT-TS/s35932_T611/s35932_T611.v,/Users/reza/Downloads/TRIT-TS/s35932_T423/s35932_T423.v,/Users/reza/Downloads/TRIT-TS/s35932_T424/s35932_T424.v,/Users/reza/Downloads/TRIT-TS/s35932_T616/s35932_T616.v,/Users/reza/Downloads/TRIT-TS/s1423_T420/s1423_T420.v,/Users/reza/Downloads/TRIT-TS/s1423_T612/s1423_T612.v,/Users/reza/Downloads/TRIT-TS/s1423_T615/s1423_T615.v,/Users/reza/Downloads/TRIT-TS/s1423_T427/s1423_T427.v,/Users/reza/Downloads/TRIT-TS/s1423_T418/s1423_T418.v,/Users/reza/Downloads/TRIT-TS/s1423_T411/s1423_T411.v,/Users/reza/Downloads/TRIT-TS/s35932_T441/s35932_T441.v,/Users/reza/Downloads/TRIT-TS/s1423_T416/s1423_T416.v,/Users/reza/Downloads/TRIT-TS/s1423_T429/s1423_T429.v,/Users/reza/Downloads/TRIT-TS/s35932_T617/s35932_T617.v,/Users/reza/Downloads/TRIT-TS/s35932_T425/s35932_T425.v,/Users/reza/Downloads/TRIT-TS/s35932_T422/s35932_T422.v,/Users/reza/Downloads/TRIT-TS/s35932_T610/s35932_T610.v,/Users/reza/Downloads/TRIT-TS/s35932_T619/s35932_T619.v,/Users/reza/Downloads/TRIT-TS/s35932_T414/s35932_T414.v,/Users/reza/Downloads/TRIT-TS/s35932_T413/s35932_T413.v,/Users/reza/Downloads/TRIT-TS/s1423_T417/s1423_T417.v,/Users/reza/Downloads/TRIT-TS/s1423_T428/s1423_T428.v,/Users/reza/Downloads/TRIT-TS/s35932_T440/s35932_T440.v,/Users/reza/Downloads/TRIT-TS/s1423_T410/s1423_T410.v,/Users/reza/Downloads/TRIT-TS/s1423_T426/s1423_T426.v,/Users/reza/Downloads/TRIT-TS/s1423_T614/s1423_T614.v,/Users/reza/Downloads/TRIT-TS/s1423_T419/s1423_T419.v,/Users/reza/Downloads/TRIT-TS/s1423_T613/s1423_T613.v,/Users/reza/Downloads/TRIT-TS/s1423_T421/s1423_T421.v,/Users/reza/Downloads/TRIT-TS/s13207_T610/s13207_T610.v,/Users/reza/Downloads/TRIT-TS/s13207_T422/s13207_T422.v,/Users/reza/Downloads/TRIT-TS/s15850_T432/s15850_T432.v,/Users/reza/Downloads/TRIT-TS/s15850_T600/s15850_T600.v,/Users/reza/Downloads/TRIT-TS/s15850_T607/s15850_T607.v,/Users/reza/Downloads/TRIT-TS/s15850_T435/s15850_T435.v,/Users/reza/Downloads/TRIT-TS/s13207_T425/s13207_T425.v,/Users/reza/Downloads/TRIT-TS/s13207_T617/s13207_T617.v,/Users/reza/Downloads/TRIT-TS/s15850_T403/s15850_T403.v,/Users/reza/Downloads/TRIT-TS/s13207_T413/s13207_T413.v,/Users/reza/Downloads/TRIT-TS/s13207_T619/s13207_T619.v,/Users/reza/Downloads/TRIT-TS/s15850_T609/s15850_T609.v,/Users/reza/Downloads/TRIT-TS/s13207_T414/s13207_T414.v,/Users/reza/Downloads/TRIT-TS/s15850_T404/s15850_T404.v,/Users/reza/Downloads/TRIT-TS/s13207_T440/s13207_T440.v,/Users/reza/Downloads/TRIT-TS/s15850_T450/s15850_T450.v,/Users/reza/Downloads/TRIT-TS/s15850_T457/s15850_T457.v,/Users/reza/Downloads/TRIT-TS/s13207_T447/s13207_T447.v,/Users/reza/Downloads/TRIT-TS/s15850_T468/s15850_T468.v,/Users/reza/Downloads/TRIT-TS/s13207_T478/s13207_T478.v,/Users/reza/Downloads/TRIT-TS/s13207_T485/s13207_T485.v,/Users/reza/Downloads/TRIT-TS/s15850_T461/s15850_T461.v,/Users/reza/Downloads/TRIT-TS/s13207_T471/s13207_T471.v,/Users/reza/Downloads/TRIT-TS/s13207_T476/s13207_T476.v,/Users/reza/Downloads/TRIT-TS/s15850_T466/s15850_T466.v,/Users/reza/Downloads/TRIT-TS/s13207_T482/s13207_T482.v,/Users/reza/Downloads/TRIT-TS/s13207_T449/s13207_T449.v,/Users/reza/Downloads/TRIT-TS/s15850_T459/s15850_T459.v,/Users/reza/Downloads/TRIT-TS/s15850_T608/s15850_T608.v,/Users/reza/Downloads/TRIT-TS/s13207_T618/s13207_T618.v,/Users/reza/Downloads/TRIT-TS/s15850_T405/s15850_T405.v,/Users/reza/Downloads/TRIT-TS/s13207_T415/s13207_T415.v,/Users/reza/Downloads/TRIT-TS/s13207_T412/s13207_T412.v,/Users/reza/Downloads/TRIT-TS/s15850_T402/s15850_T402.v,/Users/reza/Downloads/TRIT-TS/s13207_T616/s13207_T616.v,/Users/reza/Downloads/TRIT-TS/s13207_T424/s13207_T424.v,/Users/reza/Downloads/TRIT-TS/s15850_T434/s15850_T434.v,/Users/reza/Downloads/TRIT-TS/s15850_T606/s15850_T606.v,/Users/reza/Downloads/TRIT-TS/s15850_T601/s15850_T601.v,/Users/reza/Downloads/TRIT-TS/s15850_T433/s15850_T433.v,/Users/reza/Downloads/TRIT-TS/s13207_T423/s13207_T423.v,/Users/reza/Downloads/TRIT-TS/s13207_T611/s13207_T611.v,/Users/reza/Downloads/TRIT-TS/s15850_T467/s15850_T467.v,/Users/reza/Downloads/TRIT-TS/s13207_T483/s13207_T483.v,/Users/reza/Downloads/TRIT-TS/s13207_T477/s13207_T477.v,/Users/reza/Downloads/TRIT-TS/s15850_T458/s15850_T458.v,/Users/reza/Downloads/TRIT-TS/s13207_T448/s13207_T448.v,/Users/reza/Downloads/TRIT-TS/s13207_T470/s13207_T470.v,/Users/reza/Downloads/TRIT-TS/s13207_T484/s13207_T484.v,/Users/reza/Downloads/TRIT-TS/s15850_T460/s15850_T460.v,/Users/reza/Downloads/TRIT-TS/s13207_T446/s13207_T446.v,/Users/reza/Downloads/TRIT-TS/s15850_T456/s15850_T456.v,/Users/reza/Downloads/TRIT-TS/s13207_T479/s13207_T479.v,/Users/reza/Downloads/TRIT-TS/s15850_T469/s15850_T469.v,/Users/reza/Downloads/TRIT-TS/s15850_T451/s15850_T451.v,/Users/reza/Downloads/TRIT-TS/s13207_T441/s13207_T441.v,/Users/reza/Downloads/TRIT-TS/s15850_T473/s15850_T473.v,/Users/reza/Downloads/TRIT-TS/s13207_T463/s13207_T463.v,/Users/reza/Downloads/TRIT-TS/s15850_T487/s15850_T487.v,/Users/reza/Downloads/TRIT-TS/s15850_T480/s15850_T480.v,/Users/reza/Downloads/TRIT-TS/s13207_T464/s13207_T464.v,/Users/reza/Downloads/TRIT-TS/s15850_T474/s15850_T474.v,/Users/reza/Downloads/TRIT-TS/s15850_T489/s15850_T489.v,/Users/reza/Downloads/TRIT-TS/s13207_T452/s13207_T452.v,/Users/reza/Downloads/TRIT-TS/s15850_T442/s15850_T442.v,/Users/reza/Downloads/TRIT-TS/s15850_T445/s15850_T445.v,/Users/reza/Downloads/TRIT-TS/s13207_T455/s13207_T455.v,/Users/reza/Downloads/TRIT-TS/s15850_T411/s15850_T411.v,/Users/reza/Downloads/TRIT-TS/s13207_T401/s13207_T401.v,/Users/reza/Downloads/TRIT-TS/s13207_T406/s13207_T406.v,/Users/reza/Downloads/TRIT-TS/s15850_T416/s15850_T416.v,/Users/reza/Downloads/TRIT-TS/s13207_T439/s13207_T439.v,/Users/reza/Downloads/TRIT-TS/s15850_T429/s15850_T429.v,/Users/reza/Downloads/TRIT-TS/s13207_T430/s13207_T430.v,/Users/reza/Downloads/TRIT-TS/s13207_T602/s13207_T602.v,/Users/reza/Downloads/TRIT-TS/s15850_T612/s15850_T612.v,/Users/reza/Downloads/TRIT-TS/s15850_T420/s15850_T420.v,/Users/reza/Downloads/TRIT-TS/s15850_T427/s15850_T427.v,/Users/reza/Downloads/TRIT-TS/s15850_T615/s15850_T615.v,/Users/reza/Downloads/TRIT-TS/s13207_T605/s13207_T605.v,/Users/reza/Downloads/TRIT-TS/s13207_T437/s13207_T437.v,/Users/reza/Downloads/TRIT-TS/s15850_T418/s15850_T418.v,/Users/reza/Downloads/TRIT-TS/s13207_T408/s13207_T408.v,/Users/reza/Downloads/TRIT-TS/s13207_T454/s13207_T454.v,/Users/reza/Downloads/TRIT-TS/s15850_T444/s15850_T444.v,/Users/reza/Downloads/TRIT-TS/s15850_T488/s15850_T488.v,/Users/reza/Downloads/TRIT-TS/s15850_T443/s15850_T443.v,/Users/reza/Downloads/TRIT-TS/s13207_T453/s13207_T453.v,/Users/reza/Downloads/TRIT-TS/s15850_T475/s15850_T475.v,/Users/reza/Downloads/TRIT-TS/s15850_T481/s15850_T481.v,/Users/reza/Downloads/TRIT-TS/s13207_T465/s13207_T465.v,/Users/reza/Downloads/TRIT-TS/s13207_T462/s13207_T462.v,/Users/reza/Downloads/TRIT-TS/s15850_T486/s15850_T486.v,/Users/reza/Downloads/TRIT-TS/s15850_T472/s15850_T472.v,/Users/reza/Downloads/TRIT-TS/s13207_T436/s13207_T436.v,/Users/reza/Downloads/TRIT-TS/s13207_T604/s13207_T604.v,/Users/reza/Downloads/TRIT-TS/s15850_T614/s15850_T614.v,/Users/reza/Downloads/TRIT-TS/s15850_T426/s15850_T426.v,/Users/reza/Downloads/TRIT-TS/s13207_T409/s13207_T409.v,/Users/reza/Downloads/TRIT-TS/s15850_T419/s15850_T419.v,/Users/reza/Downloads/TRIT-TS/s15850_T421/s15850_T421.v,/Users/reza/Downloads/TRIT-TS/s15850_T613/s15850_T613.v,/Users/reza/Downloads/TRIT-TS/s13207_T603/s13207_T603.v,/Users/reza/Downloads/TRIT-TS/s13207_T431/s13207_T431.v,/Users/reza/Downloads/TRIT-TS/s15850_T417/s15850_T417.v,/Users/reza/Downloads/TRIT-TS/s13207_T407/s13207_T407.v,/Users/reza/Downloads/TRIT-TS/s15850_T428/s15850_T428.v,/Users/reza/Downloads/TRIT-TS/s13207_T438/s13207_T438.v,/Users/reza/Downloads/TRIT-TS/s13207_T400/s13207_T400.v,/Users/reza/Downloads/TRIT-TS/s15850_T410/s15850_T410.v,/Users/reza/Downloads/TRIT-TS/s1423_T403/s1423_T403.v,/Users/reza/Downloads/TRIT-TS/s1423_T609/s1423_T609.v,/Users/reza/Downloads/TRIT-TS/s1423_T404/s1423_T404.v,/Users/reza/Downloads/TRIT-TS/s1423_T600/s1423_T600.v,/Users/reza/Downloads/TRIT-TS/s1423_T607/s1423_T607.v,/Users/reza/Downloads/TRIT-TS/s35932_T431/s35932_T431.v,/Users/reza/Downloads/TRIT-TS/s35932_T603/s35932_T603.v,/Users/reza/Downloads/TRIT-TS/s35932_T604/s35932_T604.v,/Users/reza/Downloads/TRIT-TS/s35932_T436/s35932_T436.v,/Users/reza/Downloads/TRIT-TS/s35932_T409/s35932_T409.v,/Users/reza/Downloads/TRIT-TS/s35932_T400/s35932_T400.v,/Users/reza/Downloads/TRIT-TS/s35932_T407/s35932_T407.v,/Users/reza/Downloads/TRIT-TS/s35932_T438/s35932_T438.v,/Users/reza/Downloads/TRIT-TS/s1423_T606/s1423_T606.v,/Users/reza/Downloads/TRIT-TS/s1423_T601/s1423_T601.v,/Users/reza/Downloads/TRIT-TS/s1423_T608/s1423_T608.v,/Users/reza/Downloads/TRIT-TS/s1423_T405/s1423_T405.v,/Users/reza/Downloads/TRIT-TS/s1423_T402/s1423_T402.v,/Users/reza/Downloads/TRIT-TS/s35932_T406/s35932_T406.v,/Users/reza/Downloads/TRIT-TS/s35932_T439/s35932_T439.v,/Users/reza/Downloads/TRIT-TS/s35932_T401/s35932_T401.v,/Users/reza/Downloads/TRIT-TS/s35932_T437/s35932_T437.v,/Users/reza/Downloads/TRIT-TS/s35932_T605/s35932_T605.v,/Users/reza/Downloads/TRIT-TS/s35932_T408/s35932_T408.v,/Users/reza/Downloads/TRIT-TS/s35932_T602/s35932_T602.v,/Users/reza/Downloads/TRIT-TS/s35932_T430/s35932_T430.v,/Users/reza/Downloads/TRIT-TS/s1423_T424/s1423_T424.v,/Users/reza/Downloads/TRIT-TS/s1423_T616/s1423_T616.v,/Users/reza/Downloads/TRIT-TS/s1423_T611/s1423_T611.v,/Users/reza/Downloads/TRIT-TS/s1423_T423/s1423_T423.v,/Users/reza/Downloads/TRIT-TS/s1423_T415/s1423_T415.v,/Users/reza/Downloads/TRIT-TS/s1423_T618/s1423_T618.v,/Users/reza/Downloads/TRIT-TS/s1423_T412/s1423_T412.v,/Users/reza/Downloads/TRIT-TS/s35932_T442/s35932_T442.v,/Users/reza/Downloads/TRIT-TS/s35932_T429/s35932_T429.v,/Users/reza/Downloads/TRIT-TS/s35932_T416/s35932_T416.v,/Users/reza/Downloads/TRIT-TS/s35932_T411/s35932_T411.v,/Users/reza/Downloads/TRIT-TS/s35932_T418/s35932_T418.v,/Users/reza/Downloads/TRIT-TS/s35932_T615/s35932_T615.v,/Users/reza/Downloads/TRIT-TS/s35932_T427/s35932_T427.v,/Users/reza/Downloads/TRIT-TS/s35932_T420/s35932_T420.v,/Users/reza/Downloads/TRIT-TS/s35932_T612/s35932_T612.v,/Users/reza/Downloads/TRIT-TS/s1423_T413/s1423_T413.v,/Users/reza/Downloads/TRIT-TS/s1423_T414/s1423_T414.v,/Users/reza/Downloads/TRIT-TS/s1423_T619/s1423_T619.v,/Users/reza/Downloads/TRIT-TS/s1423_T422/s1423_T422.v,/Users/reza/Downloads/TRIT-TS/s1423_T610/s1423_T610.v,/Users/reza/Downloads/TRIT-TS/s1423_T617/s1423_T617.v,/Users/reza/Downloads/TRIT-TS/s1423_T425/s1423_T425.v,/Users/reza/Downloads/TRIT-TS/s35932_T613/s35932_T613.v,/Users/reza/Downloads/TRIT-TS/s35932_T421/s35932_T421.v,/Users/reza/Downloads/TRIT-TS/s35932_T419/s35932_T419.v,/Users/reza/Downloads/TRIT-TS/s35932_T426/s35932_T426.v,/Users/reza/Downloads/TRIT-TS/s35932_T614/s35932_T614.v,/Users/reza/Downloads/TRIT-TS/s35932_T410/s35932_T410.v,/Users/reza/Downloads/TRIT-TS/s35932_T428/s35932_T428.v,/Users/reza/Downloads/TRIT-TS/s35932_T417/s35932_T417.v,/Users/reza/Downloads/TRIT-TS/s15850_T454/s15850_T454.v,/Users/reza/Downloads/TRIT-TS/s13207_T444/s13207_T444.v,/Users/reza/Downloads/TRIT-TS/s13207_T443/s13207_T443.v,/Users/reza/Downloads/TRIT-TS/s15850_T453/s15850_T453.v,/Users/reza/Downloads/TRIT-TS/s13207_T488/s13207_T488.v,/Users/reza/Downloads/TRIT-TS/s13207_T475/s13207_T475.v,/Users/reza/Downloads/TRIT-TS/s15850_T465/s15850_T465.v,/Users/reza/Downloads/TRIT-TS/s13207_T481/s13207_T481.v,/Users/reza/Downloads/TRIT-TS/s13207_T486/s13207_T486.v,/Users/reza/Downloads/TRIT-TS/s15850_T462/s15850_T462.v,/Users/reza/Downloads/TRIT-TS/s13207_T472/s13207_T472.v,/Users/reza/Downloads/TRIT-TS/s15850_T409/s15850_T409.v,/Users/reza/Downloads/TRIT-TS/s13207_T419/s13207_T419.v,/Users/reza/Downloads/TRIT-TS/s15850_T436/s15850_T436.v,/Users/reza/Downloads/TRIT-TS/s15850_T604/s15850_T604.v,/Users/reza/Downloads/TRIT-TS/s13207_T614/s13207_T614.v,/Users/reza/Downloads/TRIT-TS/s13207_T426/s13207_T426.v,/Users/reza/Downloads/TRIT-TS/s13207_T421/s13207_T421.v,/Users/reza/Downloads/TRIT-TS/s13207_T613/s13207_T613.v,/Users/reza/Downloads/TRIT-TS/s15850_T603/s15850_T603.v,/Users/reza/Downloads/TRIT-TS/s15850_T431/s15850_T431.v,/Users/reza/Downloads/TRIT-TS/s13207_T428/s13207_T428.v,/Users/reza/Downloads/TRIT-TS/s15850_T438/s15850_T438.v,/Users/reza/Downloads/TRIT-TS/s13207_T417/s13207_T417.v,/Users/reza/Downloads/TRIT-TS/s15850_T407/s15850_T407.v,/Users/reza/Downloads/TRIT-TS/s15850_T400/s15850_T400.v,/Users/reza/Downloads/TRIT-TS/s13207_T410/s13207_T410.v,/Users/reza/Downloads/TRIT-TS/s13207_T473/s13207_T473.v,/Users/reza/Downloads/TRIT-TS/s13207_T487/s13207_T487.v,/Users/reza/Downloads/TRIT-TS/s15850_T463/s15850_T463.v,/Users/reza/Downloads/TRIT-TS/s15850_T464/s15850_T464.v,/Users/reza/Downloads/TRIT-TS/s13207_T480/s13207_T480.v,/Users/reza/Downloads/TRIT-TS/s13207_T474/s13207_T474.v,/Users/reza/Downloads/TRIT-TS/s15850_T452/s15850_T452.v,/Users/reza/Downloads/TRIT-TS/s13207_T442/s13207_T442.v,/Users/reza/Downloads/TRIT-TS/s13207_T489/s13207_T489.v,/Users/reza/Downloads/TRIT-TS/s13207_T445/s13207_T445.v,/Users/reza/Downloads/TRIT-TS/s15850_T455/s15850_T455.v,/Users/reza/Downloads/TRIT-TS/s13207_T411/s13207_T411.v,/Users/reza/Downloads/TRIT-TS/s15850_T401/s15850_T401.v,/Users/reza/Downloads/TRIT-TS/s15850_T439/s15850_T439.v,/Users/reza/Downloads/TRIT-TS/s13207_T429/s13207_T429.v,/Users/reza/Downloads/TRIT-TS/s15850_T406/s15850_T406.v,/Users/reza/Downloads/TRIT-TS/s13207_T416/s13207_T416.v,/Users/reza/Downloads/TRIT-TS/s15850_T430/s15850_T430.v,/Users/reza/Downloads/TRIT-TS/s15850_T602/s15850_T602.v,/Users/reza/Downloads/TRIT-TS/s13207_T612/s13207_T612.v,/Users/reza/Downloads/TRIT-TS/s13207_T420/s13207_T420.v,/Users/reza/Downloads/TRIT-TS/s13207_T418/s13207_T418.v,/Users/reza/Downloads/TRIT-TS/s15850_T408/s15850_T408.v,/Users/reza/Downloads/TRIT-TS/s13207_T427/s13207_T427.v,/Users/reza/Downloads/TRIT-TS/s13207_T615/s13207_T615.v,/Users/reza/Downloads/TRIT-TS/s15850_T605/s15850_T605.v,/Users/reza/Downloads/TRIT-TS/s15850_T437/s15850_T437.v,/Users/reza/Downloads/TRIT-TS/s13207_T405/s13207_T405.v,/Users/reza/Downloads/TRIT-TS/s15850_T415/s15850_T415.v,/Users/reza/Downloads/TRIT-TS/s13207_T608/s13207_T608.v,/Users/reza/Downloads/TRIT-TS/s15850_T618/s15850_T618.v,/Users/reza/Downloads/TRIT-TS/s15850_T412/s15850_T412.v,/Users/reza/Downloads/TRIT-TS/s13207_T402/s13207_T402.v,/Users/reza/Downloads/TRIT-TS/s15850_T616/s15850_T616.v,/Users/reza/Downloads/TRIT-TS/s15850_T424/s15850_T424.v,/Users/reza/Downloads/TRIT-TS/s13207_T434/s13207_T434.v,/Users/reza/Downloads/TRIT-TS/s13207_T606/s13207_T606.v,/Users/reza/Downloads/TRIT-TS/s13207_T601/s13207_T601.v,/Users/reza/Downloads/TRIT-TS/s13207_T433/s13207_T433.v,/Users/reza/Downloads/TRIT-TS/s15850_T423/s15850_T423.v,/Users/reza/Downloads/TRIT-TS/s15850_T611/s15850_T611.v,/Users/reza/Downloads/TRIT-TS/s13207_T458/s13207_T458.v,/Users/reza/Downloads/TRIT-TS/s15850_T448/s15850_T448.v,/Users/reza/Downloads/TRIT-TS/s15850_T483/s15850_T483.v,/Users/reza/Downloads/TRIT-TS/s13207_T467/s13207_T467.v,/Users/reza/Downloads/TRIT-TS/s15850_T477/s15850_T477.v,/Users/reza/Downloads/TRIT-TS/s15850_T470/s15850_T470.v,/Users/reza/Downloads/TRIT-TS/s13207_T460/s13207_T460.v,/Users/reza/Downloads/TRIT-TS/s15850_T484/s15850_T484.v,/Users/reza/Downloads/TRIT-TS/s15850_T479/s15850_T479.v,/Users/reza/Downloads/TRIT-TS/s13207_T469/s13207_T469.v,/Users/reza/Downloads/TRIT-TS/s15850_T446/s15850_T446.v,/Users/reza/Downloads/TRIT-TS/s13207_T456/s13207_T456.v,/Users/reza/Downloads/TRIT-TS/s13207_T451/s13207_T451.v,/Users/reza/Downloads/TRIT-TS/s15850_T441/s15850_T441.v,/Users/reza/Downloads/TRIT-TS/s15850_T610/s15850_T610.v,/Users/reza/Downloads/TRIT-TS/s15850_T422/s15850_T422.v,/Users/reza/Downloads/TRIT-TS/s13207_T432/s13207_T432.v,/Users/reza/Downloads/TRIT-TS/s13207_T600/s13207_T600.v,/Users/reza/Downloads/TRIT-TS/s13207_T607/s13207_T607.v,/Users/reza/Downloads/TRIT-TS/s13207_T435/s13207_T435.v,/Users/reza/Downloads/TRIT-TS/s15850_T425/s15850_T425.v,/Users/reza/Downloads/TRIT-TS/s15850_T617/s15850_T617.v,/Users/reza/Downloads/TRIT-TS/s13207_T403/s13207_T403.v,/Users/reza/Downloads/TRIT-TS/s15850_T413/s15850_T413.v,/Users/reza/Downloads/TRIT-TS/s15850_T414/s15850_T414.v,/Users/reza/Downloads/TRIT-TS/s13207_T404/s13207_T404.v,/Users/reza/Downloads/TRIT-TS/s15850_T619/s15850_T619.v,/Users/reza/Downloads/TRIT-TS/s13207_T609/s13207_T609.v,/Users/reza/Downloads/TRIT-TS/s15850_T440/s15850_T440.v,/Users/reza/Downloads/TRIT-TS/s13207_T450/s13207_T450.v,/Users/reza/Downloads/TRIT-TS/s13207_T468/s13207_T468.v,/Users/reza/Downloads/TRIT-TS/s15850_T478/s15850_T478.v,/Users/reza/Downloads/TRIT-TS/s13207_T457/s13207_T457.v,/Users/reza/Downloads/TRIT-TS/s15850_T447/s15850_T447.v,/Users/reza/Downloads/TRIT-TS/s13207_T461/s13207_T461.v,/Users/reza/Downloads/TRIT-TS/s15850_T485/s15850_T485.v,/Users/reza/Downloads/TRIT-TS/s15850_T471/s15850_T471.v,/Users/reza/Downloads/TRIT-TS/s15850_T449/s15850_T449.v,/Users/reza/Downloads/TRIT-TS/s13207_T459/s13207_T459.v,/Users/reza/Downloads/TRIT-TS/s15850_T476/s15850_T476.v,/Users/reza/Downloads/TRIT-TS/s15850_T482/s15850_T482.v,/Users/reza/Downloads/TRIT-TS/s13207_T466/s13207_T466.v,/Users/reza/Downloads/TRIT-TS/s35932_T435/s35932_T435.v,/Users/reza/Downloads/TRIT-TS/s35932_T607/s35932_T607.v,/Users/reza/Downloads/TRIT-TS/s35932_T600/s35932_T600.v,/Users/reza/Downloads/TRIT-TS/s35932_T432/s35932_T432.v,/Users/reza/Downloads/TRIT-TS/s35932_T404/s35932_T404.v,/Users/reza/Downloads/TRIT-TS/s35932_T609/s35932_T609.v,/Users/reza/Downloads/TRIT-TS/s35932_T403/s35932_T403.v,/Users/reza/Downloads/TRIT-TS/s1423_T407/s1423_T407.v,/Users/reza/Downloads/TRIT-TS/s1423_T400/s1423_T400.v,/Users/reza/Downloads/TRIT-TS/s1423_T409/s1423_T409.v,/Users/reza/Downloads/TRIT-TS/s1423_T604/s1423_T604.v,/Users/reza/Downloads/TRIT-TS/s1423_T603/s1423_T603.v,/Users/reza/Downloads/TRIT-TS/s35932_T402/s35932_T402.v,/Users/reza/Downloads/TRIT-TS/s35932_T405/s35932_T405.v,/Users/reza/Downloads/TRIT-TS/s35932_T608/s35932_T608.v,/Users/reza/Downloads/TRIT-TS/s35932_T433/s35932_T433.v,/Users/reza/Downloads/TRIT-TS/s35932_T601/s35932_T601.v,/Users/reza/Downloads/TRIT-TS/s35932_T606/s35932_T606.v,/Users/reza/Downloads/TRIT-TS/s35932_T434/s35932_T434.v,/Users/reza/Downloads/TRIT-TS/s1423_T602/s1423_T602.v,/Users/reza/Downloads/TRIT-TS/s1423_T430/s1423_T430.v,/Users/reza/Downloads/TRIT-TS/s1423_T408/s1423_T408.v,/Users/reza/Downloads/TRIT-TS/s1423_T605/s1423_T605.v,/Users/reza/Downloads/TRIT-TS/s1423_T401/s1423_T401.v,/Users/reza/Downloads/TRIT-TS/s1423_T406/s1423_T406.v";
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
