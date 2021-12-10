package com.yyin.testfx.utils;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 22:06 2021/12/10
 */
public class UIUtuils {
    public static void labelError(Label labelErrors, Color color, String text) {
        labelErrors.setTextFill(color);
        labelErrors.setText(text);
        System.out.println(text);
    }
}
