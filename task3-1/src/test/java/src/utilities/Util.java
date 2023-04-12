package src.utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class Util {

    public String listToString(List<Object> list) {
        String stringList = list.toString();
        stringList = stringList.replace("[", "");
        stringList = stringList.replace("]", "");
        return stringList;
    }

    public String generateRandomString() {
        String string = RandomStringUtils.random(12,true,false);
        return string;
    }



}
