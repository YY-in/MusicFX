package com.yyin.testfx.controllers;


import com.yyin.testfx.MainApplication;
import com.yyin.testfx.utils.KaptchaUtils;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SignUpController extends Application {

    @FXML
    private Button btnEditImage;

    @FXML
    private ImageView imgReturn;

    @FXML
    private ImageView imgClose;

    @FXML
    private ImageView imgKaptcha;

    @FXML
    void handleClose(MouseEvent event) {
        Stage stage = (Stage) imgClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void changeImgKaptcha(MouseEvent event) throws InterruptedException {

        imgKaptcha.setImage(KaptchaUtils.generateKaptchaImage());
    }


    @FXML
    void editImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(btnEditImage.getScene().getWindow());
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
}
