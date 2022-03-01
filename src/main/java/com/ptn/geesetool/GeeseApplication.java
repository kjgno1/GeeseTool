package com.ptn.geesetool;

import com.ptn.geesetool.config.HibernateUtil;
import com.ptn.geesetool.config.SqliteUtil;
import com.ptn.geesetool.constants.Constant;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.sql.SQLException;

public class GeeseApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        if (this.getClass().getResource("db/app.db") == null) {
//            Platform.runLater(() -> {
//                try {
//                    SqliteUtil.createDatabases();
//                } catch (SQLException | IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();


        FXMLLoader fxmlLoader = new FXMLLoader(GeeseApplication.class.getResource("/" + Constant.RESOUCE_DIRECTORY.FXML + "/geese.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("GeeseTool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}