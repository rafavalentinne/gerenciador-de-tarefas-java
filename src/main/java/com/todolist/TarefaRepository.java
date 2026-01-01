package com.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Só isso! O Spring cria os códigos de banco de dados automaticamente aqui.
}