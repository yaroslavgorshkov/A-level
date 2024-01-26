package ua.gorshkov.hw23.Ex2.Ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("The server is up and running. Waiting for connection...");

            try (Socket clientSocket = serverSocket.accept();
                 Scanner in = new Scanner(clientSocket.getInputStream());
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected.");
                String clientMessage;
                while (true) {
                    try {
                        if (in.hasNextLine()) {
                            clientMessage = in.nextLine();
                            System.out.println("Received from customer: " + clientMessage);
                            String response = "Hey, client! I got your message: " + clientMessage;
                            out.println(response);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
