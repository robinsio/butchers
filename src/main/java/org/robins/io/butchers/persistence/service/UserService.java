package org.robins.io.butchers.persistence.service;

import org.robins.io.butchers.persistence.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by jonrobins on 25/02/2014.
 */
public interface UserService extends UserDetailsService
{
    User findByUsername(String username);
}
