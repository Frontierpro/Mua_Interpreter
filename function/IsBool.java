package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class IsBool {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public IsBool (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            if (value.charAt(0) != '\"')
                value = value.substring(1);
            if (value.length() < 1)
                throw new MuaException("Bool value cannot be empty!");
            if (handler.isBool(value))
                return "true";
            return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
