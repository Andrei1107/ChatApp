package chatInterface.client_to_client;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread {

    Socket socket;
    Server server;
    BufferedReader in;
    BufferedWriter out;
    boolean shouldRun = true;
    public static boolean isDisconnected;
    String username;


    public ServerConnection(Socket socket, Server server) {

        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;

    }


    public void sendStringToClient(String text) {
        try {
            if (out != null) {
                out.write(text);
                out.newLine();
                out.flush();
            }

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Stream closed !!!!");


        }
    }

    public void sendStringToAllClients(String text) {
        for (int i = 0; i < server.connections.size(); i++) {

            ServerConnection serverConnection = server.connections.get(i);
            serverConnection.sendStringToClient(text);


        }
    }


    @Override
    public void run() {

        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while (shouldRun) {
                sleep(1);

                try {
                    String textIn = in.readLine();

                    sendStringToAllClients(textIn);


                } catch (IOException e) {

                    isDisconnected = true;

                    socket.close();

                    System.out.println("Socket closed");

                    System.out.println(username + " disconnected");


                    for (int i = 0; i < server.connections.size(); i++) {
                        if (server.connections.get(i).socket.isClosed()) {
                            System.out.println("It's an abandoned connexion,We need to remove it!");
                            server.connections.remove(i);
                            Server.connexionCounter--;
                            System.out.println("List refreshed  -> Active members -> " + server.connections.size());

                            if (isDisconnected) {
                                sendStringToAllClients(username + " is disconnected from server");
                            }
                        }

                    }


                    shouldRun = false;

                }


            }

            disconnect();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setUsername(String username) {
        this.username = username;
    }


    private void disconnect() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
