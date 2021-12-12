package com.yyin.testfx.controllers;


import com.yyin.testfx.MainApplication;
import com.yyin.testfx.service.UserService;
import com.yyin.testfx.service.UserServiceImpl;
import com.yyin.testfx.utils.EmailUtils;
import com.yyin.testfx.utils.KaptchaUtils;
import com.yyin.testfx.utils.UIUtuils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class SignUpController extends Application {

    private UserService userService = new UserServiceImpl();

    private File selectedImage;

    private String randomCode;

    private String emailRandomCode;

    private String email;

    @FXML
    private Button btnEditImage;

    @FXML
    private Circle circle;

    @FXML
    private TextField txtEmail;


    @FXML
    private Button btnVa;

    @FXML
    private TextField emailCode;

    @FXML
    private PasswordField firstPass;

    @FXML
    private ImageView imgClose;

    @FXML
    private ImageView imgId;

    @FXML
    private ImageView imgKaptcha;

    @FXML
    private ImageView imgReturn;

    @FXML
    private TextField kaptchaCode;

    @FXML
    private PasswordField secondPass;

    @FXML
    private TextField userName;

    @FXML
    private ImageView warnImage;

    @FXML
    private Label warnLabel;



    @FXML
    void handleClose(MouseEvent event) {
        Stage stage = (Stage) imgClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void changeImgKaptcha(MouseEvent event) throws InterruptedException {
        randomCode = KaptchaUtils.generateRandomCode();
        imgKaptcha.setImage(KaptchaUtils.generateKaptchaImage(randomCode));
    }


    @FXML
    void editImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

         selectedImage= fileChooser.showOpenDialog(btnEditImage.getScene().getWindow());

        if (selectedImage != null) {

            imgId.setVisible(false);
            circle.setFill(new ImagePattern(new Image("file:\\"+selectedImage.getPath())));

        } else  {
            warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
            UIUtuils.labelError(warnLabel, Color.TOMATO,"Can't Find The Picture You Have Selected");
        }

    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/SignUp.fxml"));
        UIUtuils.transparentStyle(root,stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleReturn(MouseEvent mouseEvent) {
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

    public void emailValidation(MouseEvent mouseEvent) throws InterruptedException {
        if(kaptchaCode.getText().equals(randomCode)){
            kaptchaCode.setDisable(true);
            String email=txtEmail.getText();
            imgKaptcha.setDisable(true);

           if (!email.isEmpty()){
               if (userService.checkEmail(email)) {
                   UIUtuils.labelError(warnLabel, Color.GREEN, "Yes,You are Human");
                   warnImage.setImage(new Image(MainApplication.class.getResource("img/正确.png").toString()));
                   if (!userService.existUserEmail(email)) {
                       UIUtuils.labelError(warnLabel, Color.GREEN, "Oh,Fresh Email");
                       emailRandomCode = KaptchaUtils.generateRandomCode();
                       EmailUtils.sendEmail(email, "Welcome TO Register Our Platform",
                               "Verification code is : " + emailRandomCode);
                       btnVa.setDisable(true);
                       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), ae -> btnVa.setDisable(false)));
                       timeline.play();
                   }else{
                       warnImage.setImage(new Image(MainApplication.class.getResource("img/错误.png").toString()));
                       UIUtuils.labelError(warnLabel,Color.TOMATO,"Email Has Been Registered");
                   }
               }else{
                   warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
                   UIUtuils.labelError(warnLabel,Color.TOMATO,"Email Is Illegal");
               }
           }else{
               warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
               UIUtuils.labelError(warnLabel,Color.TOMATO,"Email Is Empty");
           }
        }else{
            warnImage.setImage(new Image(MainApplication.class.getResource("img/错误.png").toString()));
            UIUtuils.labelError(warnLabel,Color.TOMATO,"Verification Code Error");
        }

    }

    public void cancel(MouseEvent mouseEvent) {
    }

    public void submit(MouseEvent mouseEvent) {
        String password = secondPass.getText();

        if(emailCode.getText().equals(emailRandomCode)){
            email=txtEmail.getText();
            txtEmail.setDisable(true);

            if(firstPass.getText().equals(password)){

                if (userService.checkPassword(password)){

                    String username=userName.getText();

                    if (!username.isEmpty()){
                        userName.setDisable(true);
                        //TODO UserService
                        userService.saveRegisterInformation(username,email,password, selectedImage.getAbsolutePath());
                        warnImage.setImage(new Image(MainApplication.class.getResource("img/庆祝.png").toString()));
                        UIUtuils.labelError(warnLabel,Color.LIGHTBLUE,"Welcome");
                    }else{
                        warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
                        UIUtuils.labelError(warnLabel,Color.TOMATO,"UserName Is Empty");
                    }
                }else {
                    warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
                    UIUtuils.labelError(warnLabel,Color.TOMATO,"The Password Is Illegal");
                }
            }else{
                warnImage.setImage(new Image(MainApplication.class.getResource("img/警告.png").toString()));
                UIUtuils.labelError(warnLabel,Color.TOMATO,"The Passwords Are Inconsistent");
            }
        }else{
            warnImage.setImage(new Image(MainApplication.class.getResource("img/错误.png").toString()));
            UIUtuils.labelError(warnLabel,Color.TOMATO,"Email Not Available");
        }

    }
}
