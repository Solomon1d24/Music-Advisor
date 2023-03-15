package advisor.service;

import advisor.Main;

public class LoginService implements Service {

    private Service service;

    private String message = "Please, provide access for application.";

    public LoginService(Service service) {
        this.service = service;
    }

    @Override
    public String getService() {
        if (!Main.AUTH) {
            return this.service.getService() + this.message;
        }
        return this.service.getService();
    }
}
