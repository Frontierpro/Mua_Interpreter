package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class IsEmpty {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public IsEmpty (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String value = handler.getNext(in, instbuffer);
            value = value.replaceAll(" ", "");
            if (value.equals("") || value.equals("\"") || value.equals("[]"))
                return "true";
            return "false";
        } catch (MuaException e) {
            throw e;
        }
    }

}
