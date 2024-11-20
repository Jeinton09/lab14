package com.example.lab14.Service;

import com.example.lab14.Models.SecurityUser;
import com.example.lab14.Models.User;
import com.example.lab14.Models.UserRegisterDTO;
import com.example.lab14.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findByUsername(userRegisterDTO.getUsername()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setNombre(userRegisterDTO.getNombre());
        user.setApellido(userRegisterDTO.getApellido());
        user.setEmail(userRegisterDTO.getEmail());
        user.setRole(userRegisterDTO.getRole() != null ? userRegisterDTO.getRole() : "USER");

        return userRepository.save(user);
    }
}
