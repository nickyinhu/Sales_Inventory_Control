// THIS PAGE CAN BE USED TO LOGIN PAGE. IT's WITH THE INTERFACE DONE. THERE IS A USER WITH A PASSWORD JUST LIKE A STRING TO BE REPLACED LATER BY USERS IN DATABASE
package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JDialog {

    JPanel mainPainel;
    JLabel labelName, labelPassword, labelTitle, labelTitle2;
    JTextField textName, textPassword;
    JButton buttomEnter;
    //--user and password using a fake login--/
    private String nome = "system";
    private String senha = "123456";

    public Login() {
        mainPainel = new JPanel(null);

        labelTitle2 = new JLabel("BEST SALES");
        labelTitle2.setSize(600, 30);
        labelTitle2.setLocation(310, 30);
        labelTitle2.setFont(new Font("Verdana", Font.BOLD, 30));
        labelTitle2.setForeground(Color.blue);



        labelTitle = new JLabel("LOGIN");
        labelTitle.setSize(200, 40);
        labelTitle.setLocation(380, 100);
        labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        labelTitle.setForeground(Color.blue);


        labelName = new JLabel("USER :");
        labelName.setSize(80, 20);
        labelName.setLocation(300, 150);

        textName = new JTextField();
        textName.setSize(80, 20);
        textName.setLocation(380, 150);


        labelPassword = new JLabel("PASSWD :");
        labelPassword.setSize(80, 20);
        labelPassword.setLocation(300, 180);


        textPassword = new JPasswordField();
        textPassword.setSize(80, 20);
        textPassword.setLocation(380, 180);

        buttomEnter = new JButton("ENTER");
        buttomEnter.setSize(80, 20);
        buttomEnter.setLocation(380, 220);

        mainPainel.add(labelTitle2);
        mainPainel.add(labelTitle);
        mainPainel.add(labelName);
        mainPainel.add(textName);
        mainPainel.add(labelPassword);
        mainPainel.add(textPassword);
        mainPainel.add(buttomEnter);
        

        this.getContentPane().add(mainPainel);

        this.setTitle("        SYSTEM LOGIN");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.Events();

    }

    public void Events() {
        // Events of the window JFrame
        this.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });


        buttomEnter.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        logging();
                    }
                });



        textName.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            textName.transferFocus();
                        }
                    }
                });

        textPassword.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            logging();
                            buttomEnter.transferFocus();
                        }
                    }
                });

    }

    public void logging() {
        //Verifying is same field is null
        if (textName.getText() == null ? "" == null : textName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "USER EMPTY !", "ERROR LOGIN", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (textPassword.getText() == null ? "" == null : textPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "PASSWORD EMPTY !", "ERROR LOGIN", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (textName.getText().equals(nome) && textPassword.getText().equals(senha)) {
            SystemWindow j = new SystemWindow();
            Login l = new Login();
            setVisible(false);
            j.setVisible(true);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "INVALID DATA, CHECK THE FIELDS TO LOGIN!", "ERROR LOGIN", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
}
