package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Func {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;
    private String op;
    private String[] args, insts;
    private int argnum, instnum;

    public Func (NameSpace namespace, InstSet instset, boolean state, String op) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
        this.op = op;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            if (op.equals("run"))
                return runList(handler, in, instbuffer);
            String[] content = {""};
            if (namespace.getLocalList().isInListSet(op) >= 0)
                content = namespace.getLocalList().getListContent(op);
            else
                content = namespace.getGlobalList().getListContent(op);
            String value = "";
            for (String str : content)
                value += str + " ";
            value = value.trim();
            parseList(handler, value);
            Vector<String> funcbuffer = new Vector<String> ();
            for (int pos = 0; pos < argnum; pos++) {
                funcbuffer.add("make");
                funcbuffer.add("\"" + args[pos]);
                funcbuffer.add(handler.getNext(in, instbuffer));
            }
            for (int pos = 0; pos < instnum; pos++)
                funcbuffer.add(insts[pos]);
            String res = "null";
            Interpreter itp = new Interpreter(namespace, op, content);
            while (funcbuffer.size() > 0) {
                String inst = itp.getHandler().getNextStr(in, funcbuffer);
                try {
                    res = itp.getHandler().exec(in, funcbuffer, inst);
                } catch (MuaException e) {
                    throw e;
                }
            }
            return res;
        } catch (MuaException e) {
            throw e;
        }
    }

    public String runList (Handler handler, Scanner in, Vector<String> instbuffer) throws MuaException {
        try {
            String liststr = handler.getNext(in, instbuffer);
            if (!handler.checkList(liststr))
                throw new MuaException("'" + liststr + "'->List format error!");
            liststr = liststr.substring(1, liststr.length() - 1).trim();
            Vector<String> listbuffer = new Vector<String> ();
            String res = "";
            String[] listArray = liststr.split(" ");
            if (listArray.length == 1 && listArray[0].equals(""))
                return "null";
            for (String str : listArray)
                listbuffer.add(str);
            while (listbuffer.size() > 0) {
                String op = handler.getNextStr(in, listbuffer);
                res = handler.exec(in, listbuffer, op);
            }
            return res;
        } catch (MuaException e) {
            throw e;
        }
    }

    public void parseList (Handler handler, String str) throws MuaException {
        int num = 0, cnt = 0;
        String arg = "", inst = "";
        for (int pos = 0; pos < str.length(); pos++) {
            if (str.charAt(pos) == '[') {
                num++;
                if (num == 1)
                    cnt++;
            }
            if (cnt == 1 && num > 0)
                arg += str.charAt(pos);
            if (cnt == 2 && num > 0)
                inst += str.charAt(pos);
            if (str.charAt(pos) == ']')
                num--;
        }
        if (!handler.checkList(arg))
            throw new MuaException("'" + op + "'->Args list format error!");
        if (!handler.checkList(inst))
            throw new MuaException("'" + op + "'->Instructions list format error!");
        this.getArgs(arg);
        this.getInsts(inst);
    }

    public void getArgs (String arg) {
        arg =  arg.substring(1, arg.length() - 1).trim();
        args = arg.split(" ");
        argnum = args.length;
        if (argnum == 1 && args[0].equals(""))
            argnum = 0;
    }

    public void getInsts (String inst) {
        inst = inst.substring(1, inst.length() - 1).trim();
        insts = inst.split(" ");
        instnum = insts.length;
        if (instnum == 1 && insts[0].equals(""))
            instnum = 0;
    }

}
