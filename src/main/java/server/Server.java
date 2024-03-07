package server;

import java.io.*;
import java.net.*;
import java.sql.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Connection connection;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client...");

            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String url = "jdbc:sqlite:/Users/syberlord/dbandserver/db/database.db";
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("addUser")) {
                    String[] userData = inputLine.split(" ");
                    if (userData.length == 3) {
                        String username = userData[1];
                        String password = userData[2];
                        addUser(username, password);
                        out.println("New user added");
                    } else {
                        out.println("Invalid command format");
                    }
                } else {
                    out.println("Unknown command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User added into db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
        //server.stop();
    }
}
