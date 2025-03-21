package es.daw.extra.mvc.service;

import es.daw.extra.mvc.entity.Role;
import es.daw.extra.mvc.entity.User;
import es.daw.extra.mvc.repository.RoleRepository;
import es.daw.extra.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // necesito el RoleRepository???? siiiiiiiiiii porque tengo que asignarle un rol al usuario nuevo
    private final RoleRepository roleRepository;

    public void registerUser(User user) {
        //try {
            // codificar la password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // El rol por defecto se lee de un fichero de propiedades... pendiente!!!!
            Role role = roleRepository.findByName("ROLE_STUDENT")
                    .orElseThrow(() -> new RuntimeException("No role found"));


            // forma 1
            //user.addRole(role);

            // forma 2: utilizar el setter de la propiedad roles
            user.setRoles(Set.of(role));

            userRepository.save(user);

//        }catch(DataIntegrityViolationException e){
//            //PENDIENTE!!! PROPAGO LA EXCEPCIÓN Y QUÉ HAGO CON ELLA???? DÓNDE LA CAPTURO???
//            throw new RuntimeException("Error al registrar el usuario...");
//        }catch (Exception e) {
//            throw new RuntimeException("Error inesperado al registrar el usuario");
//        }

    }



    // podría ponerlo aquí pero mejor en un @ControllerAdvice...
    // @ExceptionHandler
}
