package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Print {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Print (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            if (value.charAt(0) == '\"')
                value = value.substring(1);
            else if (value.charAt(0) == '[') {
                if (!handler.checkList(value))
                    throw new MuaException("'" + value + "'->List format error!");
                value = value.substring(1, value.length() - 1).trim();
            }
            System.out.println(value);
            return value;
        } catch (MuaException e) {
            throw e;
        }
    }

}
