package org.robins.io.butchers.persistence.domain;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 10/02/14 21:38                                               *
 * ***********************************************************************
 */
public class ObjectFactory
{
    public static Customer createCustomer(String name, String firstLineAddress, String secondLineAddress, String city, String postcode, String phoneNumber)
    {
       return new Customer(name, firstLineAddress, secondLineAddress, city, postcode, phoneNumber);
    }
}
