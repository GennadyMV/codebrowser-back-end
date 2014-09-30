package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Role;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class DefaultUserDetailsService implements UserDetailsService {

    private static final String TOKEN_HEADER = "X-Token";

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

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

        if (user == null) {
            throw new UsernameNotFoundException("Invalid credentials.");
        }

        // Return token for user
        response.setHeader(TOKEN_HEADER, tokenService.token(user));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      user.getPassword(),
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      getRolesAsGrantedAuthorities(user.getRoles()));

    }
}
