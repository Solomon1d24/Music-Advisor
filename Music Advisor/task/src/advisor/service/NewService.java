package advisor.service;

public class NewService extends ToppingWrapper {

    private final String comment = "---NEW RELEASES---\n"
            + "Mountains [Sia, Diplo, Labrinth]\n"
            + "Runaway [Lil Peep]\n"
            + "The Greatest Show [Panic! At The Disco]\n"
            + "All Out Life [Slipknot]";

    public NewService(Service service) {
        super(service);
    }

    @Override
    public String getService() {
        return super.getService() + comment;
    }
}
