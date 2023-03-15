package advisor;

import advisor.engine.AuthorizationEngine;
import advisor.model.Advisor;
import advisor.service.*;

import java.util.Objects;

public class Main {

    public static boolean AUTH = false;

    public static void main(String[] args) {
        if (args.length > 1 && Objects.equals(args[0], "-access")) {
            AuthorizationEngine.REQUEST_URI = args[1];
        }

    }
}
