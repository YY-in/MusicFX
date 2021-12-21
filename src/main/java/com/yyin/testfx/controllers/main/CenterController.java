package com.yyin.testfx.controllers.main;

import com.yyin.testfx.application.MusicMainApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:33 2021/12/16
 */
public class CenterController extends Application implements Initializable {
    /**中间的StackPane容器*/
    @FXML
    private StackPane stackPane;

    /**中间StackPane第0层的borderPane，StackPane的层数如 0,1,2...*/
    @FXML
    private BorderPane borderPane;

    @Resource
    private LeftController leftController;

    @Resource
    private MainController mainController;

    public StackPane getStackPane(){
        return stackPane;
    }

    public BorderPane getBorderPane(){ return borderPane; }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
