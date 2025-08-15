package com.oracle.cli.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class Http {
    private static HttpClient client;

    private Http() {}

    private static HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }

        return client;
    }

    public static synchronized String get(String url) throws ExecutionException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url))
            .build();

        return send(request);
    }

    private static String send(HttpRequest request) throws ExecutionException, InterruptedException {
        return getClient()
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .get()
            .body();
    }
}
