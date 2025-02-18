package com.ToramiStore.ToramiStore.Controller;


import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Torami")
public class UsuarioController {

    @Autowired
    private IUser userservice;

    @PostMapping("/user")
    public User create(@RequestBody User user){
        return userservice.save(user);
    }
}
