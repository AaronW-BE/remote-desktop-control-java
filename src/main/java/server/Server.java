package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author AaronW
 */
public class Server {
    private ServerSocket serverSocket;
    private int port = 8848;

    public static void main(String[] args) throws IOException {
        new Server().service();
    }

    void service() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String str = in.readUTF();
                System.out.println("Client: " + str);
                System.out.print("Say: ");
                String replay = scanner.nextLine();
                out.writeUTF(replay);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
