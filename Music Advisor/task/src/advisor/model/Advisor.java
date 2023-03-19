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

    /**
     * This instance initialization block is used to initialize the instance variables of serviceMap and authorizationEngine
     * */
    {
        authorizationEngine = new AuthorizationEngine();
        CategoriesService categoriesService = new CategoriesService(new PlainService());
        FeatureService featureService = new FeatureService(new PlainService());
        NewService newService = new NewService(new PlainService());
        PlayListService playListService = new PlayListService(new PlainService());
        serviceMap = new HashMap<>();
        serviceMap.put("new", newService);
        serviceMap.put("featured", featureService);
        serviceMap.put("categories", categoriesService);
        serviceMap.put("playlists", playListService);
    }

    public Advisor() {}

    /**
     * This is the start method of the advisor instance and can be regarded as the start of the program.
     * */
    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (!Main.AUTH && Objects.equals("auth", command)) {
                authorizationEngine.setAccessCode();
                authorizationEngine.setAccessToken();
                ((NewService) serviceMap.get("new")).setAlbumList(authorizationEngine.getAccessToken());
                ((CategoriesService) serviceMap.get("categories"))
                        .setCategoryList(authorizationEngine.getAccessToken());
                ((FeatureService) serviceMap.get("featured")).setPlayList(authorizationEngine.getAccessToken());
                ((PlayListService) serviceMap.get("playlists"))
                        .setCategoryMap(((CategoriesService) serviceMap.get("categories")).getCategoryMap());
            } else if (null != command && Main.AUTH && !Objects.equals("exit", command)) {
                if (!command.contains("playlists")) {
                    System.out.println(serviceMap.get(command).getService());
                } else {
                    String category = command.substring("playlists ".length());
                    PlayListService service = (PlayListService) serviceMap.get("playlists");
                    service.setPlayListList(authorizationEngine.getAccessToken(), category);
                    System.out.println(service.getService() == null ? "" : service.getService());
                }
            } else if (null != command && Objects.equals("exit", command)) {
                System.out.println("---GOODBYE!---");
                System.exit(0);
            } else if (!Main.AUTH) {
                System.out.println("Please, provide access for application.");
            }
        }
    }
}
