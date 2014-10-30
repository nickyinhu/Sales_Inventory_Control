package customer;

import system.SystemWindow;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CustomerRegister extends JInternalFrame {

    private JPanel mainPainel;
    private JLabel labelTitle, labelName, labelSsn, labelPhone, labelDateRegister, labelId;
    private JTextField textName, textSsn, textPhone, textDateRegister, textAdd;
    private JButton buttomCancel, buttomSave;

    public CustomerRegister() {
        mainPainel = new JPanel(null);

        /*TITULO*/
        labelTitle = new JLabel("CUSTOMER REGISTRATION");
        labelTitle.setSize(350, 50);
        labelTitle.setLocation(10, 5);
        labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPainel.add(labelTitle);


        labelName = new JLabel("NAME :");
        labelName.setSize(100, 30);
        labelName.setLocation(10, 65);

        labelSsn = new JLabel("SSN :");
        labelSsn.setSize(100, 30);
        labelSsn.setLocation(10, 100);

        labelPhone = new JLabel("PHONE :");
        labelPhone.setSize(100, 30);
        labelPhone.setLocation(10, 135);

        labelId = new JLabel("ADDRESS :");
        labelId.setSize(100, 30);
        labelId.setLocation(10, 170);


        labelDateRegister = new JLabel("DATE:");
        labelDateRegister.setSize(120, 30);
        labelDateRegister.setLocation(10, 205);

        mainPainel.add(labelName);
        mainPainel.add(labelSsn);
        mainPainel.add(labelPhone);
        mainPainel.add(labelDateRegister);
        mainPainel.add(labelId);

        textName = new JTextField();
        textName.setSize(400, 30);
        textName.setLocation(130, 65);
      
        textSsn = new JTextField();
        textSsn.setSize(160, 30);
        textSsn.setLocation(130, 100);
        
        textPhone = new JTextField();
        textPhone.setSize(160, 30);
        textPhone.setLocation(130, 135);
        
        
        
        
        MaskFormatter cpf; // Mask
        try {
            cpf = new javax.swing.text.MaskFormatter("###-##-####");
            textSsn = new javax.swing.JFormattedTextField(cpf);
            textSsn.setSize(160, 30);
            textSsn.setLocation(130, 100);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
        }


        MaskFormatter telefone; // Mask
        try {
            telefone = new javax.swing.text.MaskFormatter("(###)###-####");
            textPhone = new javax.swing.JFormattedTextField(telefone);
            textPhone.setSize(160, 30);
            textPhone.setLocation(130, 135);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        textAdd = new JTextField();
        textAdd.setSize(700, 30);
        textAdd.setLocation(130, 170);


        SystemWindow j = new SystemWindow();
        textDateRegister = new JTextField(j.dateString);
        textDateRegister.setSize(80, 30);
        textDateRegister.setLocation(130, 205);
        textDateRegister.setEditable(false);

        mainPainel.add(textName);
        mainPainel.add(textSsn);
        mainPainel.add(textPhone);
        mainPainel.add(textDateRegister);
        mainPainel.add(textAdd);

        buttomCancel = new JButton("NEW");
        buttomSave = new JButton("SAVE");

        buttomCancel.setSize(100, 30);
        buttomCancel.setLocation(620, 265);

        buttomSave.setSize(100, 30);
        buttomSave.setLocation(730, 265);

        mainPainel.add(buttomCancel);
        mainPainel.add(buttomSave);

        this.getContentPane().add(mainPainel);

        this.setTitle("Register of customers");
        this.setSize(800, 600);

        this.Eventos();
    }

    public void Eventos() {

        // Eventos dos Botões

        buttomCancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textName.setText("");
                        textSsn.setText("");
                        textPhone.setText("");
                        textAdd.setText("");
                        SystemWindow j = new SystemWindow();
                        textDateRegister.setText(j.dateString);
                        transferFocus();
                    }
                });

        buttomSave.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = textName.getText();
                        String ssn = textSsn.getText();
                        String telephone = textPhone.getText();
                        String address = textAdd.getText();
                        String date_register = textDateRegister.getText();

                        if (name == null || name.trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Customer must be Filled!", "ATTENTION", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        String sql = "";

                        sql += "INSERT INTO client (name, ssn, telephone, address, date_register) VALUES ";
                        sql += "('" + name + "', '" + ssn + "', '" + telephone + "', '" + address + "', '" + date_register + "');";

                        CustomerData cd = new CustomerData();
                        cd.register(sql);

                        if (cd.isRegistered) {
                            textName.setText("");
                            textSsn.setText("");
                            textPhone.setText("");
                            textAdd.setText("");
                            transferFocus();
                        }

                    }
                });
    }
}
