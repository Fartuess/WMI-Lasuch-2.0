/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.solrConnector.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import pl.edu.amu.lasuch.model.Product;
import pl.edu.amu.lasuch.solr.SolrConnection;

/**
 *
 * @author Uczelnia
 */
public class SolrUpdaterService implements MessageListener {
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
    private SolrConnection solrConnection;
    
    public SolrUpdaterService() {
        solrConnection = new SolrConnection();
    }
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Diala!");
        if (message instanceof ObjectMessage) {
            try {
                Product product = (Product) ((ObjectMessage)message).getObject();
                solrConnection.addProduct(product);
            } catch (JMSException ex) {
                Logger.getLogger(SolrUpdaterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
