package com.yyin.testfx.controllers;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.service.UserService;
import com.yyin.testfx.service.impl.UserServiceImpl;
import com.yyin.testfx.utils.EmailUtils;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 15:22 2021/12/10
 */



public class ForgetPassController extends Application {
    private UserService userService = new UserServiceImpl();
    private String code ;
    @FXML
    private Label labelConfirm;

    @FXML
    private ImageView imgConfirm;

    @FXML
    private Pane btnConfirm;

    @FXML
    private Button btnSendCode;

    @FXML
    private ImageView imgReturn;

    @FXML
    private ImageView imgSend;

    @FXML
    private Label labelError;

    @FXML
    private TextField registeredEmail;

    @FXML
    private TextField txtVerificationCode;

    private static String confirmingEmail;


    @FXML
    void sendCode(MouseEvent event) {
        code = EmailUtils.generateVerificationCode();
        confirmingEmail =registeredEmail.getText();
        if(userService.checkEmail(confirmingEmail)){
            if(userService.existUserEmail(confirmingEmail)) {
                String sendHeader = "[Verification Code]Please verify your identification";
                String sendMessage = "Verification Code:" + code;
                EmailUtils.sendEmail(confirmingEmail, sendHeader, sendMessage);
                imgSend.setImage( new Image(MainApplication.class.getResource("img/已发送.png").toString())) ;
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
            UIUtuils.labelError(labelError, Color.TOMATO,"The Mailbox Format Is Incorrect, Please Check");
        }
    }

    @FXML
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
    @FXML
    void confirmIdentity(MouseEvent event) {
        String verificationCode = txtVerificationCode.getText();
        if (verificationCode.isEmpty()){
            UIUtuils.labelError(labelError,Color.TOMATO,"The Verification Code Is Not Entered");
        }
        if (verificationCode.equals(code)){
            UIUtuils.labelError(labelError,Color.GREEN,"Identity Authentication Successful");
            /* TODO transfer values */
            EmailUtils.setEmail(confirmingEmail);
            Platform.runLater(() -> {
                //获取按钮所在的窗口
                Stage primaryStage = (Stage) imgReturn.getScene().getWindow();

                //加载forgetPass窗口
                try {
                    new ChangePassController().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }else {
            UIUtuils.labelError(labelError,Color.TOMATO,"Wrong Verification Code,Check Or Send Again ");
            imgConfirm.setImage(new Image(MainApplication.class.getResource("img/错误.png").toString()));
            imgSend.setImage(new Image(MainApplication.class.getResource("img/发送验证码.png").toString()));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

            Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/ForgetPass.fxml"));
            UIUtuils.transparentStyle(root,stage);
            stage.setScene(new Scene(root));
            stage.show();

    }
}
