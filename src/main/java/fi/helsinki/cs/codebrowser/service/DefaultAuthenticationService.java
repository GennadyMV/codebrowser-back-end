package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public final class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Override
    public User currentUser() {

        final Authentication authentication = SecurityContextHolder.getContext()
                                                                   .getAuthentication();

        if (authentication == null) {
            return null;
        }

        return userService.findByUsername(authentication.getName());
    }

    @Override
    public void invalidate() {
        SecurityContextHolder.clearContext();
    }
}
