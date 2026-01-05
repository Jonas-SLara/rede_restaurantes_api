package com.cronograma.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "unidades")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_unidade")
    private String nome;

    @Column(name = "cep")
    private String cep;

    @Column(name = "celular_unidade")
    private String celular;

    @Column(name = "email_unidade")
    private String email;

    @ManyToMany
    @JoinTable(
        name = "usuarios_unidades",
        joinColumns = @JoinColumn(name = "unidade_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
}
