package org.robins.io.butchers.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 14/11/13 20:58                                               *
 * ***********************************************************************
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { TestConfig.class}, loader = AnnotationConfigContextLoader.class )
public class SpringTest
{
    @Test
    public void whenSpringContextIsInstantiated_thenNoExceptions()
    {
        // When
    }
}