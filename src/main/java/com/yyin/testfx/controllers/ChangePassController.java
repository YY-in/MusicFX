package com.yyin.testfx.controllers;


import com.yyin.testfx.MainApplication;
import com.yyin.testfx.dao.UserDaoImpl;
import com.yyin.testfx.dao.in.UserDao;
import com.yyin.testfx.service.UserService;
import com.yyin.testfx.service.UserServiceImpl;
import com.yyin.testfx.utils.EmailUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangePassController extends Application {
    UserService service = new UserServiceImpl();



    @FXML
    private Button btnChange;

    @FXML
    private ImageView imgChange;

    @FXML
    private ImageView imgReturn;

    @FXML
    private Label labelChange;

    @FXML
    private Label labelError;

    @FXML
    private PasswordField txtPassAga;

    @FXML
    private PasswordField txtnewPass;

    @FXML
    void changePass(MouseEvent event) {
        String newPass = txtnewPass.getText();
        String newPassAga = txtPassAga.getText();
        if (newPassAga.isEmpty() || newPassAga.isEmpty()){
            UIUtuils.labelError(labelError, Color.TOMATO,"Password Is Empty");
        }
        if (!newPass.equals(newPassAga)){
            UIUtuils.labelError(labelError, Color.TOMATO,"The Two Passwords You Typed Do Not Match");
        }else {
            if (service.checkPassword(newPass)) {
                /*TODO modify database*/
                String email = EmailUtils.getEmail();
                System.out.println(email);
                UserDao userDao = new UserDaoImpl();
                //如果返回-1,说明执行失败返回其他表示影响的行数
                int i = userDao.updateUserPasswordByEmail(email,newPass);
                if (i==-1){
                    UIUtuils.labelError(labelError,Color.TOMATO,"Error Executing");
                }else{
                    UIUtuils.labelError(labelError,Color.GREEN,"Modify Successfully");
                    labelChange.setText("<--Portal");
                    imgChange.setDisable(false);
                    imgChange.setImage(new Image(MainApplication.class.getResource("img/黑洞.png").toString()));
                }


            } else {
                UIUtuils.labelError(labelError,Color.TOMATO,"Password Is Illegal");
            }
        }
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

    @FXML
    public void backHome(MouseEvent mouseEvent) {
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
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/ChangePass.fxml"));
        UIUtuils.transparentStyle(root,stage);
        stage.setScene(new Scene(root));
        stage.show();

    }



}
