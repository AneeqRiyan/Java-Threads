#!/bin/bash

run_multi_threaded() {
    echo "Running Multi-threaded (Single Process) Application..."

    mvn clean compile package
    
    if [ $? -ne 0 ]; then
        echo "Maven build failed."
        exit 1
    fi

    mvn exec:java -Dexec.mainClass="org.Task360.MultiThreadedApp"
}

run_multi_process() {
    echo "Starting Multi-Process (Socket) Application..."

    mvn clean compile package
    
    if [ $? -ne 0 ]; then
        echo "Maven build failed."
        exit 1
    fi

    mvn exec:java -Dexec.mainClass="org.Task360.SocketServer" &
    mvn exec:java -Dexec.mainClass="org.Task360.SocketClient"
}

display_menu() {
    echo "Select the mode:"


    echo "1. Run Multi-threaded (Single Process) Application"
    echo "2. Run Multi-Process (Socket-based) Application"
    echo "3. Exit"
}

while true; do
    display_menu
    read -p "Enter your choice: " choice

    case $choice in
        1) run_multi_threaded ;;
        2) run_multi_process ;;
        3) echo "Exiting..."; exit 0 ;;
        *) echo "Invalid choice, please enter a valid option." ;;
    esac
done
