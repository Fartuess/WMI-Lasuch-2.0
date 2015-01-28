/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.lasuch.model;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Uczelnia
 */
@Component("productConverter")
public class ProductConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session sn) throws JMSException, MessageConversionException {
        Product product = (Product) o;
        ObjectMessage message = sn.createObjectMessage(product);
        return message;
    }

    @Override
    public Object fromMessage(Message msg) throws JMSException, MessageConversionException {
        ObjectMessage message = (ObjectMessage) msg;
        return message.getObject();
    }
    
}
