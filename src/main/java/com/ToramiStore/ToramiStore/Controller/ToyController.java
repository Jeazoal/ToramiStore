package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Entity.Toy;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Services.IToy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Torami")
public class ToyController {

    @Autowired
    private IToy toyservice;

    @PostMapping("/Toy")
    public Toy create(@RequestBody Toy toy){
        return toyservice.save(toy);
    }

}
