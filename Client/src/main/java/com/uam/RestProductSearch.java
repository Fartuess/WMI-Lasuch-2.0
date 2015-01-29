package com.uam;

import com.uam.Model.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CLEVO on 2015-01-28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class RestProductSearch
{
    @Autowired
    private RestTemplate restTemplate;

    public RestProductSearch()
    {
        restTemplate = new RestTemplate();
        //restTemplate.getMessageConverters().add(new Jaxb2CollectionHttpMessageConverter<Collection>());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public List<Product> searchByName(String name)
    {
        //List<Product> resultList = new ArrayList<Product>();

        List<Product> resultList = restTemplate.getForObject("http://localhost:8080/products/name?name=" + name, List.class);

        return resultList;
    }

    public List<Product> searchById(String id)
    {
        List<Product> resultList = new ArrayList<Product>();

        //List<Product> resultList = restTemplate.getForObject("http://localhost:8080/products/" + id, List.class);
        resultList.add(restTemplate.getForObject("http://localhost:8080/products/" + id, Product.class));

        return resultList;
    }

    public List<Product> searchByIngredient(String ingredient)
    {
        //List<Product> resultList = new ArrayList<Product>();

        List<Product> resultList = restTemplate.getForObject("http://localhost:8080/products/search?q=" + ingredient, List.class);

        return resultList;
    }
}
