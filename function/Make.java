package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Make {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Make (NameSpace namespace, InstSet instset, boolean state) {
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
            String value = handler.getNext(in, instbuffer);
            if (value.charAt(0) == '[') {
                if (!handler.checkList(value))
                    throw new MuaException("'" + value + "'->List format error!");
                value = value.substring(1, value.length() - 1).trim();
                String[] content = value.split(" ");
                if (state)
                    namespace.getLocalList().insert(name, content);
                else
                    namespace.getGlobalList().insert(name, content);
                return value;
            }
            else {
                if (state)
                    namespace.getLocalWord().insert(name, value);
                else
                    namespace.getGlobalWord().insert(name, value);
                return value;
            }
        } catch (MuaException e) {
            throw e;
        }
    }

}
