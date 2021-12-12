package com.yyin.testfx.controllers;

import com.yyin.testfx.models.User;
import com.yyin.testfx.service.UserService;
import com.yyin.testfx.service.UserServiceImpl;
import com.yyin.testfx.utils.JdbcUtils;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * @author YinZhihao
 * @description Login界面控制器
 * @date Created in 20:32 2021/12/9
 */
public class LoginController implements Initializable {
        Connection conn;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        private UserService userService = new UserServiceImpl();

        public LoginController() {
                conn = JdbcUtils.getConnection();
        }

        @FXML
        private ImageView AliPay;

        @FXML
        private ImageView Apple;

        @FXML
        private ImageView Bilibili;

        @FXML
        private ImageView Google;

        @FXML
        private ImageView Steam;

        @FXML
        private ImageView WeChat;

        @FXML
        private Label btnForgot;


        @FXML
        private Button btnSignin;

        @FXML
        private Button btnSignup;

        @FXML
        private ImageView imgClose;

        @FXML
        private Label lblErrors;

        @FXML
        private PasswordField txtPassword;

        @FXML
        private TextField txtUsername;

        /**
         * @Description: 设定鼠标单击图片监视器，在点击图片之后关闭当前窗口
         * @Date: 21:38 2021/12/9
         */
        @FXML
        void handleButtonClose(MouseEvent event) {
                Stage stage = (Stage) imgClose.getScene().getWindow();
                stage.close();
        }

        @FXML
        void urlHandle(MouseEvent event) {
                //TODO the three party certification
                try {
                        if (event.getSource() == WeChat) {
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://wx.qq.com");
                        }else if (event.getSource() == AliPay){
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://auth.alipay.com/login/index.htm");
                        }else if(event.getSource() == Apple){
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://www.apple.com.cn");
                        }else if(event.getSource()== Bilibili){
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://www.bilibili.com");
                        }else if (event.getSource()== Steam){
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://store.steampowered.com");
                        }else if(event.getSource() == Google){
                                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://www.google.com");

                        }

                } catch(IOException e){
                        e.printStackTrace();
                }
        }



        /**
         * @Description:设置鼠标单击SignIn按钮监视器，在点击SignIn后实现页面的跳转
         * @Date: 21:37 2021/12/9
         */
        @FXML
        void handleButtonSignIn(MouseEvent event) {

                if (event.getSource() == btnSignin) {
                        //login here
                        if (logIn().equals("Success")) {
                                try {
                                        //add you loading or delays - ;-)
                                        Node node = (Node) event.getSource();
                                        Stage stage = (Stage) node.getScene().getWindow();
                                        //stage.setMaximized(true);
                                        stage.close();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/SignU.fxml")));
                                        stage.setScene(scene);
                                        stage.show();

                                } catch (IOException ex) {
                                        System.err.println(ex.getMessage());
                                }

                        }
                }
        }

        /**
         * @Description:检查数据库的连接情况，并且在报错标签中回显
         * @Date: 21:42 2021/12/9
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                // TODO
                if (conn == null) {
                        UIUtuils.labelError(lblErrors,Color.TOMATO,"Server Exception");
                } else {
                        UIUtuils.labelError(lblErrors,Color.GREEN,"Server Connected");
                }
        }

        /**
         * @Description: 完成用户的登录
         * @Date: 21:52 2021/12/9
         * @return： Status String 登录状态
         */
        protected String logIn() {
                btnSignup.setDefaultButton(true);
                String status = "Error";
                String logMes = txtUsername.getText();
                String password = txtPassword.getText();

                if (logMes.isEmpty() || password.isEmpty()) {
                        UIUtuils.labelError(lblErrors,Color.TOMATO, "Login Information Has Not Been Filled In");

                } else {
                        if (userService.checkEmail(logMes)) {
                                User loginUser = userService.loginByEmail(new User(null, null, password, logMes,null));
                                if (loginUser == null) {
                                        // 把错误信息，和回显的表单项信息
                                        UIUtuils.labelError(lblErrors,Color.TOMATO, "Email Or Password Error!");
                                } else {
                                        UIUtuils.labelError(lblErrors,Color.GREEN, "Login Successful,Redirecting...");
                                        status = "Success";
                                }
                        } else {
                                User loginUser = userService.loginByName(new User(null, logMes, password, null,null));
                                if (loginUser == null) {
                                        // 把错误信息，和回显的表单项信息
                                        UIUtuils.labelError(lblErrors,Color.TOMATO, "Wrong Username Or Password");
                                } else {
                                        UIUtuils.labelError(lblErrors,Color.GREEN, "Login Successful, redirecting...");
                                        status = "Success";
                                }
                        }

                }

                return status;

        }



        /**
         * @param mouseEvent 单击事件
         * @Description 实现forgetPass页面的跳转
         * @Date: 16:23 2021/12/10
         */
        @FXML
        public void forgetPass(MouseEvent mouseEvent) {
                Platform.runLater(() -> {
                        //获取按钮所在的窗口
                        Stage primaryStage = (Stage) btnForgot.getScene().getWindow();
                        //当前窗口隐藏

                        //加载forgetPass窗口
                        try {
                                new ForgetPassController().start(primaryStage);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });

        }

        @FXML
        public void signUp(MouseEvent mouseEvent) {
                Platform.runLater(() -> {
                        //获取按钮所在的窗口
                        Stage primaryStage = (Stage) btnForgot.getScene().getWindow();
                        //当前窗口隐藏

                        //加载forgetPass窗口
                        try {
                                new SignUpController().start(primaryStage);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });
        }
}
