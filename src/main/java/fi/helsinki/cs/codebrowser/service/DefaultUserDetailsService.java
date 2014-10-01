package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Role;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

@Service
public final class DefaultUserDetailsService implements UserDetailsService {

    private static final String AUTHORISATION_HEADER = "Authorization";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    // TODO: Remove this
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initialise() {

        final User user = new User();

        user.setUsername("username");
        user.setPassword("passwordpasswordpassword");

        userRepository.save(user);
    }
    // TODO: Remove this

    private String getToken() {

        final byte[] base64Token;

        try {
            base64Token = request.getHeader(AUTHORISATION_HEADER).substring(6).getBytes("UTF-8");
        } catch (UnsupportedEncodingException exception) {
            throw new BadCredentialsException("Failed to decode Basic authentication token.");
        }

        final byte[] decoded = Base64.decode(base64Token);
        final String token = new String(decoded).split(":")[1];

        return token;
    }

    private List<GrantedAuthority> getRolesAsGrantedAuthorities(final List<Role> roles) {

        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return authorities;
    }

    private UserDetails getUserDetails(final User user, final String password) {

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      password,
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      getRolesAsGrantedAuthorities(user.getRoles()));
    }

    private UserDetails loadUserByToken(final String token) {

        final User user = tokenService.authenticate(token);

        if (user == null) {
            throw new BadCredentialsException("Invalid token.");
        }

        return getUserDetails(user, token);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {

        // Token-based authentication
        if (username.isEmpty()) {
            return loadUserByToken(getToken());
        }

        final User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Bad credentials.");
        }

        return getUserDetails(user, user.getPassword());
    }
}
