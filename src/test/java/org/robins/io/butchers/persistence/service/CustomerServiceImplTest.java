package org.robins.io.butchers.persistence.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robins.io.butchers.config.TestConfig;
import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.domain.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 10/02/14 20:37                                               *
 * ***********************************************************************
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { TestConfig.class}, loader = AnnotationConfigContextLoader.class )
public class CustomerServiceImplTest
{
    @Autowired
    CustomerService customerService;

    @Test
    public void testCreateCustomer()
    {
        Customer customer = ObjectFactory.createCustomer("Jon Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");

        customer = customerService.createCustomer(customer);
        customer = customerService.getCustomer(customer.getId());

        assertEquals(4L, customer.getId().longValue());
        assertEquals("Jon Robins", customer.getName());
    }

    @Test
    public void testGetCustomers()
    {
        Iterable<Customer> customers = customerService.getCustomers();
        Iterator<Customer> customerIterator = customers.iterator();
        int count = 0;

        while (customerIterator.hasNext())
        {
            customerIterator.next();
            count++;
        }

        assertTrue(count > 3);
    }

    @Test
    public void testGetCustomer()
    {
        Customer customer = customerService.getCustomer(1L);

        assertEquals("Jon Robins", customer.getName());
    }

    @Test
    public void testUpdateCustomer()
    {
        Customer customer = customerService.getCustomer(1L);
        customer.setName("Jonathon Robins");
        customer.setCity("York");

        customerService.updateCustomer(customer);

        customer = customerService.getCustomer(1L);

        assertEquals("Jonathon Robins", customer.getName());
        assertEquals("York", customer.getCity());
    }
}
