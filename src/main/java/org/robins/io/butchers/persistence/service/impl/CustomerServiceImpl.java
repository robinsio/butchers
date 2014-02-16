package org.robins.io.butchers.persistence.service.impl;

import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.repository.CustomerRepository;
import org.robins.io.butchers.persistence.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 03/02/14 21:48                                               *
 * ***********************************************************************
 */
@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id)
    {
        return customerRepository.findOne(id);
    }

    @Override
    public Iterable<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }
}
