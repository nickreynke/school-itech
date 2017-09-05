package com.reynke.school.ae09mvc.View;

import com.reynke.school.ae09mvc.Controller.ProductController;
import com.reynke.school.ae09mvc.Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class ProductView extends JFrame {

    private JLabel label;

    private Product product;

    public ProductView(ProductController productController, Product product) {

        this.label = new JLabel();

        this.product = product;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 250, 176);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);

        JTextField textField = new JTextField();
        contentPane.add(textField, BorderLayout.NORTH);
        textField.addActionListener(productController);

        contentPane.add(this.label, BorderLayout.CENTER);

        this.update();
        this.setVisible(true);
    }

    public void update() {
        this.label.setText(this.product.getName());
    }

}
