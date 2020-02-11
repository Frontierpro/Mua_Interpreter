package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class And {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public And (NameSpace namespace, InstSet instset, boolean state) {
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
            if (!handler.isBool(left))
                throw new MuaException("'" + left + "'->Boolean format error!");
            String right = handler.getNext(in, instbuffer);
            if (right.charAt(0) == '\"')
                right = right.substring(1);
            if (!handler.isBool(right))
                throw new MuaException("'" + right + "'->Boolean format error!");
            return String.valueOf(Boolean.parseBoolean(left) && Boolean.parseBoolean(right));
        } catch (MuaException e) {
            throw e;
        }
    }

}
