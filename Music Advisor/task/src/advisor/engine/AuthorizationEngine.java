package advisor.engine;

import advisor.Main;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthorizationEngine {

    private static final String CLIENT_ID = "22f7d0d3fdba48e593486f7f0779cab3";

    private static final String CLIENT_SECRET = "1fd40e8a457e4e62a5fbee883cdef5b2";

    private static final String REDIRECT_URI = "http://localhost:8080";

    public static String REQUEST_URI = "https://accounts.spotify.com";

    private String accessCode;

    private String accessToken;

    /**
     * Set the Access Code
     * */
    public void setAccessCode() {
        try {
            HttpServer httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(8080), 0);
            String requestLink = REQUEST_URI
                    + "/authorize?"
                    + "client_id="
                    + CLIENT_ID
                    + "&redirect_uri="
                    + REDIRECT_URI
                    + "&response_type=code";

            System.out.println("use this link to request the access code:");
            System.out.println(requestLink);
            httpServer.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String response;
                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("code")) {
                        accessCode = query.substring(5);
                        response = "Got the code. Return back to your program.";
                    } else {
                        response = "Authorization code not found. Try again.";
                    }
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }
            });
            httpServer.start();
            System.out.println("waiting for code...");
            while (accessCode == null || accessCode.length() == 0) {
                Thread.sleep(500);
            }
            System.out.println("code received");
            httpServer.stop(5);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the access token based on the access code
     * */
    public void setAccessToken() {

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(REQUEST_URI + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code"
                        + "&code=" + accessCode
                        + "&client_id=" + CLIENT_ID
                        + "&client_secret=" + CLIENT_SECRET
                        + "&redirect_uri=" + REDIRECT_URI))
                .build();

        try {
            System.out.println("making http request for access_token...");
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("response:");
            System.out.println(httpResponse.body());
            System.out.println("---SUCCESS---");
            Main.AUTH = true;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
