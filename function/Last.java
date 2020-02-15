package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Last {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Last (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            if (value.charAt(0) == '[') {
                if (!handler.checkList(value))
                    throw new MuaException("'" + value + "'->List format error!");
                value = value.substring(1, value.length() - 1).trim();
                Vector<String> listbuffer = new Vector<String> ();
                String[] content = value.split(" ");
                for (String str : content)
                    listbuffer.add(str);
                if (listbuffer.size() == 0 && listbuffer.get(0).equals(""))
                    throw new MuaException("List cannot be empty!");
                String res = "";
                while (listbuffer.size() > 0)
                    res = handler.getNext(in, listbuffer);
                if (res.charAt(0) == '[')
                    return res;
                return "\"" + res;
            }
            if (!handler.isNumber(value) && !handler.isBool(value) && value.charAt(0) != '\"')
                throw new MuaException("'" + value + "'->Should start with character \"!");
            if (!handler.isNumber(value) && !handler.isBool(value) && value.length() < 2)
                throw new MuaException("Value cannot be empty!");
            if (!handler.isNumber(value) && !handler.isBool(value))
                value = value.substring(1);
            return "\"" + value.substring(value.length() - 1);
        } catch (MuaException e) {
            throw e;
        }
    }

}
