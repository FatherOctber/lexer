package com.github.oxaoo.asmgen;

import com.github.oxaoo.codegen.CTetrad;
import com.github.oxaoo.codegen.EOpcode;

import java.util.List;


/**
 * Created by Никита on 24.12.2015.
 */
public class TetradeParserAssist {
    protected DataBlock datas = new DataBlock();
    protected CodeBlock codes = new CodeBlock();
    protected int counter=1;

    public Pair<DataBlock, CodeBlock> parse(List<CTetrad> tetradas) {
        String code = "";
        for (CTetrad tetrad : tetradas) {
            if (EOpcode.ADD.equals(tetrad.getOpcode())) { // todo ADD routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    ADD eax,ebx" + "\n";
                code += "    push eax" + "\n";
                code += "    print udword$(eax),10,13\n";
                code += "    print \"Add\",13,10,0\n";
                code += "    pop eax" + "\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",eax" + "\n";
                }
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.MOV.equals(tetrad.getOpcode())) { // todo MOV routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",eax" + "\n";
                }
                code += "    push eax" + "\n";
                code += "    print udword$(eax),10,13\n";
                code += "    print \"Mov\",13,10,0\n";
                code += "    pop eax" + "\n";
                code += xor("eax", "eax");
            }

            if (EOpcode.BRL.equals(tetrad.getOpcode())) { // todo BRL routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    code += "    JMP " + permname+"\n";
                }
            }

            if (EOpcode.BF.equals(tetrad.getOpcode())) { // todo BF routine
                CTetrad op1 = tetrad.getOperand1();
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    CMP eax,1\n";
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    code += "    JB " + permname+"\n";
                }
            }

            if (EOpcode.DEFL.equals(tetrad.getOpcode())) { // todo DEFL routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    code += permname+": \n";
                }
            }

            if (EOpcode.NEG.equals(tetrad.getOpcode())) { // todo NEG routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    NEG eax\n";
                    code += "    MOV "+result+",eax\n";
                    code += "    push eax" + "\n";
                    code += "    print udword$(eax),10,13\n";
                    code += "    print \"NEG\",13,10,0\n";
                    code += "    pop eax" + "\n";
                }
                code += xor("eax", "eax");
            }

            if (EOpcode.MUL.equals(tetrad.getOpcode())) { // todo MUL routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    MUL ebx" + "\n";
                code += "    push eax" + "\n";
                code += "    print udword$(eax),10,13\n";
                code += "    print \"MUL\",13,10,0\n";
                code += "    pop eax" + "\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",eax" + "\n";
                }
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.DIV.equals(tetrad.getOpcode())) { // todo DIV routine
                code += xor("edx", "edx");
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    DIV ebx" + "\n";
                code += "    push eax" + "\n";
                code += "    print udword$(eax),10,13\n";
                code += "    print \"Div\",13,10,0\n";
                code += "    pop eax" + "\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",eax" + "\n";
                }
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
                code += xor("edx", "edx");
            }

            if (EOpcode.MOD.equals(tetrad.getOpcode())) { // todo MOD routine
                code += xor("edx", "edx");
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    DIV ebx" + "\n";
                code += "    push eax" + "\n";
                code += "    push edx" + "\n";
                code += "    print udword$(edx),10,13\n";
                code += "    print \"Mod\",13,10,0\n";
                code += "    pop edx" + "\n";
                code += "    pop eax" + "\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",edx" + "\n";
                }
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
                code += xor("edx", "edx");
            }

            if (EOpcode.SUB.equals(tetrad.getOpcode())) { // todo SUB routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    SUB eax,ebx" + "\n";
                code += "    push eax" + "\n";
                code += "    print udword$(eax),10,13\n";
                code += "    print \"Sub\",13,10,0\n";
                code += "    pop eax" + "\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",eax" + "\n";
                }
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.NOT.equals(tetrad.getOpcode())) { // todo NOT routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    cmp eax,0" + "\n";
                code += "    je _m_not"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "    jmp _end_not"+counter+"\n";
                //else
                code += "    _m_not"+counter+":   ";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }
                code += "    _end_not"+counter+":   ";
                code += xor("eax", "eax");
            }

            if (EOpcode.AND.equals(tetrad.getOpcode())) { // todo AND routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }

                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    cmp eax,1" + "\n";
                code += "    je _m_and"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "    jmp _end_and"+counter+"\n";
                //else
                code += "    _m_and"+counter+":   ";
                code += "    cmp ebx,1" + "\n";
                code += "    jne _end_and"+counter+"\n";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }
                code += "    _end_and"+counter+":   ";
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.OR.equals(tetrad.getOpcode())) { // todo OR routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }

                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }
                code += "    cmp eax,1" + "\n";
                code += "    jne _m_or"+counter+"\n";

                code += "    cmp ebx,0" + "\n";
                code += "    jne _m_or_2"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }
                code += "   jmp _end_or"+counter+"\n";
                //else
                code += "_m_or"+counter+":   ";
                code += "    cmp ebx,1" + "\n";
                code += "    je _end_or_1"+counter+"\n";

                code += "_m_or_2"+counter+":   ";
                Object res2= tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res2;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "   jmp _end_or"+counter+"\n";

                code += "_end_or_1"+counter+":   ";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }

                code += "_end_or"+counter+":   ";
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.GTS.equals(tetrad.getOpcode())) { // todo GTS routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }

                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }

                code += "    cmp eax,ebx" + "\n";
                code += "    jg _m_gts"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "    jmp _end_gts"+counter+"\n";
                //else
                code += "    _m_gts"+counter+":   ";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }

                code += "    _end_gts"+counter+":   ";
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.LTS.equals(tetrad.getOpcode())) { // todo LTS routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }

                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }

                code += "    cmp eax,ebx" + "\n";
                code += "    jl _m_lts"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "    jmp _end_lts"+counter+"\n";
                //else
                code += "    _m_lts"+counter+":   ";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }

                code += "    _end_lts"+counter+":   ";
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }

            if (EOpcode.ES.equals(tetrad.getOpcode())) { // todo ES routine
                CTetrad op1 = tetrad.getOperand1();
                if (op1.getResult() instanceof String) {
                    String permname = (String) op1.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + dataName + "\n";
                    }
                } else {
                    if (op1.getResult() instanceof Integer) {
                        Integer permname = (Integer) op1.getResult();
                        code += xor("eax", "eax");
                        code += "    MOV eax, " + String.valueOf(permname) + "\n";
                    }
                }

                CTetrad op2 = tetrad.getOperand2();
                if (op2.getResult() instanceof String) {
                    String permname = (String) op2.getResult();
                    if (permname.contains("#")) {
                        String dataName = "TERM" + permname.replaceAll("#", "_");
                        datas.addDataStructure(dataName, "DD", "0");
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + dataName + "\n";
                    }
                } else {
                    if (op2.getResult() instanceof Integer) {
                        Integer permname = (Integer) op2.getResult();
                        code += xor("ebx", "ebx");
                        code += "    MOV ebx, " + String.valueOf(permname) + "\n";
                    }
                }

                code += "    cmp eax,ebx" + "\n";
                code += "    je _m_es"+counter+"\n";
                //then
                Object res1 = tetrad.getResult();
                if (res1 instanceof String) {
                    String result = (String) res1;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",0" + "\n";
                }
                code += "    jmp _end_es"+counter+"\n";
                //else
                code += "    _m_es"+counter+":   ";
                Object res = tetrad.getResult();
                if (res instanceof String) {
                    String result = (String) res;
                    result = "TERM" + result.replaceAll("#", "_");
                    datas.addDataStructure(result, "DD", "0");
                    code += "    MOV " + result + ",1" + "\n";
                }

                code += "    _end_es"+counter+":   ";
                code += xor("eax", "eax");
                code += xor("ebx", "ebx");
            }
            counter++;
        }
        setFooter(code);
        return new Pair<DataBlock,CodeBlock>(datas,codes);
    }

    private void setFooter(String mainCode) {
        codes.setMainProcCode(mainCode +
        "    RET\n" +
        " ;------------------end routine---------------------------  \n" +
        " exit:\n" +
        "    push 0\n" +
        "    call ExitProcess ");
    }

    private String xor(String reg1, String reg2) {
        return "    XOR "+reg1+", "+reg2+"\n";
    }


}
