package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Payloads.request.EditUserRequest;
import com.ToramiStore.ToramiStore.Payloads.request.LoginRequest;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.*;

public interface IUser {
    RegisterResponse register(RegisterRequest request);
    VerifyUserResponse verifyUser(String token);  // Cambiado a VerifyUserResponse
    UserResponse findById(Integer id); // Asumiendo que usas UserResponse aquí
    UserResponse editUser(Integer id, EditUserRequest request); // ID se pasa como parámetro
    LoginResponse login(LoginRequest request);
}
