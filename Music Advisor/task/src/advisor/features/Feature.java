package advisor.features;

import java.util.ArrayList;
import java.util.List;

public class Feature {

    private static List<String> featureList;

    static {
        featureList = new ArrayList<>();
        featureList.add("Mellow Morning");
        featureList.add("Wake Up and Smell the Coffee");
        featureList.add("Monday Motivation");
        featureList.add("Songs to Sing in the Shower");
    }

    public static List<String> getFeatureList() {
        return featureList;
    }


}
