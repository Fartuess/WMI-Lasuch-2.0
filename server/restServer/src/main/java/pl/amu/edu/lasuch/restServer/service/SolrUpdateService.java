/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.amu.edu.lasuch.restServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.lasuch.model.Product;

/**
 *
 * @author Uczelnia
 */
@Service("solrUpdateService")
public class SolrUpdateService {
    @Autowired
    @Qualifier("productJmsTemplate")
    private JmsTemplate productJmsTemplate;
    
    @Transactional
    public void sendProduct(Product product){
        productJmsTemplate.convertAndSend(product);
    }    
}
