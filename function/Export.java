package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Export {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Export (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            if (!state)
                throw new MuaException("'export'->Only use in function!");
            String name = handler.getNext(in, instbuffer);
            if (name.charAt(0) != '\"')
                throw new MuaException("'" + name + "'->Should start with character \"!");
            if (name.length() < 2)
                throw new MuaException("Name cannot be empty!");
            name = name.substring(1);
            String value = "";
            boolean flag = true;
            if (namespace.getLocalWord().isInWordSet(name) >= 0) {
                value = namespace.getLocalWord().getWordValue(name);
                namespace.getLocalWord().remove(name);
                namespace.getGlobalWord().insert(name, value);
                flag = false;
            }
            if (namespace.getLocalList().isInListSet(name) >= 0) {
                String[] content = namespace.getLocalList().getListContent(name);
                value = handler.arr2str(content);
                namespace.getLocalList().remove(name);
                namespace.getGlobalList().insert(name, content);
                flag = false;
            }
            if (flag)
                throw new MuaException("'" + name +"'->Name not found in current local space!");
            return value;
        } catch (MuaException e) {
            throw e;
        }
    }

}
