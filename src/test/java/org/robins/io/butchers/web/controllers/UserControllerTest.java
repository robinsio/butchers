package org.robins.io.butchers.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class UserControllerTest extends ControllerTestCase {

    @InjectMocks
    private UserController userController = new UserController();


    @Before
    public void setup() throws JAXBException, IOException {
        //Initialise Mock Beans
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testPostUser() throws Exception {
        mockMvc.perform(post("/user/authenticate")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "user")
            .param("password", "password"))
            .andDo(print())
            .andExpect(status().isOk());
        }
}
