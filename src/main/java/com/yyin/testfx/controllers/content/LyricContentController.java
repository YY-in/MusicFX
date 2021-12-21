package com.yyin.testfx.controllers.content;

import com.yyin.testfx.MainApplication;
import com.yyin.testfx.controllers.main.MainController;
import com.yyin.testfx.utils.UIUtuils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 8:32 2021/12/17
 */
public class LyricContentController extends Application {
    @FXML
    private BorderPane albumPane;

    @FXML
    private ImageView ivAlbum;

    @FXML
    private ImageView ivAlbumOutdoor;

    @FXML
    private Label labAddFavor;

    @FXML
    private Label labAlbum;

    @FXML
    private Label labArtist;

    @FXML
    private Label labBlur;

    @FXML
    private Label labCollect;

    @FXML
    private ImageView labResizeSmall;

    @FXML
    private Label labTitle;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private BorderPane root;

    @FXML
    private ScrollPane scrollLyric;

    @FXML
    private StackPane stackPane;

    @FXML
    private StackPane stackPaneAlbum;

    @FXML
    private VBox vBoxAlbum;

    @FXML
    private VBox vBoxLyric;

    @FXML
    private VBox vBoxLyricContentContainer;

    @FXML
    void onClickedResizeSmall(MouseEvent event) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) albumPane.getScene().getWindow();
            //当前窗口隐藏

            //加载主页面窗口
            try {
                new MainController().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(MainApplication.class.getResource("fxml/content/album-lyric-content.fxml"));
        UIUtuils.transparentStyle(root,stage);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public boolean isShow() {
        return true;
    }
}
