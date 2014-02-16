package org.robins.io.butchers.persistence.repository;

import org.robins.io.butchers.persistence.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 03/02/14 21:42                                               *
 * ***********************************************************************
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
}
