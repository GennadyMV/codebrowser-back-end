package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Role;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public final class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private List<GrantedAuthority> getRolesAsGrantedAuthorities(final List<Role> roles) {

        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {

        final User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      user.getPassword(),
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      getRolesAsGrantedAuthorities(user.getRoles()));

    }
}
