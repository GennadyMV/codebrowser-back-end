package fi.helsinki.cs.codebrowser.repository;

import fi.helsinki.cs.codebrowser.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
