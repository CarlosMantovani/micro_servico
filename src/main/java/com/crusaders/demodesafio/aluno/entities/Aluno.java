package com.crusaders.demodesafio.aluno.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "dataDeNascimento", nullable = false, length = 10)
    private Date dataNascimento;

    @Column(name = "sexo", nullable = false, length = 15)
    private String sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluno aluno)) return false;
        return Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                '}';
    }
}
