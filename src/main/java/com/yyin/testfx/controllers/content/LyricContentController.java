package com.yyin.testfx.controllers.content;

import javafx.animation.RotateTransition;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 8:32 2021/12/17
 */
public class LyricContentController {
    /**javafx内置动画，围绕某一支点进行旋转*/
    private RotateTransition rotateTransition;

    /**标记这个面板是否正在显示在centerPane里,在bottomController里控制,当单击专辑图片时,设置显示true*/
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public RotateTransition getRotateTransition() {
        return rotateTransition;
    }
}
