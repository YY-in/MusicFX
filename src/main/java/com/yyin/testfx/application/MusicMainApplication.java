package com.yyin.testfx.application;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.controllers.content.LyricContentController;
import com.yyin.testfx.utils.ImageUtils;
import com.yyin.testfx.utils.UIUtuils;
import com.yyin.testfx.utils.WindowUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
        primaryStage.getIcons().add(ImageUtils.getImage("NeteaseMusicPlayerIcon.png")); //设置图标
        primaryStage.setScene(scene);
        UIUtuils.transparentStyle(root,primaryStage);
        addMinMaxsizeBehavior(primaryStage);
    }

    /**
     * 修补在最大化状态下，最小化窗体之后单击任务栏图标恢复时，窗体的高度和宽度是全屏的问题。修复后，宽度和高度是为屏幕可视化的宽度和高度
     */
    private void addMinMaxsizeBehavior(Stage primaryStage) {
        primaryStage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            //确保Windows平台下,窗体在最大化状态下最小化后，单击任务栏图标显示时占据的屏幕大小是可视化的全屏
            if (primaryStage.isMaximized() && WindowUtils.isWindowsPlatform()) {
                primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
                primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
            }
            LyricContentController lyricContentController = new LyricContentController();
            if (observable.getValue()){
                System.out.println("cancel");
//                /**#####RotateTransition######*/
//                if (lyricContentController.isShow() && lyricContentController.getRotateTransition().getStatus() == Animation.Status.RUNNING){
//                    lyricContentController.getRotateTransition().pause();
//                }
//            }else {
//                /**#####RotateTransition######*/
//                if (lyricContentController.isShow()
//                        //TODO
//                        && lyricContentController.getRotateTransition().getStatus() == Animation.Status.PAUSED
//                        && applicationContext.getBean(MyMediaPlayer.class).getPlayer().getStatus() == MediaPlayer.Status.PLAYING){
//                    lyricContentController.getRotateTransition().play();
//                }
            }
        });
    }
}
