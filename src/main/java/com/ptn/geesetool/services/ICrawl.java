package com.ptn.geesetool.services;

import com.ptn.geesetool.GeeseApplication;
import com.ptn.geesetool.constants.Constant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.cell.ImageGridCell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public interface ICrawl {

    String getUrlOrKeywords();

    String typeDownload();

    int getThreadNumber();

    default List<String> readInputDownload() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(getUrlOrKeywords()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    void crawlFromWebsite(String url) throws IOException;

    default void excute() {

        ExecutorService exe = Executors.newCachedThreadPool();
        readInputDownload().forEach(x -> {
            exe.submit(() -> {
                try {
                    crawlFromWebsite(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        exe.shutdown();

        try {
            exe.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
