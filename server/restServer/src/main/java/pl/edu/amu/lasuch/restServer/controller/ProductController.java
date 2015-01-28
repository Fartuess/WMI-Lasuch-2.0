/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.restServer.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.amu.edu.lasuch.restServer.service.SolrUpdateService;
import pl.edu.amu.lasuch.model.Product;
import pl.edu.amu.lasuch.solr.Doc;
import pl.edu.amu.lasuch.solr.SolrConnection;

/**
 *
 * @author Uczelnia
 */

@RestController
@RequestMapping("/products")
public class ProductController {
    private final SolrConnection solrConnection = new SolrConnection();
    @Autowired
    private ApplicationContext context;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> products() throws URISyntaxException, IOException{
        List<Product> result = new ArrayList<Product>();
        List<Doc> docs = solrConnection.GetAll();
        for (Doc d : docs) {
            Product p = new Product(d.getID(), d.getTitle(), d.getInfo(), d.getUrl(), d.getIngredient());
            result.add(p);
        }
        
        return result;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) throws MalformedURLException, IOException {
        Doc doc = solrConnection.findOneProduct("ID", Integer.toString(id));
        return new Product(doc.getID(), doc.getTitle(), doc.getInfo(), doc.getUrl(), doc.getIngredient());
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<Product> search(@RequestParam("q") String query) throws URISyntaxException, IOException {
        List<Product> result = new ArrayList<Product>();
        
        List<Doc> docs = solrConnection.searchProducts("ingredient", query);
        for (Doc d : docs) {
            Product p = new Product(d.getID(), d.getTitle(), d.getInfo(), d.getUrl(), d.getIngredient());
            result.add(p);
        }
        
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        SolrUpdateService solrUpdateService = (SolrUpdateService) context.getBean("solrUpdateService");
        return product;
    }
}
