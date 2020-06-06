package com.opentop.dao;

import com.opentop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;

@NamedQueries({
        @NamedQuery(
                name = "getUserById",
                query = "SELECT * FROM user WHERE id = :id"
        )
})


public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("getUserById",User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User merge() {
        return null;
    }
}
