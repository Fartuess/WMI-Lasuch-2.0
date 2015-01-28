package com.uam;

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

import java.util.Collection;

/**
 * Created by CLEVO on 2015-01-27.
 */

@ComponentScan
public class App extends Application
{
    public static void main(String[] args)
    {
        RestProductSearch searchClass = new RestProductSearch();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
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

        results.getChildren().add(new Button("sdsdsdds"));
        thirdLine.setContent(results);


        mainScene.getStylesheets().add("StyleSheet.css");

        searchButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                results.getChildren().clear();
                if (searchTypes.getValue() == "Nazwa")
                {
                    System.out.println("Searching by name");
                    System.out.println(searchField.getText());
                }
                if (searchTypes.getValue() == "Id")
                {
                    System.out.println("Searching by id");
                    System.out.println(searchField.getText());
                }
                if (searchTypes.getValue() == "Składnik")
                {
                    System.out.println("Searching by ingredient");
                    System.out.println(searchField.getText());
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
