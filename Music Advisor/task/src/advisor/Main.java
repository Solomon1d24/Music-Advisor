package advisor;

import advisor.categories.Categories;
import advisor.features.Feature;
import advisor.playlist.PlayList;
import advisor.release.Release;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OURERLOOP:
        while (true) {
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case "new":
                    showRelease();
                    break;
                case "featured":
                    showFeatures();
                    break;
                case "categories":
                    showCategories();
                    break;
                case "playlists":
                    showPlayList();
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    break OURERLOOP;
                default:
                    System.exit(1);
            }
        }

    }

    private static void showRelease() {
        System.out.println("---NEW RELEASES---");
        Release.getReleaseList().forEach(s -> {
            System.out.println(s);
        });
    }

    private static void showFeatures() {
        System.out.println("---FEATURED---");
        Feature.getFeatureList().forEach(f -> {
            System.out.println(f);
        });
    }

    private static void showCategories() {
        System.out.println("---CATEGORIES---");
        Categories.getCategoryList().forEach(c -> {
            System.out.println(c);
        });
    }

    private static void showPlayList() {
        System.out.println("---MOOD PLAYLISTS---");
        PlayList.getPlayList().forEach(p -> {
            System.out.println(p);
        });
    }


}
