package test.server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Server extends Thread {
    ServerSocket serverSocket;
    Socket clientSocket;

    public static void main(String[] args) throws IOException, AWTException {
        new Server();
    }

    public Server() throws IOException, AWTException {
        serverSocket = new ServerSocket(8848);
        clientSocket = serverSocket.accept();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(capture(), "jpg", outputStream);
        clientSocket.getOutputStream().write(outputStream.toByteArray());
        clientSocket.shutdownInput();
        clientSocket.shutdownOutput();
    }

    public BufferedImage capture() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        return robot.createScreenCapture(screenRectangle);
    }
}
