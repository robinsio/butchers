package org.robins.io.butchers.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.domain.ObjectFactory;
import org.robins.io.butchers.persistence.domain.User;
import org.robins.io.butchers.persistence.service.CustomerService;
import org.robins.io.butchers.util.TestUtil;
import org.springframework.http.MediaType;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class UserControllerTest extends ControllerTestCase
{
    @InjectMocks
    private UserController userController = new UserController();


    @Before
    public void setup() throws JAXBException, IOException
    {
        //Initialise Mock Beans
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testPostUser() throws Exception
    {
        User user = new User("user", "password");

        mockMvc.perform(post("/user/authenticate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(TestUtil.convertObjectToFormUrlEncodedBytes(user))
                        .sessionAttr("user", user)
        )
        .andExpect(status().isOk());

    }
}
