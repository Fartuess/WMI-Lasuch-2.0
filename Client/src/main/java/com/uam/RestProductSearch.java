package com.uam;

import com.uam.Model.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

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

    public List<Product> searchByName(String name)
    {
        return null;
    }

    public List<Product> searchById(String id)
    {
        return null;
    }

    public List<Product> searchByIngredient(String ingredient)
    {
        return null;
    }
}
