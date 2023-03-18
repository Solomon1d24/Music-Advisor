package advisor.service;

import advisor.model.PlayList;

import java.util.List;

public class PlayListService extends ToppingWrapper {

    private List<PlayList> playListList;

    public PlayListService(Service service) {
        super(service);
    }

    @Override
    public String getService() {
        String comment = "---MOOD PLAYLISTS---\n"
                + "Walk Like A Badass\n"
                + "Rage Beats\n"
                + "Arab Mood Booster\n"
                + "Sunday Stroll";
        return super.getService() + comment;
    }


    /**
     * Get the list contains playlists of the category and their links on Spotify by using the token
     * */
    public void setPlayListList(String token, String category){

    }

    public List<PlayList> getPlayListList() {
        return playListList;
    }
}
