package product;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductRegister extends JInternalFrame {

    private JPanel mainPainel;
    private JLabel labelTitle, labelName, labelPriceBuy, labelPriceSale, labelDescription;
    private JTextField textName, textBuy, textSale, textoDescription;
    private JButton buttomNew, buttomSave;
    

    public ProductRegister() {
        mainPainel = new JPanel(null);

        labelTitle = new JLabel("PRODUCT REGISTRATION");
        labelTitle.setSize(300, 50);
        labelTitle.setLocation(10, 5);
        labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPainel.add(labelTitle);


        labelName = new JLabel("NAME :");
        labelName.setSize(100, 30);
        labelName.setLocation(10, 65);

        labelPriceBuy = new JLabel("PURCHASE PRICE $ :");
        labelPriceBuy.setSize(180, 30);
        labelPriceBuy.setLocation(10, 100);

        labelPriceSale = new JLabel("SALE PRICE $ :");
        labelPriceSale.setSize(180, 30);
        labelPriceSale.setLocation(10, 135);

        labelDescription = new JLabel("DESCRIPTION :");
        labelDescription.setSize(120, 30);
        labelDescription.setLocation(10, 170);

        mainPainel.add(labelName);
        mainPainel.add(labelPriceBuy);
        mainPainel.add(labelPriceSale);
        mainPainel.add(labelDescription);

        textName = new JTextField();
        textName.setSize(230, 30);
        textName.setLocation(200, 65);

        textBuy = new JTextField();
        textBuy.setSize(230, 30);
        textBuy.setLocation(200, 100);

        textSale = new JTextField();
        textSale.setSize(230, 30);
        textSale.setLocation(200, 135);

        textoDescription = new JTextField();
        textoDescription.setSize(600, 30);
        textoDescription.setLocation(200, 170);

        mainPainel.add(textName);
        mainPainel.add(textBuy);
        mainPainel.add(textSale);
        mainPainel.add(textoDescription);

        buttomNew = new JButton("NEW");
        buttomSave = new JButton("SAVE");

        buttomNew.setSize(100, 30);
        buttomNew.setLocation(580, 240);

        buttomSave.setSize(100, 30);
        buttomSave.setLocation(700, 240);


        mainPainel.add(buttomNew);
        mainPainel.add(buttomSave);

        this.getContentPane().add(mainPainel);

        this.setTitle("Registration of Products");
        this.setSize(800, 600);

        this.Events();
    }

    public void Events() {
        buttomNew.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textName.setText("");
                        textBuy.setText("");
                        textSale.setText("");
                        textoDescription.setText("");
                        transferFocus();
                    }
                });

        buttomSave.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = textName.getText();
                        String purchase = textBuy.getText();
                        String sale = textSale.getText();
                        String description = textoDescription.getText();
                        purchase = purchase.replace(',', '.');
                        sale = sale.replace(',', '.');

                        String sql = "";

                        sql += "INSERT INTO product (name, price_cost, price_sale, description) VALUES ";
                        sql += "('" + name + "', '" + purchase + "', '" + sale + "', '" + description + "');";

                        ProductData pdao = new ProductData();
                        pdao.register(sql);

                        if (pdao.clean_texts == 1) {
                            textName.setText("");
                            textBuy.setText("");
                            textSale.setText("");
                            textoDescription.setText("");
                        }
                        transferFocus();
                    }
                });

    }
}