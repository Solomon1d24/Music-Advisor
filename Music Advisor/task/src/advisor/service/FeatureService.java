package advisor.service;

public class FeatureService extends ToppingWrapper {

    private final String comment = "---FEATURED---\n"
            + "Mellow Morning\n"
            + "Wake Up and Smell the Coffee\n"
            + "Monday Motivation\n"
            + "Songs to Sing in the Shower";

    public FeatureService(Service service) {
        super(service);
    }

    @Override
    public String getService() {
        return super.getService() + comment;
    }
}
