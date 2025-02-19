package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Repository.ToyRepository;
import com.ToramiStore.ToramiStore.Repository.UserRepository;
import com.ToramiStore.ToramiStore.Services.IToy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToyImpl implements IToy {

    @Autowired
    private ToyRepository toyRepository;


    @Override
    public Toy save(Toy toy) {
        return null;
    }

}
