package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Repeat {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Repeat (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String numstr = handler.getNext(in, instbuffer);
            if (numstr.charAt(0) == '\"')
                numstr = numstr.substring(1);
            if (!handler.isNumber(numstr))
                throw new MuaException("'" + numstr + "'->Number format error!");
            int num = Integer.valueOf(numstr);
            String liststr = handler.getNext(in, instbuffer);
            if (!handler.checkList(liststr))
                throw new MuaException("'" + liststr + "'->List format error!");
            liststr = liststr.substring(1, liststr.length() - 1).trim();
            Vector<String> listbuffer = new Vector<String> ();
            String res = "";
            for (int cnt = 0; cnt < num; cnt++) {
                String[] listarray = liststr.split(" ");
                if (listarray.length == 1 && listarray[0].equals(""))
                    return "null";
                for (String str : listarray)
                    listbuffer.add(str);
                while (listbuffer.size() > 0) {
                    String op = handler.getNextStr(in, listbuffer);
                    res = handler.exec(in, listbuffer, op);
                }
                listbuffer.removeAllElements();
            }
            return res;
        } catch (MuaException e) {
            throw e;
        }
    }

}
