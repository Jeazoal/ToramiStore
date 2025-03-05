    package com.ToramiStore.ToramiStore.Controller;

    import com.ToramiStore.ToramiStore.Payloads.request.*;
    import com.ToramiStore.ToramiStore.Payloads.response.*;
    import com.ToramiStore.ToramiStore.Services.IToken;
    import com.ToramiStore.ToramiStore.Services.IUser;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.token.TokenService;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/account")
    public class UserController {

        @Autowired
        private IUser userservice;

        @Autowired
        private IToken tokenService;


        @PostMapping("/register")
        public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
            try {
                RegisterResponse response = userservice.register(request);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new RegisterResponse("Error al registrar usuario: " + e.getMessage(), request.getCorreo(), false));
            }
        }


        @GetMapping("/verify")
        public ResponseEntity<VerifyUserResponse> verifyUser(@RequestParam String token) {
            VerifyUserResponse response = userservice.verifyUser(token);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        }


        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
            try {
                LoginResponse response = userservice.login(request); // ✅ Lógica de autenticación
                String token = tokenService.generateToken(request.getCorreo()); // ✅ Generamos el token
                response.setToken(token); // ✅ Lo agregamos a la respuesta
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse(null, "Credenciales incorrectas", null));
            }
        }


        @GetMapping("/{id}")
        public ResponseEntity<UserResponse> findById(@PathVariable Integer id) {
            try {
                UserResponse response = userservice.findById(id);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }


        @PutMapping("/edit/{id}")
        public ResponseEntity<UserResponse> editUser(@PathVariable Integer id, @RequestBody EditUserRequest request) {
            try {
                UserResponse response = userservice.editUser(id, request);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(null, null, null, null, null));
            }
        }

        @PostMapping("/forgot-password")
        public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
            return ResponseEntity.ok(userservice.forgotPassword(request));
        }

        @PostMapping("/reset-password")
        public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request) {
            return ResponseEntity.ok(userservice.resetPassword(token, request));
        }



    }
