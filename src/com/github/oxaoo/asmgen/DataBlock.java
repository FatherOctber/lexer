package com.github.oxaoo.asmgen;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by Никита on 23.12.2015.
 */
public class DataBlock extends AbstractAsmBlock implements IAssembleable {

    private List<DataStructure> dataList = new ArrayList<DataStructure>();

    public DataBlock() {
        super(".DATA");
    }

    public void addDataStructure(String name, String type, String val) {
        DataStructure data = new DataStructure(name, type, val);
        if(!checkExist(data))
            dataList.add(data);
    }

    public void addDataStructure(DataStructure dataStructure) {
        if(!checkExist(dataStructure))
            dataList.add(dataStructure);
    }

    private boolean checkExist(DataStructure ds) {
        for( DataStructure dataStructure : dataList) {
            if(dataStructure.equals(ds))
                return true;
        }
        return false;
    }


    @Override
    public String assemble() {
        StringBuilder code = new StringBuilder();
        code.append(blockSeparator + ENDL);
        dataList.forEach(data -> {
            code.append(data.assemble());
        });
        code.append(ENDL);
        return code.toString();
    }

    static public class DataStructure implements  IAssembleable{
        private String type;
        private String name;
        private String value;

        public DataStructure(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof DataStructure) {
                DataStructure objectDs = (DataStructure) obj;
                if(objectDs.name.equals(this.name) && objectDs.type.equals(this.type)) return true;
            }
            return false;
        }

        @Override
        public String assemble() {
            return TAB+name+TAB+type+TAB+value+ENDL;
        }

    }

}
