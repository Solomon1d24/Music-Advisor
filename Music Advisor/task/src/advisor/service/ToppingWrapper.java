package advisor.service;

public class ToppingWrapper implements Service {

    protected Service service;

    public ToppingWrapper(Service service) {
        this.service = service;
    }
    @Override
    public String getService() {
        return service.getService();
    }
}
