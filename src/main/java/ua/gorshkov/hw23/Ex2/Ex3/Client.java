package ua.gorshkov.hw23.Ex2.Ex3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Hello from client!");
            String serverResponse = in.nextLine();
            System.out.println("Server response: " + serverResponse);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}