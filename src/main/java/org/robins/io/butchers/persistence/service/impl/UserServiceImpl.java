package org.robins.io.butchers.persistence.service.impl;

import org.robins.io.butchers.persistence.domain.User;
import org.robins.io.butchers.persistence.service.UserService;
import org.robins.io.butchers.web.security.SaltedSHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

/**
 * Created by jonrobins on 25/02/2014.
 */
@Service
public class UserServiceImpl implements UserService
{
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.findByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException("The user with name " + username + " was not found");
        }

        return user;
    }


    @Override
    public User findByUsername(String name) {

        String code = "";

        try {
            code = new SaltedSHA256PasswordEncoder("secret").encode("password");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User("user", code);
        user.getRoles().add("ROLE_USER");
        return user;
    }
}
