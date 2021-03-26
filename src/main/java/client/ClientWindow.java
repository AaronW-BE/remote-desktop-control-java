package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

enum ControlType{
    /**
     * 主控 受控
     */
    CONTROL,
    CONTROLLED,
}

/**
 * @author AaronW
 */
public class ClientWindow extends JFrame {

    private boolean isRunning = false;
    private DatagramSocket datagramSocket;
    private ControlType controlType;

    private String host = "127.0.0.1";
    private int port = 8848;

    public static void main(String[] args) throws IOException {
        new ClientWindow();
    }

    ClientWindow() {
        int windowWidth = 500;
        int windowHeight = 300;

        this.initComponent();
        this.setTitle("被控端");
        this.setSize(windowWidth, windowHeight);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(
                (int) (screenSize.getWidth() - windowWidth) / 2,
                (int) (screenSize.getHeight() - windowHeight) / 2
        );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    void initComponent() {
        this.setLayout(new FlowLayout());
        JButton listenButton = new JButton("接收连接");
        JButton connectButton = new JButton("连接");

        // 被控
        listenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlType = ControlType.CONTROLLED;
                if (isRunning) {
                    listenButton.setText("接收连接");

                } else {
                    isRunning = true;
                    listenButton.setText("停止");
                } 
            }
        });
        // 主控按钮
        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlType = ControlType.CONTROL;
                startConnect();
            }
        });

        this.add(listenButton);
        this.add(connectButton);
    }

    private void startConnect() {
        ScreenWindow screenWindow = null;
        try {
            screenWindow = new ScreenWindow();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        screenWindow.setVisible(true);
    }

}
