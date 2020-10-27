package chatInterface.gui;

import chatInterface._listeneres.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {


    private JCheckBox startServerCheckBox;
    private JLabel startServerLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel serverIpAddressLabel;
    public JTextField serverIpAddressField;
    private JButton connectBtn;

    private JSeparator separator;

    private ServerBoolListener serverBoolListener;
    private UsernameListener usernameListener;
    private ClientBoolListener clientBoolListener;
    private ServerIPListener serverIPListener;
    private StringListener stringListener;
    private ServerOnOffListener serverOnOffListener;
    private MainFrame mainFrame;

    public OptionsPanel(MainFrame mainFrame) {


        separator = new JSeparator();
        usernameLabel = new JLabel("Username");

        usernameField = new JTextField(10);
        usernameField.setText("mircea");
        startServerLabel = new JLabel("Start Server");
        startServerCheckBox = new JCheckBox();

        serverIpAddressLabel = new JLabel("Server IP Address:");


        serverIpAddressField = new JTextField(10);
        connectBtn = new JButton("Connect");

        serverIpAddressField.setText("127.0.0.1");
        this.mainFrame = mainFrame;

        add(usernameLabel, BorderLayout.EAST);
        add(usernameField, BorderLayout.EAST);

        add(Box.createHorizontalStrut(16));

        add(serverIpAddressLabel, BorderLayout.EAST);
        add(serverIpAddressField, BorderLayout.EAST);

        add(Box.createHorizontalStrut(13));
        add(startServerCheckBox, BorderLayout.EAST);
        add(startServerLabel, BorderLayout.EAST);

        add(Box.createHorizontalStrut(16));

        add(connectBtn, BorderLayout.SOUTH);

        startServer_CheckBox();

        connectButton_Action();


        setVisible(true);
    }


    private void initializePanel_sendVariables() {
        usernameListener.textEmitted(usernameField.getText());
        serverIPListener.stringEmitted(serverIpAddressField.getText());
        connectBtn.setEnabled(false);
        usernameField.setEnabled(false);
        serverIpAddressField.setEnabled(false);
        startServerCheckBox.setEnabled(false);
        stringListener.textEmitted("Waiting for remote client.....\n");
        serverBoolListener.boolEmitted(true);
    }

    private void startServer_CheckBox() {
        startServerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startServerCheckBox.isSelected()) {

                    initializePanel_sendVariables();


                } else {
                    stringListener.textEmitted("");
                    serverBoolListener.boolEmitted(false);
                    usernameField.setEnabled(true);
                    serverIpAddressField.setEnabled(true);
                    connectBtn.setEnabled(true);
                    System.out.println("Server is false!");
                }
            }


        });

    }


    private void connectButton_Action() {
        connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.textPanel.chatArea.setText("");
                serverIPListener.stringEmitted(serverIpAddressField.getText());
                usernameListener.textEmitted(usernameField.getText());
                clientBoolListener.boolEmitted(true);
                usernameField.setEnabled(false);
                serverIpAddressField.setEnabled(false);
                startServerCheckBox.setEnabled(false);
            }
        });

    }


    public void setServerBoolListener(ServerBoolListener serverBoolListener) {
        this.serverBoolListener = serverBoolListener;
    }

    public void setUsernameListener(UsernameListener usernameListener) {
        this.usernameListener = usernameListener;
    }


    public void setClientBoolListener(ClientBoolListener clientBoolListener) {
        this.clientBoolListener = clientBoolListener;
    }


    public void setServerIPListener(ServerIPListener serverIPListener) {
        this.serverIPListener = serverIPListener;
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;

    }


}
