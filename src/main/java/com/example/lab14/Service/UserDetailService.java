package com.example.lab14.Service;

import com.example.lab14.Models.SecurityUser;
import com.example.lab14.Models.User;
import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        if(user== null){
            throw new UsernameNotFoundException("usuario no encontrado xd");
        }
        return new SecurityUser(user);
    }

    public User registerUserAsUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setNombre(userRegisterDTO.getNombre());
        user.setApellido(userRegisterDTO.getApellido());
        user.setEmail(userRegisterDTO.getEmail());
        user.setRole("USER"); // Rol predeterminado para usuarios

        return userRepository.save(user);
    }

    public User registerUserAsAdmin(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setNombre(userRegisterDTO.getNombre());
        user.setApellido(userRegisterDTO.getApellido());
        user.setEmail(userRegisterDTO.getEmail());
        user.setRole("ADMIN"); // Rol predeterminado para administradores

        return userRepository.save(user);
    }


    @PostAuthorize("hasAuthority('ADMIN') or returnObject.username == authentication.name")
    public User getUserDetailsByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    //listar usuarios

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
