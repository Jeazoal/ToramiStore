package com.ToramiStore.ToramiStore.Services;


import com.ToramiStore.ToramiStore.Payloads.request.EditUserRequest;
import com.ToramiStore.ToramiStore.Payloads.request.LoginRequest;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.*;

public interface IUser {
    RegisterResponse register(RegisterRequest request);
    VerifyUserResponse verifyUser(String token);
    UserResponse findById(Integer id);
    UserResponse editUser(Integer id, EditUserRequest request);
    LoginResponse login(LoginRequest request);
}
