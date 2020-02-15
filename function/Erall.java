package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Erall {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Erall (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) {
        Handler handler = new Handler(namespace, instset, state);
        namespace.getLocalWord().clear();
        namespace.getGlobalWord().clear();
        namespace.getLocalList().clear();
        namespace.getGlobalList().clear();
        return "null";
    }

}
