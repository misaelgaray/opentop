package com.opentop.dao;

import com.opentop.dao.UserDAOImpl;
import com.opentop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

    private SessionFactory sessionFactory;
    private Session session;

    private UserDAOImpl userDAO;

    @Before
    public void setup(){
        sessionFactory =  Mockito.mock(SessionFactory.class);
        session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDAO = new UserDAOImpl(sessionFactory);
    }

    @Test
    public void testGetByIdStudent(){
    }
}
