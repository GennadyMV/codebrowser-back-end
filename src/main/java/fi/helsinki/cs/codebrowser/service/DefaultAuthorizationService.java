package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthorizationService implements AuthorizationService {

    @Override
    public User currentUser() {

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
