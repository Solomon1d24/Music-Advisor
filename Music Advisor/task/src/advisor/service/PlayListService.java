package advisor.service;

import advisor.Main;
import advisor.exception.InvalidCategoryException;
import advisor.model.Category;
import advisor.model.PlayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class implements Service and UsePlayList interfaces.
 * Besides, it contains a list of playlist and a map of Category, the key of the category map are the names of Category
 * It has been applied with Singleton and Factory Pattern.
 * @see PlayList
 * @see Category
 * @see Service
 * @see UsePlayList
 * */
public class PlayListService implements UsePlayList, Service {

    private List<PlayList> playListList;

    private Map<String, Category> categoryMap;

    private String category;

    private static PlayListService playListService;

    public static synchronized PlayListService getInstance() {
        if (playListService == null) {
            playListService = new PlayListService();
        }
        return playListService;
    }

    private PlayListService() {}

    /**
     * @param token token for using the API service
     */
    @Override
    public void execute(String token) {
        if (!categoryMap.containsKey(category)) {
            playListList.clear();
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
        playlistInitialization(playListList, httpClient, httpRequest);
    }

    /**
     * @param categoryMap set the private instance variable categoryMap which is expected from the CategoriesService instance
     * @see CategoriesService
     * */
    public void setCategoryMap(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public List<String> getResult() {
        if (playListList.isEmpty()) {
            throw new InvalidCategoryException("Unknown category name.");
        }
        return playListList.stream()
                .map(p -> p.getName() + "\n" + p.getLink() + "\n")
                .collect(Collectors.toList());
    }
}
