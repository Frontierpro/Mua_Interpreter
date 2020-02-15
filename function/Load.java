package function;

import java.util.*;
import java.io.*;
import data.*;
import mua.*;

public class Load {

    private boolean state;
    private InstSet instset;
    private NameSpace namespace;

    public Load (NameSpace namespace, InstSet instset, boolean state) {
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
            BufferedReader bread = new BufferedReader(new FileReader(file));
            String value = "", line = "";
            while ((line = bread.readLine()) != null) {
                int index = line.indexOf("//");
                if (index >= 0)
                    line = line.replaceAll(line.substring(index), "");
                line = line.trim();
                line = line.replaceAll("\t", "").replaceAll("\r", "");
                line = line.replaceAll("\n", "").replaceAll("\f", "");
                line = line.replaceAll(" {2,}", " ");
                line = line.trim();
                if (!line.equals("") && !line.equals(" "))
                    value += line + " ";
            }
            value = value.trim();
            String[] content = value.split(" ");
            Vector<String> loadbuffer = new Vector<String> ();
            for (String str : content)
                loadbuffer.add(str);
            if (loadbuffer.size() == 0 || loadbuffer.size() == 1 && loadbuffer.get(0).equals(""))
                return "null";
            while (loadbuffer.size() > 0) {
                String op = handler.getNextStr(in, loadbuffer);
                String res = handler.exec(in, loadbuffer, op);
            }
            bread.close();
            return "null";
        } catch (MuaException e) {
            throw e;
        } catch (IOException e) {
            throw new MuaException("File io exception throw!");
        }
    }

}
