package chatInterface.gui;

import chatInterface._listeneres.SendMessage;
import chatInterface._listeneres.StringListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends JPanel {

    public JTextArea chatArea;

    public JTextArea textArea;


    public JButton sendBtn;

    private MainFrame mainFrame;

    private StringListener stringListener;

    private OptionsPanel optionsPanel;
    private Font font;
    private Font f2;
    SendMessage sendMessage;
    private String messageText;


    public TextPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;


        Dimension dim = getPreferredSize();

        dim.width = 700;

        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Chat--");
        Border outerBorder = BorderFactory.createEmptyBorder();


        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


        chatArea = new JTextArea(15, 63);
        textArea = new JTextArea(6, 63);
        sendBtn = new JButton("Send");


        font = textArea.getFont();
        f2 = new Font(font.getFontName(), font.getStyle(), font.getSize() + 2);
        chatArea.setFont(f2);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        add(Box.createHorizontalStrut(100));
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        add(sendBtn, BorderLayout.LINE_END);


        this.optionsPanel = this.mainFrame.getOptionsPanel();


        optionsPanel.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                chatArea.setText(text);
            }
        });


    }

    public void appendText(String text) {
        this.chatArea.append(text);
    }

    public void setChatText(String text) {
        this.chatArea.setText(text);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }
}
