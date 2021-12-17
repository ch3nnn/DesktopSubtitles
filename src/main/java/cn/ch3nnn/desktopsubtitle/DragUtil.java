package cn.ch3nnn.desktopsubtitle;


import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Created by yi-ge
 * 2018-12-21 22:13
 * @author  yi-ge
 */
public class DragUtil {
    public static void addDragListener(Stage stage, Node root) {
        new cn.ch3nnn.desktopsubtitle.DragListener(stage).enableDrag(root);
    }
}