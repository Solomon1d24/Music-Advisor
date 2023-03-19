package advisor.service;

import advisor.Main;
import advisor.model.Category;
import advisor.model.PlayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayListService extends ToppingWrapper implements UsePlayList {

    private List<PlayList> playListList;

    private Map<String, Category> categoryMap;

    public PlayListService(Service service) {
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

    /**
     * @param token token for using the API service
     * @param category the category of the albums need to be inserted to the playlist of the instance of this class */
    public void setPlayListList(String token, String category) {
        if (!categoryMap.containsKey(category)) {
            System.out.println("Unknown category name.");
            return;
        }
        String id = categoryMap.get(category).getId();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .uri(URI.create(Main.API_PATH + "/v1/browse/categories/" + id + "/playlists"))
                .GET()
                .build();
        if (playListList == null) {
            playListList = new ArrayList<>();
        }
        playlistInitialization(playListList,httpClient,httpRequest);
    }

    public List<PlayList> getPlayListList() {
        return playListList;
    }

    /**
     * @param categoryMap set the private instance variable categoryMap which is expected from the CategoriesService instance
     * @see CategoriesService
     * */
    public void setCategoryMap(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }
}
