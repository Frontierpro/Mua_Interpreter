package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class IsList {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public IsList (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            if (value.length() < 2)
                return "false";
            if (value.charAt(0) == '[' && value.charAt(value.length() - 1) == ']')
                return "true";
            return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
