package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Poall {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Poall (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        Vector<String> name = new Vector<String> ();
        for (int index = 0; index < namespace.getLocalWord().getLength(); index++)
            insert(name, namespace.getLocalWord().getWordName(index));
        for (int index = 0; index < namespace.getGlobalWord().getLength(); index++)
            insert(name, namespace.getGlobalWord().getWordName(index));
        for (int index = 0; index < namespace.getLocalList().getLength(); index++)
            insert(name, namespace.getLocalList().getListName(index));
        for (int index = 0; index < namespace.getGlobalList().getLength(); index++)
            insert(name, namespace.getGlobalList().getListName(index));
        System.out.println(name);
        return "null";
    }

    public void insert(Vector<String> name, String str) {
        int index = -1;
        for (index = 0; index < name.size(); index++) {
            if (str.equals(name.get(index)))
                break;
        }
        if (index == name.size())
            name.add(str);
    }

}
