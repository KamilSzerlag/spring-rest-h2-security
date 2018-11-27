package kamilszerlag.dao;

import kamilszerlag.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void saveUser(User user);
    void deleteUser(int theId);
    List<User> getAllUser();
    User getUser(int theId);


}
