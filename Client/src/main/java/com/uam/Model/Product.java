package com.uam.Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CLEVO on 2015-01-28.
 */

@XmlRootElement
public class Product
{
    public static final String URL = "PUT URL HERE";

    private String name;
    private String store;
    private List<String> ingredients;

    public Product()
    {

    }

    public Product(String name, String store, String... ingredients)
    {
        this.name = name;
        this.store = store;
        this.ingredients = Arrays.asList(ingredients);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStore()
    {
        return store;
    }

    public void setStore(String store)
    {
        this.store = store;
    }

    public List<String> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients)
    {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)   return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        //if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (name != null
            && !name.equals(product.name))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        return (name != null && store != null) ? (name + store).hashCode() : 0;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Product[");
        sb.append("name=").append(name);
        sb.append(", store=").append(store);
        //sb.append(", description=").append(description);
        sb.append(", ingredients=").append(ingredients);
        sb.append(']');
        return sb.toString();
    }
}
