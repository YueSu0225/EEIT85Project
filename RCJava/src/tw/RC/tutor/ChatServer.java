package tw.RC.tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<Socket> clientSockets = new HashSet<>();
    private static JTextArea messageArea;
    private static JTextField messageField;

    public static void main(String[] args) throws IOException {
        // 设置服务器窗口
        JFrame frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        frame.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        messageField = new JTextField();
        frame.add(messageField, BorderLayout.SOUTH);

        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    broadcastMessage("Server: " + message);
                    messageArea.append("Server: " + message + "\n");
                    messageField.setText("");
                }
            }
        });

        frame.setVisible(true);

        // 启动服务器
        ServerSocket serverSocket = new ServerSocket(12345);
        messageArea.append("Server started on port 12345...\n");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            new ClientHandler(clientSocket).start();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    messageArea.append("Client: " + message + "\n");
                    broadcastMessage("Client says: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    clientSockets.remove(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void broadcastMessage(String message) {
        for (Socket socket : clientSockets) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}