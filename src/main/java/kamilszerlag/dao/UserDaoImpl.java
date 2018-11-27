package kamilszerlag.dao;


import kamilszerlag.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void deleteUser(int theId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("User", theId);
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User").list();
        return allUsers;
    }

    @Override
    public User getUser(int theId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, theId);
        return user;
    }
}
