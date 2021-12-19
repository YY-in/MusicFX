package com.yyin.testfx.controllers.main;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:30 2021/12/16
 */
public class MainController {

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

//    public void initialize() throws IOException {
//        if (WindowUtils.isWindowsPlatform()){   //如果是windows平台,加载自定义设计的标题栏、添加阴影效果
//            FXMLLoader fxmlLoader = applicationContext.getBean(SpringFXMLLoader.class).getLoader("/fxml/main/main-top.fxml");
//            borderPane.setTop(fxmlLoader.load());
//            borderPane.getStyleClass().add("borderPaneDefault");
//        }
//    }
}
