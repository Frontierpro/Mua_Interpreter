package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Join {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Join (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String left = handler.getNext(in, instbuffer);
            if (!handler.checkList(left))
                throw new MuaException("'" + left + "'->List format error!");
            left = left.substring(1, left.length() - 1).trim();
            String right = handler.getNext(in, instbuffer);
            if (right.charAt(0) == '\"')
                right = right.substring(1);
            if (right.length() < 1)
                throw new MuaException("Value cannot be empty!");
            return "[" + left + " " + right + "]";
        } catch (MuaException e) {
            throw e;
        }
    }

}
