package src.utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Set;

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
