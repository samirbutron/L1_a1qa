package src.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public RandomUtils (){

    }
    public String generateRandomString() {
        String string = RandomStringUtils.random(12,true,false);
        return string;
    }
}
