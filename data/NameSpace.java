package data;

import java.util.*;

public class NameSpace {

    private boolean state;

    private WordSet localword;
    private WordSet globalword;

    private ListSet locallist;
    private ListSet globallist;

    public NameSpace () {
        state = false;
        localword = new WordSet();
        globalword = new WordSet();
        locallist = new ListSet();
        globallist = new ListSet();
        globalword.insert("pi", "3.14159");
        String[] content = {""};
        globallist.insert("run", content);
    }

    public NameSpace (WordSet globalword, ListSet globallist, String name, String[] content) {
        state = true;
        localword = new WordSet();
        this.globalword = globalword;
        locallist = new ListSet();
        this.globallist = globallist;
        locallist.insert(name, content);
    }

    public boolean getState () {
        return state;
    }

    public WordSet getLocalWord () {
        return localword;
    }

    public WordSet getGlobalWord () {
        return globalword;
    }

    public ListSet getLocalList () {
        return locallist;
    }

    public ListSet getGlobalList () {
        return globallist;
    }

    public boolean isInWordSet (String name) {
        return localword.isInWordSet(name) >= 0 || globalword.isInWordSet(name) >= 0;
    }

    public boolean isInListSet (String name) {
        return locallist.isInListSet(name) >= 0 || globallist.isInListSet(name) >= 0;
    }
    
}
