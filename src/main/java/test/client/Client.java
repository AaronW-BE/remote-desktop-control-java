package test.client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    private Socket clientSocket;


    public static void main(String[] args) throws IOException {
        new Client().startConnection("localhost", 8848);
    }
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(InetAddress.getByName(ip), port);
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        FileOutputStream fos=new FileOutputStream(new File("d:/test.gif"));
        while((len=inputStream.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fos.close();
        clientSocket.shutdownInput();
        clientSocket.shutdownOutput();
        inputStream.close();
        clientSocket.close();
    }



}
