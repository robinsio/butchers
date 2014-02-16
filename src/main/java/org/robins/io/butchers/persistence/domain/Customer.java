package org.robins.io.butchers.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 03/02/14 21:17                                               *
 * ***********************************************************************
 */
@Entity
@XmlRootElement(name = "customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firstLineAddress;
    private String secondLineAddress;
    private String city;
    private String postcode;
    private String phoneNumber;

    Customer(){}

    protected Customer(String name, String firstLineAddress, String secondLineAddress, String city, String postcode, String phoneNumber)
    {
        this.name = name;
        this.firstLineAddress = firstLineAddress;
        this.secondLineAddress = secondLineAddress;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFirstLineAddress()
    {
        return firstLineAddress;
    }

    public void setFirstLineAddress(String firstLineAddress)
    {
        this.firstLineAddress = firstLineAddress;
    }

    public String getSecondLineAddress()
    {
        return secondLineAddress;
    }

    public void setSecondLineAddress(String secondLineAddress)
    {
        this.secondLineAddress = secondLineAddress;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
