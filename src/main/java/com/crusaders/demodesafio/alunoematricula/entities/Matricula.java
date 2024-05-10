package com.crusaders.demodesafio.alunoematricula.entities;
import com.crusaders.demodesafio.Enum.Status;
import com.crusaders.demodesafio.curso.entidade.Curso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "matriculas")
public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 25)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula matricula)) return false;
        return Objects.equals(getId(), matricula.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
