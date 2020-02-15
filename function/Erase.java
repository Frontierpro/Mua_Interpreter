package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Erase {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Erase (NameSpace namespace, InstSet instset, boolean state) {
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
            String value = "";
            if (!handler.isName(name))
                throw new MuaException("'" + name + "'->Name not found in current namespace!");
            if (state && namespace.getLocalWord().isInWordSet(name) >= 0) {
                value = namespace.getLocalWord().getWordValue(name);
                namespace.getLocalWord().remove(name);
            }
            else if (namespace.getGlobalWord().isInWordSet(name) >= 0) {
                if (!state) {
                    value = namespace.getGlobalWord().getWordValue(name);
                    namespace.getGlobalWord().remove(name);
                }
                else
                    throw new MuaException("'" + name + "'->Word cannot be removed from global space!");
            }
            if (state && namespace.getLocalList().isInListSet(name) >= 0) {
                String[] content = namespace.getLocalList().getListContent(name);
                value = handler.arr2str(content);
                namespace.getLocalList().remove(name);
            }                
            else if (namespace.getGlobalList().isInListSet(name) >= 0) {
                if (!state) {
                    String[] content = namespace.getGlobalList().getListContent(name);
                    value = handler.arr2str(content);
                    namespace.getGlobalList().remove(name);
                }
                else
                    throw new MuaException("'" + name + "'->List cannot be removed from global space!");
            }
            return value;
        } catch (MuaException e) {
            throw e;
        }
    }

}
