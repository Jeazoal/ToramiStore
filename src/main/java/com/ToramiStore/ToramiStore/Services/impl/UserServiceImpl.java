package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Adapter.UserAdapter;
import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Payloads.request.EditUserRequest;
import com.ToramiStore.ToramiStore.Payloads.request.LoginRequest;
import com.ToramiStore.ToramiStore.Payloads.request.RegisterRequest;
import com.ToramiStore.ToramiStore.Payloads.response.*;
import com.ToramiStore.ToramiStore.Repository.UserRepository;
import com.ToramiStore.ToramiStore.Services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = userAdapter.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encriptación aquí
        user.generateVerificationToken(); // 🔹 Genera token con expiración de 5 minutos

        userRepository.save(user);
        emailImpl.sendVerificationEmail(user.getCorreo(), user.getVerificationToken());

        return new RegisterResponse("Registro exitoso. Verifica tu correo.", user.getCorreo(), false);
    }


    @Override
    public VerifyUserResponse verifyUser(String token) {
        User user = userRepository.findByVerificationToken(token);

        if (user != null) {
            if (user.getTokenExpiration() == null || LocalDateTime.now().isAfter(user.getTokenExpiration())) {
                user.setVerificationToken(null);
                user.setTokenExpiration(null);
                userRepository.save(user);
                return new VerifyUserResponse("El token ha expirado.", false);
            }

            user.setActivo(true);
            user.setVerificationToken(null);
            user.setTokenExpiration(null);
            userRepository.save(user);
            return new VerifyUserResponse("Cuenta verificada con éxito.", true);
        }

        return new VerifyUserResponse("Token inválido o no encontrado.", false);
    }



    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Aquí utilizamos el Adapter para convertir User a UserResponse
        return userAdapter.toUserResponse(user);
    }



    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByCorreo(request.getCorreo());

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return userAdapter.toLoginResponse(user);
        }

        throw new RuntimeException("Credenciales incorrectas");
    }

    @Override
    public UserResponse editUser(Integer id, EditUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Actualizar los campos del usuario
        user.setNombre(request.getNombre());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setDni(request.getDni());
        user.setNumero(request.getNumero());

        // Guardar los cambios en la base de datos
        userRepository.save(user);

        // Convertir a UserResponse y devolver
        return new UserResponse(
                user.getNombre(),
                user.getApellidos(),
                user.getCorreo(),
                user.getDni(),
                user.getNumero()
        );
    }







}
