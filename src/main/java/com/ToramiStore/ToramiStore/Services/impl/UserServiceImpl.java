package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Repository.UserRepository;
import com.ToramiStore.ToramiStore.Services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailImpl;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.generateVerificationToken();
        User savedUser = userRepository.save(user);
        emailImpl.sendVerificationEmail(user.getCorreo(), user.getVerificationToken());
        return convertToDTO(savedUser);
    }

    @Override
    public boolean verifyUser(String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setActivo(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getIdUser());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No se encontró el usuario con ID: " + userDTO.getIdUser());
        }

        User user = optionalUser.get();
        user.setNombre(userDTO.getNombre());
        user.setApellidos(userDTO.getApellidos());
        user.setCorreo(userDTO.getCorreo());
        user.setDni(userDTO.getDni());
        user.setNumero(userDTO.getNumero());

        User updatedUser = userRepository.save(user);

        return new UserDTO(
                updatedUser.getIdUser(),
                updatedUser.getNombre(),
                updatedUser.getApellidos(),
                updatedUser.getCorreo(),
                updatedUser.getDni(),
                updatedUser.getNumero()
        );
    }

    @Override
    public User login(String correo, String password) {
        User user = userRepository.findByCorreo(correo);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }


    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getIdUser(),
                user.getNombre(),
                user.getApellidos(),
                user.getCorreo(),
                user.getDni(),
                user.getNumero()
        );
    }




}
