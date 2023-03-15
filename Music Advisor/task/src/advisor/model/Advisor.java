package advisor.model;

import advisor.Main;
import advisor.engine.AuthorizationEngine;
import advisor.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Advisor {

    private Map<String, Service> serviceMap;

    private AuthorizationEngine authorizationEngine;

    public Advisor() {
        serviceMap = new HashMap<>();
        serviceMap.put("new", new NewService(new PlainService()));
        serviceMap.put("featured", new FeatureService(new PlainService()));
        serviceMap.put("categories", new CategoriesService(new PlainService()));
        serviceMap.put("playlists Mood", new PlayListService(new PlainService()));
        authorizationEngine = new AuthorizationEngine();
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (null != command && !Objects.equals("exit", command) && Main.AUTH && !Objects.equals("auth", command)) {
                System.out.println(serviceMap.get(command).getService());
            } else if (!Main.AUTH && Objects.equals("auth", command)) {
                authorizationEngine.setAccessCode();
                authorizationEngine.setAccessToken();
            } else if (null != command && Objects.equals("exit", command)) {
                System.out.println("---GOODBYE!---");
                System.exit(0);
            } else if (!Main.AUTH) {
                System.out.println("Please, provide access for application.");
            }
        }
    }
    ;
}
