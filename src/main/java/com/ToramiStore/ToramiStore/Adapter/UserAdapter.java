package com.ToramiStore.ToramiStore.Adapter;

import org.springframework.stereotype.Component;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.RegisterResponse;
import com.ToramiStore.ToramiStore.Utils.AdapterTemplate;

@Component
public class UserAdapter implements AdapterTemplate<User, RegisterRequest> {
    @Override
    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setDni(request.getDni());
        user.setNumero(request.getNumero());
        user.setPassword(request.getPassword());
        return user;
    }
}