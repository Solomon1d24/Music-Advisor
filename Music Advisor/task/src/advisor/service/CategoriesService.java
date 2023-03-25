package advisor.service;

import advisor.Main;
import advisor.helper.JsonHelper;
import advisor.model.Category;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * This class implements the Service interface and contains the Categories of Songs in Spotify with a map,
 * the name of the Categories are the keys of the map
 * This class has been applied with Singleton and Factory pattern
 * @see Category
 * @see Service
 * */
public class CategoriesService implements Service {

    private Map<String, Category> categoryMap;

    private static CategoriesService categoriesService;

    public static synchronized CategoriesService getInstance() {
        if (categoriesService == null) {
            categoriesService = new CategoriesService();
        }
        return categoriesService;
    }

    private CategoriesService() {}

    /**
     * Thie method is used to initialize the categoryMap using the accessToken got from the authorization engine
     * @param accessToken
     * @see advisor.engine.AuthorizationEngine
     * */
    @Override
    public void execute(String accessToken) {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .uri(URI.create(Main.API_PATH + "/v1/browse/categories"))
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonObject responseObject =
                    JsonHelper.getField(httpResponse.body(), "categories").getAsJsonObject();
            if (categoryMap == null) {
                categoryMap = new HashMap<>();
            }
            categoryMap.clear();
            for (JsonElement element : responseObject.get("items").getAsJsonArray()) {
                JsonObject object = element.getAsJsonObject();
                String name = object.get("name").getAsString();
                String id = object.get("id").getAsString();
                String link = object.get("href").getAsString();
                categoryMap.put(name, new Category(id, name, link));
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public List<Category> getCategoryList() {
        return categoryMap.values().stream().toList();
    }

    @Override
    public List<String> getResult() {
        return categoryMap.values().stream()
                .map(c -> c.getName())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
