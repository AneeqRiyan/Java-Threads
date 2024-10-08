package org.Task360;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The main class to run the single process application.
 */
public class MultiThreadedApp {

    public static void main(String[] args) {
        Object lock = new Object(); // Synchronization lock for message order
        Player player1 = new Player("Player 1", lock,true);
        Player player2 = new Player("Player 2", lock,false);

        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        // Use executor to run both players as threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new PlayerThread(player1));
        executorService.submit(new PlayerThread(player2));

        // Shutdown the executor after completion
        executorService.shutdown();
    }
}
