package com.yyin.testfx.application;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.utils.WindowUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 16:22 2021/12/13
 */
public class MusicMainApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/main/main-pane.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(MainApplication.class.getResource("css/ContextMenuStyle.css").toExternalForm());  //添加contextmenu的样式
        primaryStage.setTitle("音乐"); // 设置标题
        primaryStage.getIcons().add(new Image(MainApplication.class.getResource("img/NeteaseMusicPlayerIcon.png").toString())); //设置图标
        primaryStage.setScene(scene);
        if (WindowUtils.isWindowsPlatform()){   //如果是Windows平台
            primaryStage.initStyle(StageStyle.TRANSPARENT);   //去掉Windows自带的标题栏
            WindowUtils.addResizable(primaryStage,860,570);  //为primaryStage添加自由缩放
            WindowUtils.addWindowsStyle(primaryStage);  //为primaryStage添加一些GUI的修复代码
        }else { //minWidth="870.0" minHeight="580.0"
//            primaryStage.setMinWidth(870.0);
//            primaryStage.setMinHeight(620);
        }
    }
}
