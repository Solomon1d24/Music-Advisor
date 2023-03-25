package advisor.service;

import advisor.Main;
import advisor.model.PlayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * This class implements the Service and UsePlayList interface and contains the playList of Songs in Spotify with a list.
 * This class has been applied with Singleton and Factory pattern
 * @see PlayList
 * @see Service
 * @see UsePlayList
 * */
public class FeatureService implements UsePlayList, Service {

    private List<PlayList> playListList;

    private static FeatureService featureService;

    public static synchronized FeatureService getInstance() {
        if (featureService == null) {
            featureService = new FeatureService();
        }
        return featureService;
    }

    private FeatureService() {}

    /**
     * This method is to initialize the playlist containing in the instance of FeatureService instance using the token
     * got from AuthorizationEngine instance
     * @param token
     * @see advisor.engine.AuthorizationEngine
     * */
    @Override
    public void execute(String token) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .uri(URI.create(Main.API_PATH + "/v1/browse/featured-playlists"))
                .GET()
                .build();
        if (playListList == null) {
            playListList = new ArrayList<>();
        }
        playlistInitialization(playListList, httpClient, httpRequest);
    }

    @Override
    public List<String> getResult() {
        return playListList.stream()
                .map(p -> p.getName() + "\n" + p.getLink() + "\n")
                .collect(Collectors.toList());
    }
}
