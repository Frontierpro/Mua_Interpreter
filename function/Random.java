package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Random {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Random (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String num = handler.getNext(in, instbuffer);
            if (!handler.isNumber(num))
                throw new MuaException("'" + num +"'->Number format error!");
            double ceil = Double.valueOf(num);
            return String.valueOf(Math.random() * ceil);
        } catch (MuaException e) {
            throw e;
        }
    }

}
