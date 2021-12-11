package com.yyin.testfx.controllers;


import com.yyin.testfx.MainApplication;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangePassController extends Application {

    @FXML
    private Button btnComfirm;

    @FXML
    private ImageView imgConfirm;

    @FXML
    private ImageView imgReturn;

    @FXML
    private Label labelConfirm;

    @FXML
    private Label labelError;

    @FXML
    private PasswordField txtPassAga;

    @FXML
    private PasswordField txtnewPass;

    @FXML
    void changePass(MouseEvent event) {

    }

    @FXML
    void returnLogIn(MouseEvent event) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) imgReturn.getScene().getWindow();

            //加载forgetPass窗口
            try {
                new ForgetPassController().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/ChangePass.fxml"));
        UIUtuils.transparentStyle(root,stage);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
