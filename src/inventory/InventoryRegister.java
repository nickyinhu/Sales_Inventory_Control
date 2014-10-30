package inventory;

import system.SystemWindow;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InventoryRegister extends JInternalFrame {

    private JPanel mainPainel;
    private JLabel labelTitle, labelName, labelQuantity, labelDate;
    private JComboBox comboProduct;
    private JTextField TextQuantidade, TextData;
    private JButton buttomNew, buttomSave;

    public InventoryRegister() {

        mainPainel = new JPanel(null);

        labelTitle = new JLabel("REGISTER IN STOCK");
        labelTitle.setSize(300, 50);
        labelTitle.setLocation(10, 5);
        labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));


        labelName = new JLabel("PRODUCT :");
        labelName.setSize(100, 30);
        labelName.setLocation(10, 60);


        comboProduct = new JComboBox();
        comboProduct.setSize(300, 30);
        comboProduct.setLocation(170, 60);

        comboProduct.addItem("");

        /***************** Getiing the values of database to put in a combo *******************/
        InventoryData inventory = new InventoryData();
        inventory.readAll();
        try {
            while (inventory.list.next()) {
                comboProduct.addItem(inventory.list.getArray("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inventory.stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

        /****************************************************************************************************/
        labelQuantity = new JLabel("QUANTITY :");
        labelQuantity.setSize(100, 30);
        labelQuantity.setLocation(500, 60);


        TextQuantidade = new JTextField();
        TextQuantidade.setSize(60, 30);
        TextQuantidade.setLocation(605, 60);

        labelDate = new JLabel("DATE OF STOCK :");
        labelDate.setSize(150, 30);
        labelDate.setLocation(10, 100);

        SystemWindow j = new SystemWindow();
        TextData = new JTextField(j.dateString);
        TextData.setSize(80, 30);
        TextData.setLocation(170, 100);


        buttomNew = new JButton("NEW");
        buttomSave = new JButton("SAVE");

        buttomNew.setSize(100, 30);
        buttomNew.setLocation(455, 140);

        buttomSave.setSize(100, 30);
        buttomSave.setLocation(570, 140);


        mainPainel.add(labelTitle);

        mainPainel.add(comboProduct);

        mainPainel.add(labelName);
        mainPainel.add(labelQuantity);
        mainPainel.add(labelDate);

        mainPainel.add(TextQuantidade);
        mainPainel.add(TextData);

        mainPainel.add(buttomNew);
        mainPainel.add(buttomSave);


        this.getContentPane().add(mainPainel);

        setTitle("Register Product in stock");
        this.setSize(800, 600);

        this.Events();

    }

    public void Events() {

        buttomNew.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TextQuantidade.setText("");
                        transferFocus();
                    }
                });

        buttomSave.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        Object name = comboProduct.getSelectedItem();
                        String quantity = TextQuantidade.getText();
                        String date = TextData.getText();
                        quantity = quantity.replace(" ", "");

                        InventoryData stock = new InventoryData();
                        stock.getId(name);

                        String sql2 = "";
                        int id = 0;
                        try {
                            while (stock.product_id.next()) {
                                id = stock.product_id.getInt("id");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        sql2 += "INSERT INTO stock (product_id,quantity,date_register) ";
                        sql2 += "VALUES ( '" + id + "', '" + quantity + "', '" + date + "' )";
                        stock.register(sql2);

                        try {
                        	stock.stm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        TextQuantidade.setText("");

                        // ATUALIZAR A COMBO
                        comboProduct.removeAllItems();
                        comboProduct.addItem("");
                        InventoryData inventory = new InventoryData();
                        inventory.readAll();
                        try {
                            while (inventory.list.next()) {
                                comboProduct.addItem(inventory.list.getArray("name"));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                        	stock.stm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(InventoryRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        transferFocus();

                    }
                });
    }
}
