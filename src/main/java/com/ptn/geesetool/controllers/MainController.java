package com.ptn.geesetool.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ptn.geesetool.GeeseApplication;
import com.ptn.geesetool.constants.Constant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

    @FXML
    private VBox vboxBtn;


    @FXML
    private BorderPane borderPane;

    @FXML
    private Button leftDownload;

    @FXML
    void onDownloadClick(MouseEvent event) {
        loadPage("download", event);
    }

    @FXML
    void onNotificationClick(MouseEvent event) {

    }

    @FXML
    void onSettingClick(MouseEvent event) {

    }

    @FXML
    void onUploadClick(MouseEvent event) {
        loadPage("upload",event);

    }

    public void loadPage(String page, MouseEvent event)  {
        Parent parent = null;

        try {
            parent =  FXMLLoader.load(GeeseApplication.class.getResource("/"+ Constant.RESOUCE_DIRECTORY.FXML+"/"+page + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(parent);

        Button btn = (Button) event.getSource();
        vboxBtn.getChildren().stream().forEach(x -> {
            x.getStyleClass().removeAll("left-btn-selected");
        });
        btn.getStyleClass().add("left-btn-selected");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
