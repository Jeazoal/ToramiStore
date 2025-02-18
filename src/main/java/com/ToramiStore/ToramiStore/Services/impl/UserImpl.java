package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Repository.UserRepository;
import com.ToramiStore.ToramiStore.Services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
