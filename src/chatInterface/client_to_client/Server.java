package chatInterface.client_to_client;

import chatInterface.gui.MainFrame;
import chatInterface.gui.TextPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

    ServerSocket serverSocket;

    public Socket socket;

    ArrayList<ServerConnection> connections = new ArrayList<>();

    boolean shouldRun = true;

    String remoteUsername;

    public static int connexionCounter = 0;

    TextPanel textPanel;


    public Server() {

    }


    @Override
    public void run() {
        try {


            serverSocket = new ServerSocket(MainFrame.PORT);


            while (shouldRun) {
                System.out.println("Waiting for clients... FROM SERVER CLASS");
                socket = serverSocket.accept();
                ++connexionCounter;

                ServerConnection serverConnection = new ServerConnection(socket, this);
                connections.add(serverConnection);
                String username = readUsername(socket);
                serverConnection.setUsername(username);
                serverConnection.start();
                System.out.println("connexion " + connexionCounter);


//                textPanel.chatArea.append(remoteUsername + " is connected ! ");

            }


        } catch (IOException e) {
            System.out.println("PORT already Open");
            e.printStackTrace();
        }
    }


    private String readUsername(Socket socket) {
        try {


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            remoteUsername = in.readLine();

            //TODO: Do this in chatArea   checked
            System.out.println(remoteUsername + "  connected ");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return remoteUsername;
    }


    public static void main(String[] args) {
        Thread t = new Thread(new Server());
        t.start();
    }
}



