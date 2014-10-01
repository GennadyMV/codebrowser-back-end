package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(final String username) {

        return userRepository.findByUsername(username);
    }
}
