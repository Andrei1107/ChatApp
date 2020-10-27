package chatInterface.client_to_client;

import chatInterface.gui.TextPanel;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {

    Socket socket;
    BufferedReader in;
    BufferedWriter out;
    Client client;
    boolean shouldRun = true;
    TextPanel textPanel;

    public ClientConnection(Socket s, Client client, TextPanel textPanel) {
        this.socket = s;
        this.client = client;
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.textPanel=textPanel;
    }


    public void sendStringToServer(String text) {
        try {

            out.write(text);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            System.err.println(e.toString());
            close();
        }
    }

    @Override
    public void run() {
        try {


            while (shouldRun) {
                sleep(1);
                String reply = in.readLine();

                textPanel.chatArea.append(reply+" \n");


            }


        } catch (IOException e) {
            System.err.println(e.toString());

            close();
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
