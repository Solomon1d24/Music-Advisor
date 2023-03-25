package advisor;

import advisor.controller.ServiceController;
import advisor.engine.AuthorizationEngine;
import advisor.view.ServiceView;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static boolean AUTH = false;
    public static String API_PATH = "https://api.spotify.com";
    public static int PAGE = 5;

    public static void main(String[] args) {
        if (args.length != 0) {
            List<String> argsList = Arrays.asList(args);
            int indexAccess = argsList.indexOf("-access");
            int indexResource = argsList.indexOf("-resource");
            int indexPage = argsList.indexOf("-page");
            if (indexAccess != -1) {
                AuthorizationEngine.REQUEST_URI = argsList.get(indexAccess + 1);
            }
            if (indexResource != 1) {
                API_PATH = argsList.get(indexResource + 1);
            }
            if (indexPage != -1) {
                PAGE = Integer.parseInt(argsList.get(indexPage + 1));
            }
        }

        ServiceView serviceView = new ServiceView();
        AuthorizationEngine authorizationEngine = new AuthorizationEngine();
        new ServiceController(authorizationEngine, serviceView).start();
    }
}
