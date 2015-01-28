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
    private int id;
    private String name;
    private String info;
    private String url;
    private List<String> ingredients;

    public Product(int id, String name, String info, String url, List<String> ingredients)
    {
        this.id = id;
        this.name = name;
        this.info = info;
        this.url = url;
        this.ingredients = ingredients;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
        return (name != null) ? (name + ((Integer)id).toString()).hashCode() : 0;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Product[");
        sb.append("id=").append(id);
        sb.append("name=").append(name);
        sb.append(", info=").append(info);
        //sb.append(", description=").append(description);
        sb.append(", ingredients=").append(ingredients);
        sb.append(']');
        return sb.toString();
    }
}
