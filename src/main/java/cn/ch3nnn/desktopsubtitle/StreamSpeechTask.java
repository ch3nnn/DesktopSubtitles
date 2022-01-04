package cn.ch3nnn.desktopsubtitle;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.scene.control.Label;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author chentong
 */
public class StreamSpeechTask implements Runnable {

    public Session session;
    public static Label label;

    public StreamSpeechTask(Label label) {
        StreamSpeechTask.label = label;
    }

    public static void setText(String s) {
        final Result result = JSON.parseObject(s, Result.class);
        final Result.ResultDTO resultDTO = result.getResult();
        if (resultDTO.getPartial() != null && resultDTO.getPartial()) {
            final String tranContent = resultDTO.getTranContent();
            Platform.runLater(() -> label.setText(tranContent));
        }
    }

    public void start(String uri) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            session = container.connectToServer(Websocket.class, URI.create(uri));
            System.out.println(session.getContainer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doAsrWebSocketClient(String uri, Integer step) {
        start(uri);
        try {
            // 读取麦克风数据
            AudioFormat audioFormat = new AudioFormat(16000.0F, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            Platform.runLater(() -> label.setText("You can speak now!"));
            final int buffSize = 6400;
            byte[] buffer = new byte[buffSize];
            while (targetDataLine.read(buffer, 0, buffSize) > 0) {
                // Step4 直接发送麦克风数据流
                session.getBasicRemote().sendBinary(ByteBuffer.wrap(buffer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void streamSpeech(String appKey, String appSecret, String from, String to) throws NoSuchAlgorithmException {
        String nonce = UUID.randomUUID().toString();
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String signStr = appKey + nonce + curtime + appSecret;
        String sign = encrypt(signStr, null);

        String uri = "wss://openapi.youdao.com/stream_speech_trans?appKey=" + appKey + "&salt=" + nonce + "&curtime=" + curtime + "&sign=" + sign + "&version=v1&channel=1&format=wav&signType=v4&rate=16000&from=" + from + "&to=" + to + "&transPattern=stream";
        doAsrWebSocketClient(uri, 6400);
    }

    /**
     * 获取MessageDigest的加密结果
     *
     * @param strSrc
     * @param encName
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String strSrc, String encName) throws NoSuchAlgorithmException {
        byte[] bt = strSrc.getBytes();
        if (encName == null || "".equals(encName)) {
            encName = "SHA-256";
        }
        MessageDigest md = MessageDigest.getInstance(encName);
        md.update(bt);
        // to HexString
        return bytes2Hex(md.digest());
    }

    public static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    @Override
    public void run() {
        // TODO 申请智能云 appKey appSecret
        String from = "源语言";
        String to = "目标语言";
        String appKey = "您的应用ID";
        String appSecret = "您的应用密钥";
        try {
            streamSpeech(appKey, appSecret, from, to);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}



