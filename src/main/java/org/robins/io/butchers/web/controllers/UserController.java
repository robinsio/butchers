package org.robins.io.butchers.web.controllers;

import org.robins.io.butchers.persistence.domain.Customer;
import org.robins.io.butchers.persistence.domain.User;
import org.robins.io.butchers.persistence.service.CustomerService;
import org.robins.io.butchers.web.domain.UserTransfer;
import org.robins.io.butchers.web.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaPrintableArea;
import javax.xml.crypto.URIDereferencer;
import java.util.HashMap;
import java.util.Map;

/**
 * ***********************************************************************
 * Author: Jonathon Robins <jonathon.robins@pressassociation.com>        *
 * Created: 12/02/14 20:31                                               *
 * ***********************************************************************
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationManager authManager;


    @RequestMapping(value="authenticate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public UserTransfer authenticate(@ModelAttribute("user") User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Boolean> roles = new HashMap<String, Boolean>();

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
        UserDetails userDetails = this.userService.loadUserByUsername(user.getUsername());

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }

        return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
    }
}
