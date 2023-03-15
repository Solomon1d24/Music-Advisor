package advisor.service;

public class PlayListService extends ToppingWrapper {

    private String comment = "---MOOD PLAYLISTS---\n"
            + "Walk Like A Badass\n"
            + "Rage Beats\n"
            + "Arab Mood Booster\n"
            + "Sunday Stroll";

    public PlayListService(Service service) {
        super(service);
    }

    @Override
    public String getService() {
        return super.getService() + comment;
    }
}
