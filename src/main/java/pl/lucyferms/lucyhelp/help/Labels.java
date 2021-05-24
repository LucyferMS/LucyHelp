package pl.lucyferms.lucyhelp.help;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labels {
    private static HashMap<String, List<String>> labels = new HashMap<>();


    public static HashMap<String, List<String>> getLabelsMap(){
        return labels;
    }

    public static List<String> getLabels(){
        return new ArrayList<>(labels.keySet());
    }

    public static List<String> getAliases(String label){
        return labels.get(label);
    }

    public static void addLabel(String l, List<String> list){
        labels.put(l, list);
    }
}
