package com.cronograma.demo.service.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cronograma.demo.dto.request.UserRequestDTO;
import com.cronograma.demo.dto.response.UserResponseDTO;
import com.cronograma.demo.model.Role;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRequestDTO dto) {

        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario user = new Usuario();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setRole(Role.USER); //por padrao
        user.setPassword(passwordEncoder.encode(dto.password()));

        Usuario saved = repository.save(user);

        return new UserResponseDTO(
            saved.getUuid(),
            saved.getName(),
            saved.getEmail()
        );
    }
}
