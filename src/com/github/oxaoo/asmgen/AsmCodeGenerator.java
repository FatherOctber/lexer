package com.github.oxaoo.asmgen;

/**
 * Created by Никита on 23.12.2015.
 */
public class AsmCodeGenerator implements ICodeGenerator {

    private final String ASM_HEADER = ".386\n" +
            ".model flat, stdcall\n" +
            "option casemap :none\n" +
            "include \\masm32\\include\\masm32.inc\n" +
            "include \\masm32\\include\\kernel32.inc\n" +
            "include \\masm32\\macros\\macros.asm\n" +
            "includelib \\masm32\\lib\\masm32.lib\n" +
            "includelib \\masm32\\lib\\kernel32.lib";

    private DataBlock dataBlock = new DataBlock();
    private CodeBlock codeBlock = new CodeBlock();


    @Override
    public void generate(String filename) {

    }
}
