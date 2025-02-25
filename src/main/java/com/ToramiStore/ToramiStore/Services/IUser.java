package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.RegisterResponse;

public interface IUser {
    RegisterResponse register(RegisterRequest request);
    boolean verifyUser(String token);
    User findById(Integer id);
    UserDTO editUser(UserDTO userDTO);
    User login(String correo, String password); // 🔹 Cambiado a UserDTO
}