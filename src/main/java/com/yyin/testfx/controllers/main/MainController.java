package com.yyin.testfx.controllers.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:30 2021/12/16
 */
public class MainController implements Initializable {

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
}
