package com.uam;

import com.uam.Model.Product;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private RestProductPost postClass;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        searchClass = new RestProductSearch();
        postClass = new RestProductPost();

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
        Button addButton = new Button("Add new item");

        secondLine.getChildren().add(searchButton);
        secondLine.getChildren().add(addButton);

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
                    int i = 0;
                    for (Product product : newResults)
                    {
                        if (i > 20) break;
                        ProductItem productItem = new ProductItem(product);
                        results.getChildren().add(productItem);
                        i++;
                    }
                }
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                VBox newRoot = new VBox();
                newRoot.getStyleClass().add("container");
                Scene newScene = new Scene(newRoot, 400, 300);
                //newRoot.getChildren().add(new Label("testerino"));
                final Stage newWindow = new Stage();
                newWindow.setScene(newScene);
                newWindow.setTitle("Add new Product");

                VBox namePane = new VBox();
                namePane.getStyleClass().add("container");
                Label nameLabel = new Label("Name:");
                final TextField nameTextField = new TextField();
                namePane.getChildren().add(nameLabel);
                namePane.getChildren().add(nameTextField);

                VBox infoPane = new VBox();
                infoPane.getStyleClass().add("container");
                Label infoLabel = new Label("Info:");
                final TextArea infoTextField = new TextArea();
                infoTextField.setPrefHeight(Integer.MAX_VALUE);
                infoPane.getChildren().add(infoLabel);
                infoPane.getChildren().add(infoTextField);

                VBox ingredientsPane = new VBox();
                ingredientsPane.getStyleClass().add("container");

                HBox buttonPane = new HBox();
                buttonPane.getStyleClass().add("container");
                Button okButton = new Button("Ok");
                Button cancelButton = new Button("Cancel");
                buttonPane.getChildren().add(okButton);
                buttonPane.getChildren().add(cancelButton);

                newRoot.getChildren().add(namePane);
                newRoot.getChildren().add(infoPane);
                newRoot.getChildren().add(buttonPane);

                newScene.getStylesheets().add("StyleSheet.css");

                cancelButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        newWindow.close();
                    }
                });

                okButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        int id = 0; //temp
                        String url = "";    //temp
                        Product product = new Product(id, nameTextField.getText(), infoTextField.getText(), url, new ArrayList<String>());  //temp;

                        postClass.postProduct(product);

                        newWindow.close();
                    }
                });

                newWindow.show();
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
