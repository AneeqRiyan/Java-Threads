package org.Task360;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.Task360.Constants.messageLimit;
import static org.Task360.Constants.serverPort;

/**
 * The main class to run the server in multi-process mode.
 */
public class SocketServer {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server is waiting for a connection...");
            Socket clientSocket = serverSocket.accept();
            PlayerSocket player1 = new PlayerSocket("Player 1", clientSocket);

            for (int i = 0; i < messageLimit; i++) {
                player1.sendMessage("Message from Player 1");
                player1.receiveMessage();
            }

            player1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}