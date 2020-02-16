package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Not {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Not (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            if (value.charAt(0) == '\"')
                value = value.substring(1);
            if (!handler.isBool(value))
                throw new MuaException("'" + value + "'->Boolean format error!");
            return String.valueOf(!Boolean.parseBoolean(value));
        } catch (MuaException e) {
            throw e;
        }
    }

}
