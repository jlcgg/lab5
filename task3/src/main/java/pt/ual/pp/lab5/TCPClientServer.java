package pt.ual.pp.lab5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class TCPClient {
    public TCPClient() throws IOException {
        final Socket socket = new Socket("127.0.0.1", 4242);
        try {
            final var printWriter = new PrintWriter(socket.getOutputStream());
            final var scanner = new Scanner(socket.getInputStream());
            printWriter.println("ping");
            printWriter.flush();
            System.out.println("Server replied: " + scanner.nextLine());
            printWriter.close();
            scanner.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class TCPServer {
    public TCPServer() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(4242);
        var count = 0;
        while(count < 3) {
            count++;
            final Socket socket = serverSocket.accept();
//            new TCPServerThread(socket).start();
            new Thread(() -> {
                try {
                    final var printWriter = new PrintWriter(socket.getOutputStream());
                    final var scanner = new Scanner(socket.getInputStream());
                    System.out.println("Client sent: " + scanner.nextLine());
                    printWriter.println("pong");
                    printWriter.flush();
                    printWriter.close();
                    scanner.close();
                    socket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        serverSocket.close();
    }
}

//class TCPServerThread extends Thread {
//    private final Socket socket;
//
//    public TCPServerThread(final Socket socket) {
//        this.socket = socket;
//    }
//
//    @Override
//    public void run() {
//        try {
//            final var printWriter = new PrintWriter(socket.getOutputStream());
//            final var scanner = new Scanner(socket.getInputStream());
//            System.out.println("Client sent: " + scanner.nextLine());
//            printWriter.println("pong");
//            printWriter.flush();
//            printWriter.close();
//            scanner.close();
//            socket.close();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class TCPClientServer {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new TCPServer();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }).start();
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    new TCPClient();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}