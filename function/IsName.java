package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class IsName {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public IsName (NameSpace namespace, InstSet instset, boolean state) {
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
            if (handler.isName(name))
                return "true";
            return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
