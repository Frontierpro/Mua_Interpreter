package data;

import java.util.*;

public class InstSet {

    private Vector<String> instset;

    public InstSet () {
        instset = new Vector<String> ();
        instset.add("add");
        instset.add("and");
        instset.add("butfirst");
        instset.add("butlast");
        instset.add("div");
        instset.add("eq");
        instset.add("erall");
        instset.add("erase");
        instset.add("exit");
        instset.add("export");
        instset.add("first");
        instset.add("gt");
        instset.add("if");
        instset.add("int");
        instset.add("isbool");
        instset.add("isempty");
        instset.add("islist");
        instset.add("isname");
        instset.add("isnumber");
        instset.add("isword");
        instset.add("join");
        instset.add("last");
        instset.add("list");
        instset.add("load");
        instset.add("lt");
        instset.add("make");
        instset.add("mod");
        instset.add("mul");
        instset.add("not");
        instset.add("or");
        instset.add("output");
        instset.add("poall");
        instset.add("print");
        instset.add("random");
        instset.add("read");
        instset.add("readlist");
        instset.add("repeat");
        instset.add("run");
        instset.add("save");
        instset.add("sentence");
        instset.add("sqrt");
        instset.add("stop");
        instset.add("sub");
        instset.add("thing");
        instset.add("wait");
        instset.add("word");
        instset.add(":");
    }

    public boolean isInst (String str) {
        for (String inst : instset) {
            if (inst.equals(str))
                return true;
        }
        return false;
    }
    
}
