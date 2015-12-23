package com.github.oxaoo.asmgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 23.12.2015.
 */
public class CodeBlock extends AbstractAsmBlock implements IAssembleable {

    /**
     * only for Main proc
     * **/
    private Procedure mainProc = new Procedure("_MAIN");
    /**
     *  for other procs
     *  **/
    private List<Procedure> procList = new ArrayList<Procedure>();

    public CodeBlock() {
        super(".CODE");
    }

    public void addProcedure(String code, String name) {
        Procedure proc = new Procedure(name);
        proc.addCode(code);
        procList.add(proc);
    }

    @Override
    public String assemble() {
        StringBuilder code = new StringBuilder();
        code.append(blockSeparator + IAssembleable.ENDL);
        procList.forEach(proc -> {
            code.append(proc.assemble());
        });
        code.append(mainProc.assemble());
        return code.toString();
    }


    class Procedure implements IAssembleable{
        private final String startPointer = "PROC";
        private final String finalPointer = "ENDP";
        private String procName = "";
        private StringBuilder codeString = new StringBuilder();

        public Procedure(String name) {
            procName = name;
        }

        public String toString() {
            return codeString.toString();
        }

        public void addCode(String code) {
            codeString.append(code);
        }

        @Override
        public String assemble() {
            return toString();
        }
    }
}
