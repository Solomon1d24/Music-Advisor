package advisor.service;

import advisor.helper.JsonHelper;
import advisor.model.PlayList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public interface UsePlayList {

    /**
     * This method is used by the classes that contains the playlist and need to initialize it.
     * @param playListList
     * @param httpClient
     * @param httpRequest
     * @see FeatureService
     * @see PlayListService
     * */
    public default void playlistInitialization(
            List<PlayList> playListList, HttpClient httpClient, HttpRequest httpRequest) {
        playListList.clear();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (!JsonHelper.checkFieldExist(httpResponse.body(), "error")) {
                for (JsonElement itemsElement : JsonHelper.getField(httpResponse.body(), "playlists")
                        .getAsJsonObject()
                        .get("items")
                        .getAsJsonArray()) {
                    String link = itemsElement
                            .getAsJsonObject()
                            .get("external_urls")
                            .getAsJsonObject()
                            .get("spotify")
                            .getAsString();
                    String name = itemsElement.getAsJsonObject().get("name").getAsString();
                    playListList.add(new PlayList(name, link));
                }
            } else {
                JsonObject errorObject =
                        JsonHelper.getField(httpResponse.body(), "error").getAsJsonObject();
                String errorMessage = errorObject.get("message").getAsString();
                System.out.println(errorMessage);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
