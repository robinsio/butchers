package org.robins.io.butchers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 14/11/13 20:29                                               *
 * ***********************************************************************
 */
@Configuration
@Import(TestPersistenceConfig.class)
@ComponentScan(basePackages = {"org.robins.io.butchers.web.controllers"})
public class TestConfig
{
}
