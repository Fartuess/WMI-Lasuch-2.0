/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.model;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Uczelnia
 */
public class Product {
    private int id;
    private String name;
    private String info;
    private String url;
    private List<String> ingredients;

    public Product(int id, String name, String info, String url, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    
    
}
