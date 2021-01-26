package mua;

import java.util.*;
import java.io.*;
import data.*;
import function.*;

public class Handler {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Handler (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer, String op) throws MuaException {
        String res = "";
        try {
            if (op.equals("make")) {
                Make make = new Make(namespace, instset, state);
                res = make.exec(in, instbuffer);
            }
            else if (op.equals("thing")) {
                Thing thing = new Thing(namespace, instset, state);
                res = thing.exec(in, instbuffer);
            }
            else if (op.charAt(0) == ':') {
                Thing thing = new Thing(namespace, instset, state);
                if (op.length() < 2)
                    throw new MuaException("Name cannot be empty!");
                instbuffer.add(0, "\"" + op.substring(1));
                res = thing.exec(in, instbuffer);
            }
            else if (op.equals("erase")) {
                Erase erase = new Erase(namespace, instset, state);
                res = erase.exec(in, instbuffer);
            }
            else if (op.equals("isname")) {
                IsName isname = new IsName(namespace, instset, state);
                res = isname.exec(in, instbuffer);
            }
            else if (op.equals("isnumber")) {
                IsNumber isnumber = new IsNumber(namespace, instset, state);
                res = isnumber.exec(in, instbuffer);
            }
            else if (op.equals("isword")) {
                IsWord isword = new IsWord(namespace, instset, state);
                res = isword.exec(in, instbuffer);
            }
            else if (op.equals("islist")) {
                IsList islist = new IsList(namespace, instset, state);
                res = islist.exec(in, instbuffer);
            }
            else if (op.equals("isname")) {
                IsBool isbool = new IsBool(namespace, instset, state);
                res = isbool.exec(in, instbuffer);
            }
            else if (op.equals("isempty")) {
                IsEmpty isempty = new IsEmpty(namespace, instset, state);
                res = isempty.exec(in, instbuffer);
            }
            else if (op.equals("print")) {
                Print print = new Print(namespace, instset, state);
                res = print.exec(in, instbuffer);
            }
            else if (op.equals("read")) {
                Read read = new Read(namespace, instset, state);
                res = read.exec(in, instbuffer);
            }
            else if (op.equals("readlist")) {
                ReadList readlist = new ReadList(namespace, instset, state);
                res = readlist.exec(in, instbuffer);
            }
            else if (op.equals("add")) {
                Add add = new Add(namespace, instset, state);
                res = add.exec(in, instbuffer);
            }
            else if (op.equals("sub")) {
                Sub sub = new Sub(namespace, instset, state);
                res = sub.exec(in, instbuffer);
            }
            else if (op.equals("mul")) {
                Mul mul = new Mul(namespace, instset, state);
                res = mul.exec(in, instbuffer);
            }
            else if (op.equals("div")) {
                Div div = new Div(namespace, instset, state);
                res = div.exec(in, instbuffer);
            }
            else if (op.equals("mod")) {
                Mod mod = new Mod(namespace, instset, state);
                res = mod.exec(in, instbuffer);
            }
            else if (op.equals("eq")) {
                Eq eq = new Eq(namespace, instset, state);
                res = eq.exec(in, instbuffer);
            }
            else if (op.equals("gt")) {
                Gt gt = new Gt(namespace, instset, state);
                res = gt.exec(in, instbuffer);
            }
            else if (op.equals("lt")) {
                Lt lt = new Lt(namespace, instset, state);
                res = lt.exec(in, instbuffer);
            }
            else if (op.equals("and")) {
                And and = new And(namespace, instset, state);
                res = and.exec(in, instbuffer);
            }
            else if (op.equals("or")) {
                Or or = new Or(namespace, instset, state);
                res = or.exec(in, instbuffer);
            }
            else if (op.equals("not")) {
                Not not = new Not(namespace, instset, state);
                res = not.exec(in, instbuffer);
            }
            else if (op.equals("word")) {
                Word w = new Word(namespace, instset, state);
                res = w.exec(in, instbuffer);
            }
            else if (op.equals("sentence")) {
                Sentence sentence = new Sentence(namespace, instset, state);
                res = sentence.exec(in, instbuffer);
            }
            else if (op.equals("list")) {
                function.List l = new function.List(namespace, instset, state);
                res = l.exec(in, instbuffer);
            }
            else if (op.equals("join")) {
                Join join = new Join(namespace, instset, state);
                res = join.exec(in, instbuffer);
            }
            else if (op.equals("first")) {
                First first = new First(namespace, instset, state);
                res = first.exec(in, instbuffer);
            }
            else if (op.equals("last")) {
                Last last = new Last(namespace, instset, state);
                res = last.exec(in, instbuffer);
            }
            else if (op.equals("butfirst")) {
                ButFirst butfirst = new ButFirst(namespace, instset, state);
                res = butfirst.exec(in, instbuffer);
            }
            else if (op.equals("butlast")) {
                ButLast butlast = new ButLast(namespace, instset, state);
                res = butlast.exec(in, instbuffer);
            }
            else if (op.equals("repeat")) {
                Repeat repeat = new Repeat(namespace, instset, state);
                res = repeat.exec(in, instbuffer);
            }
            else if (op.equals("if")) {
                If condition = new If(namespace, instset, state);
                res = condition.exec(in, instbuffer);
            }
            else if (op.equals("output")) {
                Output output = new Output(namespace, instset, state);
                res = output.exec(in, instbuffer);
            }
            else if (op.equals("stop")) {
                Stop stop = new Stop(namespace, instset, state);
                res = stop.exec(in, instbuffer);
            }
            else if (op.equals("export")) {
                Export export = new Export(namespace, instset, state);
                res = export.exec(in, instbuffer);
            }
            else if (op.equals("random")) {
                function.Random random = new function.Random(namespace, instset, state);
                res = random.exec(in, instbuffer);
            }
            else if (op.equals("int")) {
                Int integer = new Int(namespace, instset, state);
                res = integer.exec(in, instbuffer);
            }
            else if (op.equals("sqrt")) {
                Sqrt sqrt = new Sqrt(namespace, instset, state);
                res = sqrt.exec(in, instbuffer);
            }
            else if (op.equals("wait")) {
                Wait wait = new Wait(namespace, instset, state);
                res = wait.exec(in, instbuffer);
            }
            else if (op.equals("save")) {
                Save save = new Save(namespace, instset, state);
                res = save.exec(in, instbuffer);
            }
            else if (op.equals("load")) {
                Load load = new Load(namespace, instset, state);
                res = load.exec(in, instbuffer);
            }
            else if (op.equals("erall")) {
                Erall erall = new Erall(namespace, instset, state);
                res = erall.exec(in, instbuffer);
            }
            else if (op.equals("poall")) {
                Poall poall = new Poall(namespace, instset, state);
                res = poall.exec(in, instbuffer);
            }
            else if (op.equals("exit")) {
                Exit exit = new Exit(namespace, instset, state);
                res = exit.exec(in, instbuffer);
            }
            else if (namespace.isInListSet(op)) {
                Func func = new Func(namespace, instset, state, op);
                res = func.exec(in, instbuffer);
            }
            else if (op.charAt(0) == '(') {
                Calc calc = new Calc(namespace, instset, state, op);
                res = calc.exec(in, instbuffer);
            }
            else
                throw new MuaException("'" + op + "'->Instruction not found!");
        } catch (MuaException e) {
            throw e;
        }
        return res;
    }

    public String getNext (Scanner in, Vector<String> instbuffer) throws MuaException {
        String str = this.getNextStr(in, instbuffer);
        String res = "";
        try {
            if (instset.isInst(str) || namespace.isInListSet(str) || str.charAt(0) == ':')
                res = this.exec(in, instbuffer, str);
            else if (str.charAt(0) == '(' || str.charAt(0) == '[') {
                char left = str.charAt(0);
                char right = ')';
                if (left == '[')
                    right = ']';
                int count = 0;
                while (true) {
                    for (int pos = 0; pos < str.length(); pos++) {
                        if (str.charAt(pos) == left)
                            count++;
                        else if (str.charAt(pos) == right)
                            count--;
                    }
                    res += str;
                    if (count == 0) {
                        if (left == '(')
                            res = this.exec(in, instbuffer, res);
                        break;
                    }
                    res += " ";
                    str = getNextStr(in, instbuffer);
                }
            }
            else
                res = str;
        } catch (MuaException e) {
            throw e;
        }
        return res;
    }

    public String getNextStr (Scanner in, Vector<String> instbuffer) {
        if (instbuffer.size() > 0)
            return instbuffer.remove(0);
        System.out.print(">>> ");
        String line = getNextLine(in);
        String[] element = line.split(" ");
        for (String str : element)
            instbuffer.add(str);
        return instbuffer.remove(0);
    }

    public String getNextLine (Scanner in) {
        while (true) {
            String line = in.nextLine();
            int index = line.indexOf("//");
            if (index >= 0)
                line = line.replaceAll(line.substring(index), "");
            line = line.trim();
            line = line.replaceAll("\t", "").replaceAll("\r", "");
            line = line.replaceAll("\n", "").replaceAll("\f", "");
            line = line.replaceAll(" {2,}", " ");
            line = line.trim();
            if (!line.equals("") && !line.equals(" "))
                return line;
            System.out.print(">>> ");
        }
    }

    public boolean isName (String name) {
        if (namespace.isInWordSet(name) || namespace.isInListSet(name))
            return true;
        return false;
    }

    public boolean isInteger (String num) {
        int val = (int)num.charAt(0);
        if (num.length() == 1) {
            if (val >= 48 && val <= 57)
                return true;
            else
                return false;
        }
        else {
            if ((val < 48 || val > 57) && val != 45)
                return false;
            for (int pos = 1; pos < num.length(); pos++) {
                val = (int)num.charAt(pos);
                if (val < 48 || val > 57)
                    return false;
            }
            return true;
        }
    }

    public boolean isNumber (String num) {
        int val = (int)num.charAt(0);
        int cnt = 0;
        if (num.length() == 1) {
            if (val >= 48 && val <= 57)
                return true;
            else
                return false;
        }
        else {
            if ((val < 48 || val > 57) && val != 45)
                return false;
            for (int pos = 1; pos < num.length(); pos++) {
                val = (int)num.charAt(pos);
                if (val == 46 && pos < num.length() - 1)
                    cnt++;
                else if (val < 48 || val > 57)
                    return false;
            }
            if (cnt > 1)
                return false;
            return true;
        }
    }

    public boolean isBool (String str) {
        if (str.equals("true") || str.equals("false"))
            return true;
        return false;
    }

    public boolean isOp (char c) {
        if (c == '+')
            return true;
        if (c == '-')
            return true;
        if (c == '*')
            return true;
        if (c == '/')
            return true;
        if (c == '%')
            return true;
        return false;
    }

    public boolean checkList (String str) {
        int count = 0;
        if (str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']')
            return false;
        for (int pos = 0; pos < str.length(); pos++) {
            if (str.charAt(pos) == '[')
                count++;
            else if (str.charAt(pos) == ']')
                count--;
            if (count < 0)
                return false;
        }
        if (count == 0)
            return true;
        return false;
    }

    public String arr2str (String[] content) {
        String value = "[";
        for (String str : content)
            value += str + " ";
        value = value.trim() + "]";
        return value;
    }

}
