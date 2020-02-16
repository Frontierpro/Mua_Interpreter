package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Thing {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Thing (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String name = handler.getNext(in, instbuffer);
            if (name.charAt(0) != '\"')
                throw new MuaException("'" + name + "'->Should start with character \"!");
            if (name.length() < 2)
                throw new MuaException("Name cannot be empty!");
            name = name.substring(1);
            if (state && namespace.getLocalWord().isInWordSet(name) >= 0)
                return namespace.getLocalWord().getWordValue(name);
            else if (state && namespace.getLocalList().isInListSet(name) >= 0) {
                String[] content = namespace.getLocalList().getListContent(name);
                return handler.arr2str(content);
            }
            else if (namespace.getGlobalWord().isInWordSet(name) >= 0)
                return namespace.getGlobalWord().getWordValue(name);
            else if (namespace.getGlobalList().isInListSet(name) >= 0) {
                String[] content = namespace.getGlobalList().getListContent(name);
                return handler.arr2str(content);
            }
            else
                throw new MuaException("'" + name + "'->Name not found in current namespace!");
        } catch (MuaException e) {
            throw e;
        }
    }

}
