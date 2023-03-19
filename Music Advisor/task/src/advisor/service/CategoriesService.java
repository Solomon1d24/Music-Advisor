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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriesService extends ToppingWrapper {

    private Map<String, Category> categoryMap;

    public CategoriesService(Service service) {
        super(service);
    }

    /**
     * Thie method is used to initialize the categoryMap using the accessToken got from the authorization engine
     * @param accessToken
     * @see advisor.engine.AuthorizationEngine
     * */
    public void setCategoryList(String accessToken) {
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

    /**
     * The instance method inherited from the service interface
     * @return String of the response value
     * @see Service
     * */
    @Override
    public String getService() {
        return super.getService()
                + getCategoryList().stream().map(Category::getName).collect(Collectors.joining("\n"));
    }
}
