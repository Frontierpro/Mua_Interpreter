package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Stop {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Stop (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            if (!state)
                throw new MuaException("'stop'->Only use in function!");
            return "null";
        } catch (MuaException e) {
            throw e;
        }
    }

}
