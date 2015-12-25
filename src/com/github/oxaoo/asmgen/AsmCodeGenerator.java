package com.github.oxaoo.asmgen;

import com.github.oxaoo.codegen.CTetrad;
import com.github.oxaoo.codegen.CTetradSimple;
import com.github.oxaoo.codegen.EOpcode;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 23.12.2015.
 */
public class AsmCodeGenerator implements ICodeGenerator {

    private static final String DESCRIPTION = ";RESULT OF ASSEMBLY\n";
    private HeaderBlock headerBlock = new HeaderBlock();
    private DataBlock dataBlock = new DataBlock();
    private CodeBlock codeBlock = new CodeBlock();


    @Override
    public void generate(String filename) throws Exception {
        PrintWriter fileWriter = new PrintWriter(filename, "UTF-8");
        parseCodeGen();
        fileWriter.println(DESCRIPTION);
        fileWriter.println(headerBlock.assemble());
        fileWriter.println(dataBlock.assemble());
        fileWriter.println(codeBlock.assemble());
        fileWriter.close();
    }

    private void parseCodeGen(/** code gen sequence **/) throws Exception {
        /**
         * for test purposes only
         */
//        dataBlock.addDataStructure("MESSAGE", "DB", "\"MESSAGE EXAMPLE!\",0");
//        codeBlock.setMainProcCode(";-----------------------start----------------------------\n" +
//                "    push offset MESSAGE\n" +
//                "    call StdOut\n" +
//                "    RET\n" +
//                " ;------------------end routine---------------------------  \n" +
//                " exit:\n" +
//                "    push 0\n" +
//                "    call ExitProcess \n");
        CTetrad t1 = new CTetrad(8);
        CTetrad t2 = new CTetrad(5);

        CTetrad tetr = new CTetrad(EOpcode.SUB, t1, t2, "#A");

        CTetrad t3 = new CTetrad(2);
        CTetrad tetr2 = new CTetrad(EOpcode.MOV, t3, null, "#A");
        List<CTetrad> tetradas = new ArrayList<CTetrad>();

        CTetrad t4 = new CTetrad("label1");
        CTetrad t41 = new CTetrad(false);
        CTetrad tetr3 = new CTetrad(EOpcode.BF, t4, t41, null);

        CTetrad t42 = new CTetrad(20);
        CTetrad t43 = new CTetrad(3);
        CTetrad tetr4 = new CTetrad(EOpcode.MOD, t42, t43, "#B");

        CTetrad t45 = new CTetrad(3);
        CTetrad t46 = new CTetrad(3);
        CTetrad tetr6 = new CTetrad(EOpcode.OR, t45, t46, "#B");

        tetradas.add(tetr);
        tetradas.add(tetr2);
        tetradas.add(tetr3);
        tetradas.add(tetr6);

        CTetradSimple ts = new CTetradSimple();
        Pair<DataBlock, CodeBlock> asm = new TetradeParserAssist().parse(ts.getTetrads());
        dataBlock = asm.getFirst();
        codeBlock = asm.getSecond();
    }
}
