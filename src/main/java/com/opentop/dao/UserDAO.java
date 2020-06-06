package com.opentop.dao;

import com.opentop.entity.User;

interface UserDAO {
    public User getById(Integer id);
    public User merge();
}
