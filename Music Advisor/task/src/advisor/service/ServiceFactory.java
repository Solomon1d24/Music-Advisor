package advisor.service;

public class ServiceFactory {
    public static Service get(String type) {

        switch (type) {
            case "new" -> {
                return NewService.getInstance();
            }
            case "featured" -> {
                return FeatureService.getInstance();
            }
            case "playlists" -> {
                return PlayListService.getInstance();
            }
            case "categories" -> {
                return CategoriesService.getInstance();
            }
            default -> throw new RuntimeException("Unknown input");
        }
    }
}
