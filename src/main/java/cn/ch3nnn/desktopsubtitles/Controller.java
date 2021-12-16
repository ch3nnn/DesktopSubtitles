package cn.ch3nnn.desktopsubtitles;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;


/**
 * @author chentong
 */
public class Controller implements Initializable {


    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        pool.execute(new Task(label));
        pool.shutdown();



    }
}
