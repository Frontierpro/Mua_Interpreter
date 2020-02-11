package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Mul {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Mul (NameSpace namespace, InstSet instset, boolean state) {
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
            if (!handler.isNumber(left))
                throw new MuaException("'" + left + "'->Number format error!");
            String right = handler.getNext(in, instbuffer);
            if (right.charAt(0) == '\"')
                right = right.substring(1);
            if (!handler.isNumber(right))
                throw new MuaException("'" + right + "'->Number format error!");
            return String.valueOf(Integer.valueOf(left) * Integer.valueOf(right));
        } catch (MuaException e) {
            throw e;
        }
    }

}
