package advisor;

import advisor.engine.AuthorizationEngine;
import advisor.service.NewService;
import advisor.service.PlainService;

public class HttpSample {
    public static void main(String[] args) {

        AuthorizationEngine authorizationEngine = new AuthorizationEngine();
        authorizationEngine.setAccessCode();
        authorizationEngine.setAccessToken();
        String accessToken = authorizationEngine.getAccessToken();
        NewService newService = new NewService(new PlainService());
        newService.setAlbumList(accessToken);
        System.out.println(newService.getService());
    }
}
