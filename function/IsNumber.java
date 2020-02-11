package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class IsNumber {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public IsNumber (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String num = handler.getNext(in, instbuffer);
            if (num.charAt(0) == '\"')
                num = num.substring(1);
            if (num.length() < 1)
                throw new MuaException("Number cannot be empty!");
            if (handler.isNumber(num))
                return "true";
            return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
