package advisor.service;

import advisor.Main;
import advisor.model.PlayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureService extends ToppingWrapper implements UsePlayList {

    private List<PlayList> playListList;

    public FeatureService(Service service) {
        super(service);
    }

    /**
     * The instance method inherited from the service interface
     * @return String of the response value
     * @see Service
     * */
    @Override
    public String getService() {
        return super.getService()
                + playListList.stream()
                        .map(s -> s.getName() + "\n" + s.getLink() + "\n")
                        .collect(Collectors.joining("\n"));
    }

    public List<PlayList> getPlayListList() {
        return playListList;
    }

    /**
     * This method is to initializate the playlist containing in the instance of FeatureService instance using the token
     * got from AuthorizationEngine instance
     * @param token
     * @see advisor.engine.AuthorizationEngine
     * */
    public void setPlayList(String token) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .uri(URI.create(Main.API_PATH + "/v1/browse/featured-playlists"))
                .GET()
                .build();
        if (playListList == null) {
            playListList = new ArrayList<>();
        }
        playlistInitialization(playListList,httpClient,httpRequest);
    }
}
