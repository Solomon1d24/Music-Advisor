package advisor.service;

import advisor.Main;
import advisor.helper.JsonHelper;
import advisor.model.Album;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class implements the Service interface and contains a list of Albums.
 * This class has been applied with Singleton and Factory Pattern
 * @see Service
 * @see Album
 * */
public class NewService implements Service {

    private List<Album> albumList;

    private static NewService newService;

    public static synchronized NewService getInstance() {
        if (newService == null) {
            newService = new NewService();
        }
        return newService;
    }

    private NewService() {}

    /**
     * This method is to initialize the alnumList using the accesstoken got from authorization engine class
     * @param token
     * @see advisor.engine.AuthorizationEngine
     * */
    @Override
    public void execute(String token) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .uri(URI.create(Main.API_PATH + "/v1/browse/new-releases"))
                .GET()
                .build();
        if (albumList == null) {
            albumList = new ArrayList<>();
        }
        albumList.clear();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonObject responseObject =
                    JsonHelper.getField(httpResponse.body(), "albums").getAsJsonObject();
            for (JsonElement itemsElement : responseObject.get("items").getAsJsonArray()) {
                JsonObject itemObject = itemsElement.getAsJsonObject();
                String name = itemObject.get("name").getAsString();
                String link = itemObject
                        .get("external_urls")
                        .getAsJsonObject()
                        .get("spotify")
                        .getAsString();
                List<String> artistList = new ArrayList<>();
                for (JsonElement artistElement : itemObject.get("artists").getAsJsonArray()) {
                    artistList.add(artistElement.getAsJsonObject().get("name").getAsString());
                }
                albumList.add(new Album(name, link, artistList));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getResult() {
        return albumList.stream()
                .map(a -> a.getName() + "\n" + a.getArtist().toString() + "\n" + a.getLink() + "\n")
                .collect(Collectors.toList());
    }
}
