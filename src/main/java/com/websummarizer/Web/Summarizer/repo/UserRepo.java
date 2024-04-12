package com.websummarizer.Web.Summarizer.repo;

import com.websummarizer.Web.Summarizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Long> {
    //  Run this method to update a user's password based on the user's email
    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2")
    void setPassword(String password, String email);

    //  Run this method to get a user based on a given email
    @Query("select u from User u  where u.email = ?1")
    User getUserByEmail(String email);

    //  Run this method to get a user based on a given email and request_token
    @Query("select u from User u  where u.email = ?1 and u.request_token = ?2")
    User getUserByEmailAndResetToken(String email, String token);

    //  Run this method to set a user's reset token if they select "Forgot Password"
    @Modifying
    @Query("update User u set u.request_token = ?1 where u.email = ?2")
    int setRequestToken(String token, String email);

    /**
     * Retrieves a user by its email address.
     *
     * @param username The email address of the user.
     * @return Optional containing the user with the given email, if found.
     */
    Optional<User> findByEmail(String username);
}
