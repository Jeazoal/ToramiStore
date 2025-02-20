package com.ToramiStore.ToramiStore.Controller;


import com.ToramiStore.ToramiStore.Dto.UserDTO;
import com.ToramiStore.ToramiStore.Entity.User;
import com.ToramiStore.ToramiStore.Services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {

    @Autowired
    private IUser userservice;

    @PostMapping("/create")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            UserDTO newUser = userservice.register(user);
            return ResponseEntity.ok("Registro exitoso. Verifica tu correo.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String token) {
        boolean verified = userservice.verifyUser(token);
        if (verified) {
            return ResponseEntity.ok("Cuenta verificada con éxito. Ahora puedes iniciar sesión.");
        } else {
            return ResponseEntity.badRequest().body("Token inválido o expirado.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User authenticatedUser = userservice.login(user.getCorreo(), user.getPassword());

            if (authenticatedUser != null) {
                UserDTO userDTO = convertToDTO(authenticatedUser);
                return ResponseEntity.ok(userDTO);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el login: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userservice.editUser(userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar usuario: " + e.getMessage());
        }
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
