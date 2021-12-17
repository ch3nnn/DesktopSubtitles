package cn.ch3nnn.desktopsubtitle;

import com.google.common.io.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * @author chentong
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final URL resource = Resources.getResource("sample.fxml");
        Parent root = FXMLLoader.load(resource);

        final int width = 1024;
        final int height = 50;

        final Scene scene = new Scene(root, width, height);
        scene.setFill(null);

        final Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - width) / 2);
        stage.setY((primaryScreenBounds.getHeight() - height));
        stage.setAlwaysOnTop(true);

        // 拖动监听器
        cn.ch3nnn.desktopsubtitle.DragUtil.addDragListener(stage, root);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}