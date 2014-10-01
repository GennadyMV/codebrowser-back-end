package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public final class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Override
    public User currentUser() {

        final Principal user = (Principal) SecurityContextHolder.getContext()
                                                                .getAuthentication()
                                                                .getPrincipal();

        return userService.findByUsername(user.getName());
    }
}
