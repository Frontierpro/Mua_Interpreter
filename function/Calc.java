package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Calc {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;
    private String express;
    private Vector<Integer> operand;
    private Vector<Character> operator; 

    public Calc (NameSpace namespace, InstSet instset, boolean state, String op) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
        express = op;
        operand = new Vector<Integer> ();
        operator = new Vector<Character> ();
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            initial(handler);
            String[] strarray = express.split(" ");
            if (strarray.length == 0 || strarray.length == 1 && strarray[0].equals(""))
                throw new MuaException("Express cannot be empty!");
            Vector<String> expressbuffer = new Vector<String> ();
            for (String str : strarray)
                expressbuffer.add(str);
            check(expressbuffer);
            return run(handler, in, expressbuffer);
        } catch (MuaException e) {
            throw e;
        }
    }

    public void initial (Handler handler) {
        for (int index = 0; index < express.length(); index++) {
            char c = express.charAt(index);
            if (handler.isOp(c) || c == '(' || c == ')') {
                String left = express.substring(0, index);
                String right = express.substring(index + 1);
                express = left + " " + c + " " + right;
                index += 2;
            }
        }
        express = express.replaceAll(" {2,}", " ");
        express = express.trim();
        express = express.substring(1, express.length() - 1).trim();
    }

    public void check (Vector<String> expressbuffer) {
        for (int index = 0; index < expressbuffer.size(); index++) {
            if (expressbuffer.get(index).equals("-")) {
                if (index == 0) {
                    expressbuffer.add(0, "0");
                    index++;
                }
                else {
                    String last = expressbuffer.get(index - 1);
                    if (last.equals("(") || last.equals("-") || last.equals("+")) {
                        expressbuffer.add(index, "0");
                        index++;
                    }
                }
            }
        }
    }

    public String run (Handler handler, Scanner in, Vector<String> expressbuffer) throws MuaException {
        try {
            while (expressbuffer.size() > 0) {
                String value = handler.getNext(in, expressbuffer);
                if (value.charAt(0) == '\"')
                    value = value.substring(1);
                if (handler.isInteger(value))
                    operand.add(Integer.valueOf(value));
                else if (handler.isOp(value.charAt(0))) {
                    char c = value.charAt(0);
                    if (c == '*' || c == '/' || c == '%') {
                        halfClear();
                        operator.add(c);
                    }
                    else {
                        fullClear();
                        operator.add(c);
                    }
                }
                else
                    throw new MuaException("'" + express + "'->Express element illegal!");
            }
            fullClear();
            if (operand.size() != 1 && operator.size() == 0)
                throw new MuaException("'" + express + "'->Express format illegal!");
            return String.valueOf(operand.get(0));
        } catch (MuaException e) {
            throw e;
        }
    }

    public void halfClear () throws MuaException {
        while (operator.size() > 0) {
            char c = operator.get(operator.size() - 1);
            if (c == '+' || c == '-')
                break;
            operator.remove(operator.size() - 1);
            if (operand.size() < 2)
                throw new MuaException("'" + express + "'->Express format illegal!");
            int right = operand.remove(operand.size() - 1);
            int left = operand.remove(operand.size() - 1);
            if (c == '*')
                operand.add(left * right);
            else if (c == '/')
                operand.add(left / right);
            else
                operand.add(left % right);
        }
    }

    public void fullClear () throws MuaException {
        while (operator.size() > 0) {
            char c = operator.remove(operator.size() - 1);
            if (operand.size() < 2)
                throw new MuaException("'" + express + "'->Express format illegal!");
            int right = operand.remove(operand.size() - 1);
            int left = operand.remove(operand.size() - 1);
            if (c == '+')
                operand.add(left + right);
            else if (c == '-')
                operand.add(left - right);
            else if (c == '*')
                operand.add(left * right);
            else if (c == '/')
                operand.add(left / right);
            else
                operand.add(left % right);
        }
    }

}
