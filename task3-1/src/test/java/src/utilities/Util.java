package src.utilities;

import java.util.List;

public class Util {

    public String listToString(List<Object> list){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<list.size(); i++){
            if (i > 0){
                sb.append(", ");
            }
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }
}
