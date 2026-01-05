package com.cronograma.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cronograma.demo.model.UserDetailsAdapter;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsAdapterService implements UserDetailsService{

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {

        Optional<Usuario> u =repository.findByEmail(username);
        if(!u.isPresent()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return new UserDetailsAdapter(u.get());
    }
    
}
