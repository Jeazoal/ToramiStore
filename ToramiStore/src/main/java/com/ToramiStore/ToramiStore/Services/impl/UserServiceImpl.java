package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Adapter.UserAdapter;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Exceptions.AuthenticationException;
import com.ToramiStore.ToramiStore.Exceptions.InvalidTokenException;
import com.ToramiStore.ToramiStore.Exceptions.UserNotFoundException;
import com.ToramiStore.ToramiStore.Payloads.request.*;
import com.ToramiStore.ToramiStore.Payloads.response.*;
import com.ToramiStore.ToramiStore.Repository.UserRepository;
import com.ToramiStore.ToramiStore.Services.IToken;
import com.ToramiStore.ToramiStore.Services.IUser;
import com.ToramiStore.ToramiStore.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailImpl;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserAdapter userAdapter;

    @Autowired
    private IToken tokenService;






    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = userAdapter.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String token = JwtUtil.generateVerifyToken(user.getCorreo());
        user.setVerificationToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(1));

        userRepository.save(user);

        String verificationLink = "http://localhost:8080/toramistore/account/verify?token=" + token;

        emailImpl.sendVerificationEmail(user.getCorreo(), verificationLink);

        return new RegisterResponse("Registro exitoso. Verifica tu correo.", user.getCorreo(), false);
    }

    @Override
    public VerifyUserResponse verifyUser(String token) {
        try {
            String email = JwtUtil.decodeToken(token).getSubject();
            User user = userRepository.findByCorreo(email)
                    .orElseThrow(() -> new InvalidTokenException("Token inválido o no encontrado."));

            if (!token.equals(user.getVerificationToken())) {
                throw new InvalidTokenException("Token inválido o no encontrado.");
            }

            user.setActivo(true); // ✅ Activar usuario
            user.setVerificationToken(null);
            user.setTokenExpiration(null);
            userRepository.save(user);

            return new VerifyUserResponse("Cuenta verificada con éxito.", true);
        } catch (Exception e) {
            throw new InvalidTokenException("Token inválido o expirado.");
        }
    }



    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = tokenService.generateToken(user.getCorreo());

        return UserAdapter.toLoginResponse(user, token);
    }




    @Override
    public UserResponse editUser(Integer id, EditUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));

        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setDni(request.getDni());
        user.setNumero(request.getNumero());

        userRepository.save(user);

        return new UserResponse(
                user.getNombre(),
                user.getApellidos(),
                user.getCorreo(),
                user.getDni(),
                user.getNumero()
        );
    }

    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));
        return userAdapter.toUserResponse(user);
    }


    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new UserNotFoundException("No se encontró un usuario con el correo proporcionado."));

        String token = JwtUtil.generateTokenPassword(user.getCorreo());
        user.setVerificationToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(2));
        userRepository.save(user);

        String resetLink = "http://localhost:8080/account/reset-password?token=" + token;
        emailImpl.sendPasswordResetEmail(user.getCorreo(), resetLink);

        return userAdapter.toForgotPasswordResponse();
    }


    @Override
    public ResetPasswordResponse resetPassword(String token, ResetPasswordRequest request) {
        User user = userRepository.findByVerificationToken(token);

        if (user == null || user.getTokenExpiration() == null || user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Token inválido o expirado.");
        }

        if (passwordEncoder.matches(request.getNuevaPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual a la anterior.");
        }

        user.setPassword(passwordEncoder.encode(request.getNuevaPassword()));
        user.setVerificationToken(null);
        user.setTokenExpiration(null);
        userRepository.save(user);

        return userAdapter.toResetPasswordResponse();
    }

}
