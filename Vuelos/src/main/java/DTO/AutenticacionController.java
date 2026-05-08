package DTO;


import User.User;
import User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private UserService userService; // Tu lógica de validaciones de registro

    @Autowired
    private AuthenticationManager authenticationManager; // El motor de Spring Security

    @Autowired

    private JwtProvider jwtProvider; // Tu generador de tokens

    // 1. REGISTRO (Usa UserRespuestaDTO para devolver solo el ID)
    @PostMapping("/register")
    public ResponseEntity<UserRespuestaDTO> registrar(@RequestBody User user) {
        User usuarioCreado = userService.registro(user);

        // Retornamos el DTO con el ID (como te pedían al inicio)
        return ResponseEntity.status(201).body(new UserRespuestaDTO(usuarioCreado.getId()));
    }

    // 2. LOGIN (Aquí cumples las validaciones de email y contraseña)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {

        // REQUISITO: Validar email y contraseña obligatorios
        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            return ResponseEntity.badRequest().body("Email y contraseña son obligatorios");
        }

        try {
            // REQUISITO: Validar email desconocido / contraseña incorrecta
            // Esto dispara el proceso de Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            // REQUISITO: Retorna token JWT si todo está OK
            String token = jwtProvider.generateToken(loginDTO.getEmail());

            // REQUISITO: El formato debe ser { token: "<jwt>" }
            return ResponseEntity.ok(new LoginRespuestaDTO(token));

        } catch (BadCredentialsException e) {
            // Si el email no existe o la contraseña está mal, cae aquí
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }
    }
}