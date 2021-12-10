package com.yyin.testfx.controllers;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.service.UserService;
import com.yyin.testfx.service.UserServiceImpl;
import com.yyin.testfx.utils.EmailUtils;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:22 2021/12/10
 */



public class ForgetPassController {
    private UserService userService = new UserServiceImpl();

    @FXML
    private ImageView imgReturn;

    @FXML
    private ImageView imgSend;

    @FXML
    private Label labelError;

    @FXML
    private TextField registeredEmail;

    @FXML
    private TextField verificationCode;

    @FXML
    void sendCode(MouseEvent event) {
        String email =registeredEmail.getText();
        if(EmailUtils.checkEmail(email)){
            if(userService.existUserEmail(email)) {
                String code = EmailUtils.generateVerificationCode();
                String sendHeader = "[Verification Code]Please verify your identification";
                String sendMessage = "Verification Code:" + code;
                EmailUtils.sendEmail(email, sendHeader, sendMessage);
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Without Relevant User");
                alert.setHeaderText("This email haven't been registered");
                alert.setContentText("Do you want to register for our platform");
                ImageView image=new ImageView(MainApplication.class.getResource("img/数据库异常.png").toString());
                image.setFitHeight(50);
                image.setFitWidth(45);
                alert.setGraphic(image);
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    //TODO
                }else{
                    alert.close();
                }

            }
        }else{
            UIUtuils.labelError(labelError, Color.TOMATO,"邮箱格式错误,请检查");
        }
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
