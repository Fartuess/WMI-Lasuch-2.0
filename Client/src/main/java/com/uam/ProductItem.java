package com.uam;

import com.uam.Model.Product;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by CLEVO on 2015-01-29.
 */
public class ProductItem extends VBox
{
    public ProductItem(Product product)
    {
        this.getChildren().add(new Label(product.getName()));
        this.getChildren().add(new Label(product.getInfo()));
        this.getChildren().add(new Label(product.getIngredients().toString()));
    }
}
