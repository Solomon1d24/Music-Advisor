package advisor.controller;

import advisor.Main;
import advisor.engine.AuthorizationEngine;
import advisor.exception.NotAuthorizedException;
import advisor.service.CategoriesService;
import advisor.service.PlayListService;
import advisor.service.Service;
import advisor.service.ServiceFactory;
import advisor.view.ServiceView;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ServiceController {

    private Service service;

    private AuthorizationEngine authorizationEngine;

    private ServiceView serviceView;

    public ServiceController(AuthorizationEngine authorizationEngine, ServiceView serviceView) {
        this.authorizationEngine = authorizationEngine;
        this.serviceView = serviceView;
    }
    /**
     * This is the entry point of the whole application, to start the usage of different services
     * */
    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            try {
                /**
                 * Need to delete the first condition to delete the exit option if we need to pass all the test in hyperskill
                 * */
                if (null != command && Objects.equals("exit", command)) {
                    serviceView.printExitView();
                    System.exit(0);
                } else if (null != command && Objects.equals("auth", command)) {
                    serviceView.printServiceView(authorizationEngine);
                } else if (Objects.equals("prev", command) || Objects.equals("next", command)) {
                    serviceView.updateView(command);
                    serviceView.printServiceView();
                } else {
                    serviceView.setResult(executeCommand(command));
                    serviceView.printServiceView();
                }
            } catch (RuntimeException e) {
                serviceView.printServiceView(e);
            }
        }
    }

    private List<String> executeCommand(String command) throws RuntimeException {
        if (!Main.AUTH && !Objects.equals("auth", command)) {
            throw new NotAuthorizedException("Please, provide access for application.");
        }
        if (command.contains("playlists")) {
            String category = command.substring("playlists ".length());
            service = ServiceFactory.get("playlists");
            CategoriesService categoriesService = (CategoriesService) ServiceFactory.get("categories");
            categoriesService.execute(authorizationEngine.getAccessToken());
            ((PlayListService) service).setCategoryMap(categoriesService.getCategoryMap());
            ((PlayListService) service).setCategory(category);
            service.execute(authorizationEngine.getAccessToken());
        } else {
            service = ServiceFactory.get(command);
            service.execute(authorizationEngine.getAccessToken());
        }
        return service.getResult();
    }
}
