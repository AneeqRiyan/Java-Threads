package org.Task360;

import java.io.IOException;
import java.net.Socket;

import static org.Task360.Constants.messageLimit;
import static org.Task360.Constants.serverPort;

/**
 * The main class to run the client in multi-process mode.
 */
public class SocketClient {

    public static void main(String[] args) {
        String host = "localhost";

        try (Socket socket = new Socket(host, serverPort)) {
            PlayerSocket player2 = new PlayerSocket("Player 2", socket);

            for (int i = 0; i < messageLimit; i++) {
                String message = player2.receiveMessage();
                player2.sendMessage("Response from Player 2 to " + message);
            }

            player2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}