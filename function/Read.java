package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Read {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Read (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) {
        Handler handler = new Handler(namespace, instset, state);
        System.out.print(">>> ");
        String line = handler.getNextLine(in);
        String[] element = line.split(" ");
        return element[0];
    }

}
