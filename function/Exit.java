package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Exit {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Exit (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) {
        Handler handler = new Handler(namespace, instset, state);
        System.out.println("Byebye, mua...");
        System.exit(0);
        return "null";
    }

}
