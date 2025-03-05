package com.ToramiStore.ToramiStore.Adapter;

import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Payloads.request.EditUserRequest;
import com.ToramiStore.ToramiStore.Payloads.request.LoginRequest;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.ForgotPasswordResponse;
import com.ToramiStore.ToramiStore.Payloads.response.LoginResponse;
import com.ToramiStore.ToramiStore.Payloads.response.ResetPasswordResponse;
import com.ToramiStore.ToramiStore.Payloads.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {



    public User toEntity(RegisterRequest request) {
        User user = new User();
        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setPassword(request.getPassword());
        user.setDni(request.getDni());
        user.setNumero(request.getNumero());
        return user;
    }

    public User toEntity(EditUserRequest request) {
        User user = new User();
        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setDni(request.getDni());
        user.setNumero(request.getNumero());
        return user;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getNombre(),
                user.getApellidos(),
                user.getCorreo(),
                user.getDni(),
                user.getNumero()
        );
    }

    public static LoginResponse toLoginResponse(User user, String token) {
        return new LoginResponse(
                user.getIdUser(),
                "Login exitoso",
                token
        );
    }


    public ForgotPasswordResponse toForgotPasswordResponse() {
        return new ForgotPasswordResponse("Se ha enviado un correo con las instrucciones para restablecer la contraseña.");
    }

    public ResetPasswordResponse toResetPasswordResponse() {
        return new ResetPasswordResponse("La contraseña se ha actualizado correctamente.");
    }
}
