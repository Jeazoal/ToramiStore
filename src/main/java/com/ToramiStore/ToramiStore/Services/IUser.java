package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;

public interface IUser {
    UserDTO register(User user);
    boolean verifyUser(String token);
    User findById(Integer id);
    UserDTO editUser(UserDTO userDTO);
    User login(String correo, String password); // 🔹 Cambiado a UserDTO
}