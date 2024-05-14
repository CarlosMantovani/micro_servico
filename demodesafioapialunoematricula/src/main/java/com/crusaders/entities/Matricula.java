package com.crusaders.entities;

import com.crusaders.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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

    @Column(name = "curso_id")
    private Long cursoId; // Alteração aqui

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
