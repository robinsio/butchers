package org.robins.io.butchers.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.domain.ObjectFactory;
import org.robins.io.butchers.persistence.service.CustomerService;
import org.springframework.http.MediaType;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 12/02/14 19:08                                               *
 * ***********************************************************************
 */
public class CustomerControllerTest extends ControllerTestCase
{
    @InjectMocks
    private CustomerController customerController = new CustomerController();

    @Mock
    private CustomerService customerService;

    @Before
    public void setup() throws JAXBException, IOException
    {
        //Initialise Mock Beans
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomerList() throws Exception
    {
        //Setup Mock Data Objects
        Customer customer1 = ObjectFactory.createCustomer("Jon Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");
        Customer customer2 = ObjectFactory.createCustomer("Steve Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");
        Customer customer3 = ObjectFactory.createCustomer("Nick Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        when(customerService.getCustomers()).thenReturn(customers);

        this.mockMvc.perform(get("/customer")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomer() throws Exception
    {
        Customer customer = ObjectFactory.createCustomer("Jon Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");

        when(customerService.getCustomer(1L)).thenReturn(customer);

        String jsonCustomer = new ObjectMapper().writeValueAsString(customer);

        this.mockMvc.perform(get("/customer/1")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(jsonCustomer));
    }

    @Test
    public void testPutCustomer() throws Exception
    {
        Customer customer = ObjectFactory.createCustomer("Jon Robins", "90 Syntax Street", "Java", "Development", "JA2 DEV", "010101223224");

        when(customerService.getCustomer(1L)).thenReturn(customer);
        when(customerService.updateCustomer(customer)).thenReturn(customer);

        String jsonCustomer = new ObjectMapper().writeValueAsString(customer);

        this.mockMvc.perform(put("/customer/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonCustomer)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(jsonCustomer));
    }
}
