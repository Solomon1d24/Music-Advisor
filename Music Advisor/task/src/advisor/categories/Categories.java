package advisor.categories;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    private static List<String> categoryList;


    static {
        categoryList = new ArrayList<>();
        categoryList.add("Top Lists");
        categoryList.add("Pop");
        categoryList.add("Mood");
        categoryList.add("Latin");
    }

    public static List<String> getCategoryList() {
        return categoryList;
    }


}
