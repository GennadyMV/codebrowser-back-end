package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.User;

public interface AuthorizationService {

    User currentUser();

}
