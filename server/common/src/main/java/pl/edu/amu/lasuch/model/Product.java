/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.model;

import java.util.Dictionary;
import java.util.Map;

/**
 *
 * @author Uczelnia
 */
public class Product {
    private int id;
    private String name;
    private String producer;
    private String seller;
    private String url;
    private Map<String, Double> ingredients;

    public Product(int id, String name, String producer, String seller, String url, Map<String, Double> ingredients) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.seller = seller;
        this.url = url;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }
    
    
}
