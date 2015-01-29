/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.restServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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
    //@Autowired
    //@Qualifier("productJmsTemplate")
    //private JmsTemplate productJmsTemplate;
    @Autowired
    private ApplicationContext context;
    
    @Transactional
    public void sendProduct(Product product){
        JmsTemplate productJmsTemplate = (JmsTemplate) context.getBean("productJmsTemplate");
        productJmsTemplate.convertAndSend(product);
    }    
}
