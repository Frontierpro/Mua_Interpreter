package function

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class ReadList {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public ReadList (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        System.out.print(">>> ");
        String line = handler.getNextLine(in);
        return "[" + line + "]";
    }

}
