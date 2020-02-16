package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Wait {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Wait (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String num = handler.getNext(in, instbuffer);
            if (!handler.isInteger(num))
                throw new MuaException("'" + num +"'->Integer format error!");
            int duration = Integer.valueOf(num);
            Thread.sleep(duration);
            return "null";
        } catch (MuaException e) {
            throw e;
        } catch (InterruptedException e) {
            throw new MuaException("Interrupted exception throw!");
        }
    }

}
