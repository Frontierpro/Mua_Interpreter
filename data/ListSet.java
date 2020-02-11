package data;

import java.util.*;

public class ListSet {

    private Vector<list> listset;

    public ListSet () {
        listset = new Vector<list> ();
    }

    public void insert (String name, String[] content) {
        int index = isInListSet(name);
        if (index >= 0) {
            listset.get(index).setContent(content);
            return;
        }
        list l = new list(name, content);
        listset.add(l);
    }

    public void remove (String name) {
        int index = isInListSet(name);
        if (index >= 0)
            listset.remove(index);
    }

    public int isInListSet (String name) {
        for (int index = 0; index < listset.size(); index++) {
            if (listset.get(index).getName().equals(name))
                return index;
        }
        return -1;
    }

    public int getLength () {
        return listset.size();
    }

    public String getListName (int index) {
        return listset.get(index).getName();
    }

    public String[] getListContent (int index) {
        return listset.get(index).getContent();
    }

    public String[] getListContent (String name) {
        int index = isInListSet(name);
        if (index >= 0)
            return listset.get(index).getContent();
        String[] l = {""};
        return l;
    }

    public void clear () {
        listset.removeAllElements();
    }
}

class list {

    private String name;
    private String[] content;

    public list (String name, String[] content) {
        setName(name);
        setContent(content);
    }

    public String getName () {
        return name;
    }

    public String[] getContent () {
        return content;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setContent (String[] content) {
        this.content = content;
    }

}
