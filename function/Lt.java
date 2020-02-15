package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Lt {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Lt (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String left = handler.getNext(in, instbuffer);
            if (left.charAt(0) == '\"')
                left = left.substring(1);
            String right = handler.getNext(in, instbuffer);
            if (right.charAt(0) == '\"')
                right = right.substring(1);
            if (left.compareTo(right) < 0)
                return "true";
            else
                return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
