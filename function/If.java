package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class If {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public If (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String condition = handler.getNext(in, instbuffer);
            if (!handler.isBool(condition))
                throw new MuaException("'" + condition + "'->Boolean format error!");
            String left = handler.getNext(in, instbuffer);
            if (!handler.checkList(left))
                throw new MuaException("'" + left + "->List format error!");
            left = left.substring(1, left.length() - 1);
            String right = handler.getNext(in, instbuffer);
            if (!handler.checkList(right))
                throw new MuaException("'" + right + "->List format error!");
            right = right.substring(1, right.length() - 1).trim();
            String branch = left;
            if (condition.equals("false"))
                branch = right;
            Vector<String> branchbuffer = new Vector<String> ();
            String[] branchlist = branch.split(" ");
            if (branchlist.length == 1 && branchlist[0].equals(""))
                return "null";
            for (String str : branchlist)
                branchbuffer.add(str);
            String res = "";
            while (branchbuffer.size() > 0) {
                String op = handler.getNextStr(in, branchbuffer);
                res = handler.exec(in, branchbuffer, op);
            }
            return res;
        } catch (MuaException e) {
            throw e;
        }
    }

}
