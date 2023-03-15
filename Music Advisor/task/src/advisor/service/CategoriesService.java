package advisor.service;

public class CategoriesService extends ToppingWrapper {

    private String comment = "---CATEGORIES---\n" + "Top Lists\n" + "Pop\n" + "Mood\n" + "Latin\n";

    public CategoriesService(Service service) {
        super(service);
    }

    @Override
    public String getService() {
        return super.getService() + comment;
    }
}
