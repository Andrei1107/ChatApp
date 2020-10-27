package chatInterface.gui;

import chatInterface._listeneres.ClientBoolListener;
import chatInterface._listeneres.ServerBoolListener;
import chatInterface._listeneres.ServerIPListener;
import chatInterface._listeneres.UsernameListener;
import chatInterface.client_to_client.Client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainFrame extends JFrame implements Runnable {

    private OptionsPanel optionsPanel;
    public TextPanel textPanel;
    private boolean isClosed = true;
    private boolean isServer = false;
    private boolean isCLient = false;


    public String localUsername;
    private String remoteUsername;
    public String serverIP;
    private ServerSocket serverSocket;
    private Socket client;
    private InetAddress address;
    private BufferedReader in;
    BufferedWriter out;
    public static final int PORT = 9090;



    private Client clientMode;


    public MainFrame() {
        super("Chat App");

        this.optionsPanel = new OptionsPanel(this);
        textPanel = new TextPanel(this);



        setLayout(new BorderLayout());
        setSize(750, 600);


        add(textPanel, BorderLayout.WEST);
        add(Box.createHorizontalStrut(100));
        add(optionsPanel, BorderLayout.BEFORE_FIRST_LINE);


        getLocalUsernameFromOptionPanel();

        getIpAddressFromOptionPanel();

        listenForClientButton();

        listenForServerCheckBox();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.isClosed = false;


    }


    public void getLocalUsernameFromOptionPanel() {
        //check if UsernameListener isn't null
        optionsPanel.setUsernameListener(new UsernameListener() {
            @Override
            public void textEmitted(String text) {
                localUsername = text;
            }
        });
    }

    public void getIpAddressFromOptionPanel() {
        optionsPanel.setServerIPListener(new ServerIPListener() {
            @Override
            public void stringEmitted(String ipAddress) {
                serverIP = ipAddress;

            }
        });
    }

    public void listenForServerCheckBox() {

        optionsPanel.setServerBoolListener(new ServerBoolListener() {
            @Override
            public void boolEmitted(boolean emittedBool) {
                isServer = emittedBool;
            }
        });
    }

    public void listenForClientButton() {
        optionsPanel.setClientBoolListener(new ClientBoolListener() {
            @Override
            public void boolEmitted(boolean emittedBool) {
                isCLient = emittedBool;
            }
        });

    }


    private void initializeClientMode() {
        validateUsername();

        System.out.println("I'm in Client MODE");

        String ip = optionsPanel.serverIpAddressField.getText();
        System.out.println(ip);
        clientMode = new Client(ip, PORT, this);

//        readIncomingMessages();

    }

    public void disconnectClinet_if_Server_is_off() {
        disconnect();
        isCLient = false;
        textPanel.chatArea.append("You are disconnected!! Try another connection! \n");
    }

    private void readIncomingMessages() {

        try {
            boolean isClosed = false;

            while (!isClosed) {

                String incomingMessage = in.readLine();
                textPanel.chatArea.append(remoteUsername + " : " + incomingMessage + "\n");

            }


        } catch (IOException e) {
            System.err.println(e.toString());
            clientOFF();
        }

    }

    private void clientOFF() {
        textPanel.chatArea.append("         \n" + remoteUsername + " is disconnected ..\n");
    }

    private void initializeServerMode() {

        validateUsername();
        System.out.println("I'm in Server MODE");

        try {


            serverSocket = new ServerSocket(PORT);
            client = serverSocket.accept();
            System.out.println("A ajuns aici 1");

            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("A ajuns aici 2");


            remoteUsername = in.readLine();

            out.write(localUsername);

            out.newLine();

            out.flush();

            textPanel.chatArea.append(remoteUsername + " is connected \n");

            textPanel.chatArea.setEditable(false);

            readIncomingMessages();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            disconnect();
        } catch (IOException e) {
            System.out.println("Port is open,Please Try another Port");
            System.err.println(e.toString());
            disconnect();
        }


    }


    @Override
    public void run() {

        while (!isClosed) {

            sleep(100);

            if (isServer) {

                initializeServerMode();
            }
            if (isCLient) {
                initializeClientMode();
            }

        }

    }


    public void sleep(int millis) {
        try {
            Thread.sleep(millis);

        } catch (InterruptedException e) {
            System.err.println(e.toString());
        }
    }

    public void validateUsername() {
        try {

            if (localUsername.equals("")) {
                localUsername = "Client";
            }
        } catch (NullPointerException e) {
            System.err.println(e.toString());
        }

    }


    private void disconnect() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }


    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }


    public MainFrame getMainFrame() {
        return this;
    }

  }

