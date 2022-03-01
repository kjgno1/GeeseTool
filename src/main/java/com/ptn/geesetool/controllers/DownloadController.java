package com.ptn.geesetool.controllers;


import com.ptn.geesetool.GeeseApplication;
import com.ptn.geesetool.dao.DataCrudImpl;
import com.ptn.geesetool.dao.IDataCrud;
import com.ptn.geesetool.model.ImageInfo;
import com.ptn.geesetool.services.CrawlTeePublic;
import com.ptn.geesetool.services.DownloadImage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DownloadController implements Initializable {
    @FXML
    private AnchorPane acDownload;
    @FXML
    public ComboBox cbbWebsite;
    @FXML
    public RadioButton rbKeyworld;
    @FXML
    public ToggleGroup rbSearchGroup;
    @FXML
    public RadioButton rbLinkFile;
    @FXML
    public TextArea txtKeywords;
    @FXML
    public Button btnDownload;
    @FXML
    public Button btnCrawl;
    @FXML
    public TextField txtFile;
    @FXML
    public Button btnChoose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbKeyworld.setSelected(true);
        txtFile.setText("D:\\Java Fx\\GeeseTool\\template.txt");
    }

    @FXML
    void onChooseClick(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) acDownload.getScene().getWindow();

        FileChooser chooser = new FileChooser();
        chooser.setTitle("JavaFX Projects");
        File selectedFile = chooser.showOpenDialog(primaryStage);
        txtFile.setText(selectedFile.getPath());

    }

    @FXML
    void onCrawlClick(MouseEvent event) {
        var thread = new Thread(() -> {
            long startTime = System.nanoTime();
            CrawlTeePublic downLoadTeePublic = new CrawlTeePublic();
            downLoadTeePublic.setFixedThread(5);
            downLoadTeePublic.setKeyWords(txtFile.getText());
            downLoadTeePublic.excute();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            Platform.runLater(() -> {
                Image img = new Image(String.valueOf(GeeseApplication.class.getResource("/img/success.png")));
                Notifications.create()
                        .title("Notification")
                        .text("Crawl successfully")
                        .graphic(new ImageView(img))
                        .show();
            });

            System.out.println(duration);
        });
        thread.start();
    }

    @FXML
    void onDownloadClick(MouseEvent event) {
        var thread = new Thread(() -> {
            IDataCrud crud = new DataCrudImpl();
            List<ImageInfo> lstImage = crud.findAll();


            long startTime = System.nanoTime();
            ExecutorService exe = Executors.newCachedThreadPool();
            lstImage.forEach(x -> {
                exe.submit(() -> {
                    try {
                        DownloadImage.action(x.getUrl(),x.getName());
                    } catch (Exception e) {
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
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            Platform.runLater(() -> {
                Image img = new Image(String.valueOf(GeeseApplication.class.getResource("/img/success.png")));
                Notifications.create()
                        .title("Notification")
                        .text("Download successfully")
                        .graphic(new ImageView(img))
                        .show();
            });

            System.out.println(duration);
        });
        thread.start();
    }


}
