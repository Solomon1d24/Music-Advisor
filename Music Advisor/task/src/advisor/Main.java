package advisor;

import advisor.categories.Categories;
import advisor.features.Feature;
import advisor.playlist.PlayList;
import advisor.release.Release;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] inputArray = getInput(scanner);
            if (!inputArray[0].toLowerCase().equals("auth") && !inputArray[0].toLowerCase().equals("exit")) {
                System.out.println("Please, provide access for application.");
            } else if (inputArray[0].toLowerCase().equals("exit")) {
                System.out.println("---GOODBYE!---");
                System.exit(0);
            } else {
                showAuth();
                break;
            }
        }
        OUTERLOOP:
        while (true) {
            String[] inputArray = getInput(scanner);
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
                case "auth":
                    showAuth();
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    break OUTERLOOP;
                default:
                    System.exit(1);
            }
        }
    }

    private static String[] getInput(Scanner scanner) {
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        return inputArray;
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

    private static void showAuth() {
        System.out.println("https://accounts.spotify.com/authorize?client_id=22f7d0d3fdba48e593486f7f0779cab3&redirect_uri=http://localhost:8080&response_type=code");
        System.out.println("---SUCCESS---");
    }


}
