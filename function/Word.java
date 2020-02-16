package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Word {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Word (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String left = handler.getNext(in, instbuffer);
            if (left.charAt(0) != '\"')
                throw new MuaException("'" + left + "'->Should start with character \"!");
            if (left.length() < 2)
                throw new MuaException("Value cannot be empty!");
            left = left.substring(1);
            String right = handler.getNext(in, instbuffer);
            if (!handler.isNumber(right) && ! handler.isBool(right) && right.charAt(0) != '\"')
                throw new MuaException("'" + right + "'->Should start with character \"!");
            if (!handler.isNumber(right) && ! handler.isBool(right) && right.length() < 2)
                throw new MuaException("Value cannot be empty!");
            if (!handler.isNumber(right) && ! handler.isBool(right))
                right = right.substring(1);
            return "\"" + left + right;
        } catch (MuaException e) {
            throw e;
        }
    }

}
