package com.cronograma.demo.service.me;
import org.springframework.stereotype.Service;

import com.cronograma.demo.model.Unidade;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.repository.UnidadeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletMeUnidadeService {
    private final UnidadeRepository unidadeRepository;

    @Transactional
    public void execute(Usuario usuario, Long idUnidade){
        Unidade unidade = unidadeRepository
        .findByIdAndUsuario(idUnidade, usuario.getUuid())
        .orElseThrow(() ->
            new RuntimeException("Usuário não vinculado a esta unidade")
        );

        unidade.getUsuarios().removeIf(u ->
            u.getUuid().equals(usuario.getUuid())
        );

        unidadeRepository.save(unidade);
        System.out.println("Usuario removido de: " + unidade.getNome());
    }
}
