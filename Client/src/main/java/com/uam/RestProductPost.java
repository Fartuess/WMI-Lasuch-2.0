package com.uam;

import com.uam.Model.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by CLEVO on 2015-01-29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class RestProductPost
{
    @Autowired
    private RestTemplate restTemplate;

    public RestProductPost()
    {
        restTemplate = new RestTemplate();
        //restTemplate.getMessageConverters().add(new Jaxb2CollectionHttpMessageConverter<Collection>());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    //Not sure if check for errors or not.
    public boolean postProduct(Product product)
    {
        //Dunno how to do this exactly. Sleep ZZzzZZzz

        //restTemplate.postForObject();

        return true;
    }
}
