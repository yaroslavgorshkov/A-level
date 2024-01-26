package ua.gorshkov.hw23.Ex2.Ex3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("The server is up and running. Waiting for connection...");

            try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> handleClient(clientSocket));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                Scanner in = new Scanner(clientSocket.getInputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            if (in.hasNextLine()) {
                String clientMessage = in.nextLine();
                System.out.println("Received from customer: " + clientMessage);
                String response = "Hey, client! I got your message: " + clientMessage;
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
