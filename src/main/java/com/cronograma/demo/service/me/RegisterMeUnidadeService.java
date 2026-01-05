package com.cronograma.demo.service.me;

import org.springframework.stereotype.Service;

import com.cronograma.demo.model.Unidade;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.repository.UnidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterMeUnidadeService {

    private final UnidadeRepository unidadeRepository;

    public void execute(Usuario usuario, Long unidadeId){
        Unidade unidade = unidadeRepository.findById(unidadeId)
            .orElseThrow(() -> new RuntimeException("Unidade nao encontrada"));
        if(unidade.getUsuarios().contains(usuario)){
            throw new RuntimeException("Já esta vinculado nesta Unidade: " + unidade.getNome());
        }

        try {
            unidade.getUsuarios().add(usuario);
            unidadeRepository.save(unidade);
            System.out.println("Usuario: " + usuario.getEmail() + " vinculado a " + unidade.getNome());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
