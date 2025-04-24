package es.daw.extra.apievaluaciones.controller;

import es.daw.extra.apievaluaciones.dto.AuthRequest;
import es.daw.extra.apievaluaciones.dto.AuthResponse;
import es.daw.extra.apievaluaciones.dto.RegisterRequest;
import es.daw.extra.apievaluaciones.entities.Rol;
import es.daw.extra.apievaluaciones.entities.Usuario;
import es.daw.extra.apievaluaciones.repositories.RolRepository;
import es.daw.extra.apievaluaciones.repositories.UsuarioRepository;
import es.daw.extra.apievaluaciones.security.JwtService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // El mismo que se cargó en UserDetailsService.loadUserByUsername.....
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));


    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        // 1. Comprobar si el usuario ya existe (username)
        if(usuarioRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Usuario ya existe");
        }

        // 2. Inicializamos roles
        Set<Rol> roles = new HashSet<>();

        // 3. Asegurar que el rol enviado tenga el prefijo _ROLE
        // Si no viene rol, porque por defecto USUARIO
        System.out.println("request.getRole(): <"+request.getRole()+">");
        System.out.println("request.getRole().isEmpty(): <"+request.getRole().isEmpty()+">");
        System.out.println("request.getRole().isBlank(): <"+request.getRole().isBlank()+">");
        String roleName = (request.getRole() != null && !request.getRole().isBlank() )? request.getRole().toUpperCase() : "ROLE_USER";
        System.out.println("roleName: <"+roleName+">");
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName;
        }

        // 4. Buscamos el rol en la BD
        Optional<Rol> roleOpt = rolRepository.findByName(roleName);
        if(roleOpt.isPresent())
            roles.add(roleOpt.get());
        else
            return ResponseEntity.badRequest().body("Rol no encontrado");

        // 5. Creamos el nuevo usuario
        Usuario newUser = new Usuario();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(roles);

        usuarioRepository.save(newUser);

        // 6. Respuesta
        return ResponseEntity.ok("Usuario registrado correctamente");


    }

}
