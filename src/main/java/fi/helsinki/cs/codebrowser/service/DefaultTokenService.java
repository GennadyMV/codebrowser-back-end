package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public final class DefaultTokenService implements TokenService {

    private final Map<String, User> tokensToUsers = new HashMap<>();
    private final Map<User, String> usersToTokens = new HashMap<>();

    @Override
    public String token(final User user) {

        final String token = UUID.randomUUID().toString();

        // User has a token
        if (usersToTokens.containsKey(user)) {
            return usersToTokens.get(user);
        }

        tokensToUsers.put(token, user);
        usersToTokens.put(user, token);

        return token;
    }

    @Override
    public User authenticate(final String token) {

        return tokensToUsers.get(token);
    }
}
