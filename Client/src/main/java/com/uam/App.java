package com.uam;

import javafx.application.*;
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

/**
 * Created by CLEVO on 2015-01-27.
 */
public class App extends javafx.application.Application
{
    public static void main(String[] args)
    {
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
        ScrollPane thirdLine = new ScrollPane();
        thirdLine.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thirdLine.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        thirdLine.setPrefHeight(Integer.MAX_VALUE);

        root.getChildren().add(firstLine);
        root.getChildren().add(secondLine);
        root.getChildren().add(thirdLine);

        TextField searchField = new TextField();
        searchField.setPrefWidth(Integer.MAX_VALUE);
        ComboBox<String> searchTypes = new ComboBox<String>();
        searchTypes.getItems().add("Nazwa");
        searchTypes.getItems().add("Sklep");
        searchTypes.getItems().add("Składnik");
        searchTypes.setValue("Nazwa");
        searchTypes.setMinWidth(150);
        firstLine.getChildren().add(searchField);
        firstLine.getChildren().add(searchTypes);

        Button searchButton = new Button("Search");
        secondLine.getChildren().add(searchButton);

        VBox results = new VBox();
        results.getStyleClass().add("container");

        results.getChildren().add(new Button("sdsdsdds"));
        thirdLine.setContent(results);


        mainScene.getStylesheets().add("StyleSheet.css");

        primaryStage.show();
    }
}
