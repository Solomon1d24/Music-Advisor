package advisor.functionality;

public class FunctionalityDecorator implements Functionality {

    private Functionality functionality;

    public FunctionalityDecorator(Functionality functionality) {
        this.functionality = functionality;
    }

    @Override
    public void show() {
        this.functionality.show();
    }
}
