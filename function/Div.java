package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Div {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Div (NameSpace namespace, InstSet instset, boolean state) {
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
            if (!handler.isInteger(left))
                throw new MuaException("'" + left + "'->Integer format error!");
            String right = handler.getNext(in, instbuffer);
            if (right.charAt(0) == '\"')
                right = right.substring(1);
            if (!handler.isInteger(right))
                throw new MuaException("'" + right + "'->Integer format error!");
            if (Integer.valueOf(right) == 0)
                throw new MuaException("Divisor cannot be zero!");
            return String.valueOf(Integer.valueOf(left) / Integer.valueOf(right));
        } catch (MuaException e) {
            throw e;
        }
    }

}
