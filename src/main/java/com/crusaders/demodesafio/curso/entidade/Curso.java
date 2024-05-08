package com.crusaders.demodesafio.curso.entidade;

import com.crusaders.demodesafio.curso.entidade.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "curso")
@Entity(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "qtdHoras", nullable = false, length = 200)
    private String qtdHoras;
    @Column(name = "professor", nullable = false)
    private String professor;
    @Column(name = "areaConhecimento", nullable = false, length = 200)
    private String areaConhecimento;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 25)
    private Status status = Status.ATIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nome='" + nome + '\'' + '}';
    }
}
