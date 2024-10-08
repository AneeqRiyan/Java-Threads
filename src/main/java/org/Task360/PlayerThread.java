package org.Task360;

/**
 * Represents a player thread for concurrent message exchange.
 */
public class PlayerThread implements Runnable {
    private final Player player;

    /**
     * Constructor for PlayerThread.
     */
    public PlayerThread(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        // Start the communication by sending the first message
        if (player.getInitiator()) {
            player.sendMessage("Hello from " + player.toString());
        }
    }
}
