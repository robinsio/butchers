package org.robins.io.butchers.web.controllers;

import org.junit.runner.RunWith;
import org.robins.io.butchers.config.TestConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { TestConfig.class}, loader = AnnotationConfigContextLoader.class )
@WebAppConfiguration
public abstract class ControllerTestCase
{
    public static final String APPLICATION_JSON = "application/json";

    public MockMvc mockMvc;
}
