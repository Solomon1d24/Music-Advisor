import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSample {

    public static void main(String[] args) {

        try {
            HttpServer httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress(8080), 0);
            httpServer.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String content = "Hello World";
                    exchange.sendResponseHeaders(200, content.length());
                    exchange.getResponseBody().write(content.getBytes());
                    exchange.getResponseBody().close();
                }
            });

            httpServer.start();
            try {
                HttpClient httpClient = HttpClient.newBuilder().build();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080"))
                        .GET()
                        .build();
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println(httpResponse.body());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            httpServer.stop(1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
