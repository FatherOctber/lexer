package com.github.oxaoo.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 25.12.2015.
 */
public class CTetradSimple {
    List<CTetrad> tetrads = new ArrayList<>(8);

    public CTetradSimple() {
        CTetrad lab = new CTetrad("Lbegin");
        CTetrad lab2 = new CTetrad("Lend");

        tetrads.add(new CTetrad(EOpcode.MOV, new CTetrad(0), null, "#Peremennaya"));
        tetrads.add(new CTetrad(EOpcode.ADD, new CTetrad(3), tetrads.get(0)));
        tetrads.add(new CTetrad(EOpcode.MOV, tetrads.get(1), null, "#Perm2"));
        tetrads.add(new CTetrad(EOpcode.DEFL, lab));
        tetrads.add(new CTetrad(EOpcode.ADD, tetrads.get(0), new CTetrad(10)));
        tetrads.add(new CTetrad(EOpcode.MOV, tetrads.get(4), null, "#Peremennaya"));

        tetrads.add(new CTetrad(EOpcode.LTS, tetrads.get(0), new CTetrad(100)));
        tetrads.add(new CTetrad(EOpcode.BF, lab2, tetrads.get(6)));
        tetrads.add(new CTetrad(EOpcode.BRL, lab));
        tetrads.add(new CTetrad(EOpcode.DEFL, lab2));

        /**
         * tetrads.add(new CTetrad(EOpcode.MOV, new CTetrad(0), null, "#Peremennaya"));
         tetrads.add(new CTetrad(EOpcode.ADD, new CTetrad(3), tetrads.get(0)));
         tetrads.add(new CTetrad(EOpcode.MOV, tetrads.get(1), null, "#Perm2"));
         tetrads.add(new CTetrad(EOpcode.DEFL, lab));
         tetrads.add(new CTetrad(EOpcode.ADD, tetrads.get(0), new CTetrad(10)));
         tetrads.add(new CTetrad(EOpcode.LTS, tetrads.get(0), new CTetrad(100)));
         tetrads.add(new CTetrad(EOpcode.BF, lab2, tetrads.get(5)));
         tetrads.add(new CTetrad(EOpcode.BRL, lab));
         tetrads.add(new CTetrad(EOpcode.DEFL, lab2));
         */
    }

    public List<CTetrad> getTetrads() {
        return tetrads;
    }
}