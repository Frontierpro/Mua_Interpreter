package mua;

import java.util.*;
import java.io.*;
import data.*;
import function.*;

public class Interpreter {

    private NameSpace namespace;
    private InstSet instset;
    private Handler handler;
    private boolean state;
    
    public Interpreter () {
        namespace = new NameSpace();
        instset = new InstSet();
        handler = new Handler(namespace, instset, false);
        state = false;
    }

    public Interpreter (NameSpace namespace, String name, String[] content) {
        WordSet globalword = namespace.getGlobalWord();
        ListSet globallist = namespace.getGlobalList();
        instset = new InstSet();
        this.namespace = new NameSpace(globalword, globallist, name, content);
        handler = new Handler(this.namespace, instset, true);
        state = true;
    }

    public String run () {
        Scanner in = new Scanner(System.in);
        Vector<String> instbuffer = new Vector<String> (); 
        String res;
        while (true) {
            String op = handler.getNextStr(in, instbuffer);
            try {
                res = handler.exec(in, instbuffer, op);
            } catch (MuaException e) {
                System.out.println(e.toString());
            } finally {
                in = new Scanner(System.in);
                instbuffer.removeAllElements();
            }
        }
    }

    public Handler getHandler () {
        return handler;
    }
    
}
