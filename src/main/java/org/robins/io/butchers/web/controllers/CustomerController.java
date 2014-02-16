package org.robins.io.butchers.web.controllers;

import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 12/02/14 20:31                                               *
 * ***********************************************************************
 */
@Controller
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Iterable<Customer> getCustomers()
    {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Customer getCustomer(@PathVariable long customerId)
    {
        return customerService.getCustomer(customerId);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT, produces="application/json")
    @ResponseBody
    public Customer updateCustomer(@PathVariable long customerId, @RequestBody Customer customer)
    {
        Customer persistedCustomer = customerService.getCustomer(customerId);

        if(persistedCustomer == null) return null;

        persistedCustomer.setName(customer.getName());
        persistedCustomer.setFirstLineAddress(customer.getFirstLineAddress());
        persistedCustomer.setSecondLineAddress(customer.getSecondLineAddress());
        persistedCustomer.setCity(customer.getCity());
        persistedCustomer.setPostcode(customer.getPostcode());
        persistedCustomer.setPhoneNumber(customer.getPhoneNumber());

        return customerService.updateCustomer(persistedCustomer);
    }

    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public Customer createCustomer(@RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }
}
