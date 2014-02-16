package org.robins.io.butchers.persistence.service;

import org.robins.io.butchers.persistence.domain.Customer;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 03/02/14 21:47                                               *
 * ***********************************************************************
 */
public interface CustomerService
{
    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomer(Long id);

    Iterable<Customer> getCustomers();
}
