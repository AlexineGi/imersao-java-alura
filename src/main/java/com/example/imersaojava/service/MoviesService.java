package com.example.imersaojava.service;

import com.example.imersaojava.utils.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Math.toIntExact;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.newBuilder;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class MoviesService {

    private static final String TOP_MOVIES_URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

    public static void findMostPopularMovies() throws IOException, InterruptedException {
        URI uri = URI.create(TOP_MOVIES_URL);

        var httpClient = newHttpClient();

        HttpRequest request = newBuilder(uri).GET().build();

        HttpResponse<String> response = httpClient.send(request, ofString());

        String body = response.body();

        JSONParser parser = new JSONParser();

        List<Map<String, String>> moviesList = parser.parse(body);

        System.out.println("\n \u001b[100m ------------------------------------------------------------------------------------------------------- \u001b[m \n");

        for (Map<String, String> movie : moviesList) {
            int stars = toIntExact((long) parseDouble(movie.get("imDbRating")));

            System.out.print("\u001b[1m Poster: \u001b[0m");
            System.out.println(movie.get("image"));
            System.out.print("\u001b[1m Classificação: \u001b[0m");
            System.out.println("\u001b[95m \u001b[4m" + movie.get("imDbRating") + "\u001b[m");

            for (int x = 0; x < stars; x++) {
                System.out.print("\u001b[93m \u2B50 \u001b[m");
            }

            System.out.println("");
            System.out.print("\u001b[1m Título: \u001b[m");
            System.out.println(movie.get("title"));
            System.out.println("\n \u001b[100m ------------------------------------------------------------------------------------------------------- \u001b[m \n");
        }
    }

}
