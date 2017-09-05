package com.reynke.school.ae09mvc.Controller;

import com.reynke.school.ae09mvc.Model.Product;
import com.reynke.school.ae09mvc.View.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicklas Reincke (contact@reynke.com)
 */
public class ProductController implements ActionListener {

    private Product product;
    private ProductView productView;

    public ProductController() {
        this.product = new Product();
        this.productView = new ProductView(this, this.product);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JTextField) {
            JTextField source = (JTextField) e.getSource();
            product.setName(source.getText());
        }

        this.productView.update();
    }

}
