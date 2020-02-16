package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Save {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Save (NameSpace namespace, InstSet instset, boolean state) {
        this.state = state;
        this.namespace = namespace;
        this.instset = instset;
    }

    public String exec (Scanner in, Vector<String> instbuffer) throws MuaException {
        Handler handler = new Handler(namespace, instset, state);
        try {
            String file = handler.getNext(in, instbuffer);
            if (file.charAt(0) != '\"')
                throw new MuaException("'" + file + "'->Should start with character \"!");
            if (file.length() < 2)
                throw new MuaException("Name cannot be empty!");
            file = file.substring(1);
            File f = new File(file);
            FileOutputStream fstream = new FileOutputStream(f);
            OutputStreamWriter fwrite = new OutputStreamWriter(fstream, "utf-8");
            for (int index = 0; index < namespace.getGlobalWord().getLength(); index++) {
                String name = namespace.getGlobalWord().getWordName(index);
                String value = namespace.getGlobalWord().getWordValue(index);
                fwrite.append("make \"" + name + " " + value + "\n");
            }
            for (int index = 0; index < namespace.getLocalWord().getLength(); index++) {
                String name = namespace.getLocalWord().getWordName(index);
                String value = namespace.getLocalWord().getWordValue(index);
                fwrite.append("make \"" + name + " " + value + "\n");
            }
            for (int index = 0; index < namespace.getGlobalList().getLength(); index++) {
                String name = namespace.getGlobalList().getListName(index);
                String[] content = namespace.getGlobalList().getListContent(index);
                String value = handler.arr2str(content);
                fwrite.append("make \"" + name + " " + value + "\n");
            }
            for (int index = 0; index < namespace.getLocalList().getLength(); index++) {
                String name = namespace.getLocalList().getListName(index);
                String[] content = namespace.getLocalList().getListContent(index);
                String value = handler.arr2str(content);
                fwrite.append("make \"" + name + " " + value + "\n");
            }
            fwrite.close();
            fstream.close();
            return "null";
        } catch (MuaException e) {
            throw e;
        } catch (IOException e) {
            throw new MuaException("File io exception throw!");
        }
    }

}
