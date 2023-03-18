package advisor;

import advisor.engine.AuthorizationEngine;
import advisor.model.Advisor;
import advisor.service.*;

import java.util.Objects;

public class Main {

    public static boolean AUTH = false;
    public static String API_PATH = "https://api.spotify.com";

    public static void main(String[] args) {
        if (args.length == 2 && Objects.equals(args[0], "-access")) {
            AuthorizationEngine.REQUEST_URI = args[1];
        } else if(args.length > 2 && Objects.equals(args[0],"-access") && Objects.equals(args[2],"-resource")) {
            AuthorizationEngine.REQUEST_URI = args[1];
            Main.API_PATH = args[3];
        }
        Advisor advisor = new Advisor();
        advisor.start();
    }
}
