package advisor.release;

import java.util.ArrayList;
import java.util.List;

public class Release {
    private static List<String> releaseList;

    static {
        releaseList = new ArrayList<>();
        releaseList.add("Mountains [Sia, Diplo, Labrinth]");
        releaseList.add("Runaway [Lil Peep]");
        releaseList.add("The Greatest Show [Panic! At The Disco]");
        releaseList.add("All Out Life [Slipknot]");
    }


    public static List<String> getReleaseList() {
        return releaseList;
    }

}
