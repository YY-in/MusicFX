package com.yyin.testfx.utils;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:06 2021/12/10
 */
public class UIUtuils {
    public static double xOffset = 0;
    public static double yOffset = 0;


    public static void labelError(Label labelErrors, Color color, String text) {
        labelErrors.setTextFill(color);
        labelErrors.setText(text);
        System.out.println(text);
    }

    public static void transparentStyle(Parent root, Stage stage){
        if (stage.getStyle()!= StageStyle.TRANSPARENT) {
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }




}
