package com.yyin.testfx.controllers;

import com.yyin.testfx.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:22 2021/12/10
 */



public class ForgetPassController {

    @FXML
    private ImageView imgReturn;

    @FXML
    private ImageView imgSend;

    @FXML
    private TextField registeredEmail;

    @FXML
    private TextField verificationCode;

    @FXML
    void sendCode(MouseEvent event) {

    }

    public void returnLogIn(MouseEvent mouseEvent) {
        Platform.runLater(()->{
            try{
                //获取按钮所在的窗口
                Stage primaryStage = (Stage) imgReturn.getScene().getWindow();
                //当前窗口隐藏
                primaryStage.hide();
                //加载forgetPass窗口
                new MainApplication().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
