package com.ptn.geesetool.services;

import com.ptn.geesetool.utils.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DownloadImage {
    public static final String PATH = System.getProperty("user.dir");
    public static final String suffix = "/";
    public static final String OUTPUT = PATH + suffix + "out" + suffix + "img" + suffix;

    public static void action(String url,String name) throws Exception {

        if(Files.exists(Paths.get(OUTPUT + name))){
            return;
        }

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        Future<InputStream> futureInputStream =
                httpClient
                        .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofInputStream())
                        .thenApply(HttpResponse::body);

        InputStream inputStream = futureInputStream.get();
        Files.copy(inputStream, Path.of(OUTPUT + name));
    }
}
