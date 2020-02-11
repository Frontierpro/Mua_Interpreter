package data;

import java.util.*;

public class WordSet {

    private Vector<word> wordset;

    public WordSet () {
        wordset = new Vector<word> ();
    }

    public void insert (String name, String value) {
        int index = isInWordSet(name);
        if (index >= 0) {
            wordset.get(index).setValue(value);
            return;
        }
        word w = new word(name, value);
        wordset.add(w);
    }

    public void remove (String name) {
        int index = isInWordSet(name);
        if (index >= 0)
            wordset.remove(index);
    }

    public int isInWordSet (String name) {
        for (int index = 0; index < wordset.size(); index++) {
            if (wordset.get(index).getName().equals(name))
                return index;
        }
        return -1;
    }

    public int getLength () {
        return wordset.size();
    }

    public String getWordName (int index) {
        return wordset.get(index).getName();
    }

    public String getWordValue (int index) {
        return wordset.get(index).getValue();
    }

    public String getWordValue (String name) {
        int index = isInWordSet(name);
        if (index >= 0)
            return wordset.get(index).getValue();
        return "";
    }

    public void clear () {
        wordset.removeAllElements();
    }
    
}

class word {

    private String name;
    private String value;

    public word (String name, String value) {
        setName(name);
        setValue(value);
    }

    public String getName () {
        return name;
    }

    public String getValue () {
        return value;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setValue (String value) {
        this.value = value;
    }
    
}
