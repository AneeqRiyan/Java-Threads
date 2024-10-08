package org.Task360;
import java.io.*;
import java.net.*;

/**
 * Represents a player that communicates using sockets.
 */
public class PlayerSocket {
    private String name;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private int messageCounter = 0;

    /**
     * Constructor for SocketPlayer.
     */
    public PlayerSocket(String name, Socket socket) throws IOException {
        this.name = name;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Send a message to the connected player.
     */
    public void sendMessage(String message) {
        messageCounter++;
        out.println(message + " (Message " + messageCounter + ")");
        System.out.println(name + " sent: " + message + " (Message " + messageCounter + ")");
    }

    /**
     * Receive a message from the connected player.
     */
    public String receiveMessage() throws IOException {
        String message = in.readLine();
        System.out.println(name + " received: " + message);
        return message;
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
