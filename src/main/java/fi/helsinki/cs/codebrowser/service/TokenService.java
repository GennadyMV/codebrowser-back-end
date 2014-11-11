package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

public interface TokenService {

    String token(User user);
    User authenticate(String token);
    void invalidate(User user);

}
