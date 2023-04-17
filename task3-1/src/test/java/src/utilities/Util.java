package src.utilities;

import java.util.List;

public class Util {

    public Util(){

    }
    public String listToString(List<Object> list) {
        String stringList = list.toString();
        stringList = stringList.replace("[", "");
        stringList = stringList.replace("]", "");
        return stringList;
    }

}
