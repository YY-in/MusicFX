package com.yyin.testfx;

import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 16:07 2021/12/10
 */
public class ForgetPassApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ForgetPass.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        UIUtuils.transparentStyle(root,primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
