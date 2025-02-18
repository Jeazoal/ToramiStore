package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Entity.User;

public interface IUser {
    User save(User user);
    User findById(Integer id);
    User editUser(User user);
}
