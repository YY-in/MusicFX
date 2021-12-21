package com.yyin.testfx.controllers.main;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:30 2021/12/16
 */
public class MainController extends Application implements Initializable {

    /**根容器stackPane*/
    @FXML
    private static StackPane stackPane;

    /**根容器stackPane的第一个孩子容器borderPane，deep-index=0，表示在stackPane的第0层*/
    @FXML
    private BorderPane borderPane;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public static StackPane getStackPane() {
        return stackPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/main/main-pane.fxml"));
        UIUtuils.transparentStyle(root,stage);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
