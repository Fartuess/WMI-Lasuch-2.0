/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.restServer.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.lasuch.model.Product;

/**
 *
 * @author Uczelnia
 */

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> products(){
        List<Product> result = new ArrayList<Product>();
        
        return result;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) {
        Product result = new Product(1, "test", "test", "test", "test", null);
        return result;
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<Product> search(@RequestParam("q") String query) {
        List<Product> result = new ArrayList<Product>();
        System.out.print(query);
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        
        return product;
    }
}
