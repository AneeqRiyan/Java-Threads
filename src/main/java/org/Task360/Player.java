package org.Task360;


import static org.Task360.Constants.messageLimit;

/**
 *  Represents a Player in the communication game.
 * Handles sending and receiving messages.
 */
public class Player {
    private final String name;
    private int messageCounter;
    private final Object lock; // Lock object for synchronization
    private Player otherPlayer;
    private boolean isInitiator;

    /**
     * Constructor for Player.
     */
    public Player(String name, Object lock,boolean isInitiator) {
        this.name = name;
        this.lock = lock;
        this.messageCounter = 0;
        this.isInitiator = isInitiator;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    /**
     * Send a message to the other player.
     * The message is concatenated with the current counter.
     */
    public void sendMessage(String message) {
        synchronized (lock) {
            messageCounter++;
            System.out.println(name + " sent: " + message + " (Message " + messageCounter + ")");
            if (otherPlayer != null) {
                otherPlayer.receiveMessage(message + " (Response " + messageCounter + ")");
            }
        }
    }

    /**
     * Receive a message and respond to it.
     */
    public void receiveMessage(String message) {
        synchronized (lock) {
            System.out.println(name + " received: " + message);
            if (messageCounter < messageLimit) { // Stop condition
                sendMessage("Message from " + name);
            }
        }
    }

    /**
     * Getter method to check if the player object is the initiator
     */
    public Boolean getInitiator() {
        return isInitiator;
    }
}
