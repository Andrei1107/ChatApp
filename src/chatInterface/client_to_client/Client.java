package chatInterface.client_to_client;

import chatInterface.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    ClientConnection clientConnection;
    MainFrame mainFrame;
    private String messageText;
    boolean stop = false;
    String localUsername;


    public Client(String ip, int port, MainFrame mainFrame) {
        try {
            this.mainFrame = mainFrame;
            Socket socket = new Socket(ip, port);
            localUsername = mainFrame.localUsername;
            clientConnection = new ClientConnection(socket, this, mainFrame.textPanel);

            clientConnection.start();


            sendStringFromClientToServer();

        } catch (UnknownHostException e) {
            System.err.println("Unknown host !!" + e.toString());
            mainFrame.disconnectClinet_if_Server_is_off();
        } catch (IOException e) {
            System.err.println("Port already in use! " + e.toString());
            mainFrame.disconnectClinet_if_Server_is_off();
        }

    }

    public void sendStringFromClientToServer() {


        sendUsernameToServer();



        while (!stop) {



            mainFrame.textPanel.sendBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (mainFrame.textPanel.textArea.getText() != null) {

                        messageText = mainFrame.textPanel.textArea.getText();
                        mainFrame.textPanel.textArea.setText("");

                        if (!messageText.equals("")) {

                            clientConnection.sendStringToServer(localUsername + " : " + messageText);



                        }
                    }
                }
            });

        }

    }

    private void sendUsernameToServer() {

        try {
            clientConnection.out.write(localUsername);
            clientConnection.out.newLine();
            clientConnection.out.flush();

//            informNewConnection();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void informNewConnection() {

        try {
            clientConnection.out.write(localUsername + " is connected!");
            clientConnection.out.newLine();
            clientConnection.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
