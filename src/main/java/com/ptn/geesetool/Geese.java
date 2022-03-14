package com.ptn.geesetool;

import javafx.scene.control.Alert;

public class Geese {
    public static void main(String[] args) {
        try {
            GeeseApplication.main(args);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }
}
