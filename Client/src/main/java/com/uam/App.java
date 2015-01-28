package com.uam;

import com.uam.Model.Product;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CLEVO on 2015-01-27.
 */

@ComponentScan
public class App extends Application
{
    private RestProductSearch searchClass;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        searchClass = new RestProductSearch();

        VBox root = new VBox();
        Scene mainScene = new Scene(root, 800, 600);
        root.getStyleClass().add("container");

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("WMI Lasuch 2.0");

        HBox firstLine = new HBox();
        firstLine.getStyleClass().add("container");
        HBox secondLine = new HBox();
        secondLine.getStyleClass().add("container");
        final ScrollPane thirdLine = new ScrollPane();
        thirdLine.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thirdLine.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        thirdLine.setPrefHeight(Integer.MAX_VALUE);

        root.getChildren().add(firstLine);
        root.getChildren().add(secondLine);
        root.getChildren().add(thirdLine);

        final TextField searchField = new TextField();
        searchField.setPrefWidth(Integer.MAX_VALUE);
        final ComboBox<String> searchTypes = new ComboBox<String>();
        searchTypes.getItems().add("Nazwa");
        searchTypes.getItems().add("Id");
        searchTypes.getItems().add("Składnik");
        searchTypes.setValue("Nazwa");
        searchTypes.setMinWidth(150);
        firstLine.getChildren().add(searchField);
        firstLine.getChildren().add(searchTypes);

        Button searchButton = new Button("Search");

        secondLine.getChildren().add(searchButton);

        final VBox results = new VBox();
        results.getStyleClass().add("container");

        //results.getChildren().add(new Button("sdsdsdds"));
        List<String> testIngredients = new ArrayList<String>();
        testIngredients.add("sok pomarańczowy");
        testIngredients.add("E1234");
        Product testProduct = new Product(13, "sok", "to je sok pomarańczowy z pomarańcz", "", testIngredients);
        ProductItem testProductItem = new ProductItem(testProduct);
        results.getChildren().add(testProductItem);
        //results.getChildren().add(new Button("sdsdsdds"));
        thirdLine.setContent(results);


        mainScene.getStylesheets().add("StyleSheet.css");

        searchButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                boolean isChanged = false;
                List<Product> newResults;
                newResults = new ArrayList<Product>();

                results.getChildren().clear();
                if (searchTypes.getValue() == "Nazwa")
                {
                    isChanged = true;
                    System.out.println("Searching by name");
                    System.out.println(searchField.getText());
                    newResults = searchClass.searchByName(searchField.getText());
                }
                if (searchTypes.getValue() == "Id")
                {
                    isChanged = true;
                    System.out.println("Searching by id");
                    System.out.println(searchField.getText());
                    newResults = searchClass.searchById(searchField.getText());
                }
                if (searchTypes.getValue() == "Składnik")
                {
                    isChanged = true;
                    System.out.println("Searching by ingredient");
                    System.out.println(searchField.getText());
                    newResults = searchClass.searchByIngredient(searchField.getText());
                }

                if (isChanged)
                {
                    for (Product product : newResults)
                    {
                        ProductItem productItem = new ProductItem(product);
                        results.getChildren().add(productItem);
                    }
                }
            }
        });

        primaryStage.show();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getMessageConverters().add(new Jaxb2CollectionHttpMessageConverter<Collection>());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
