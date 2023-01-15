package advisor.playlist;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private static List<String> playList;


    static {
        playList = new ArrayList<>();
        playList.add("Walk Like A Badass");
        playList.add("Rage Beats");
        playList.add("Arb Mood Booster");
        playList.add("Sunday Stroll");
    }

    public static List<String> getPlayList() {
        return playList;
    }

}
