package com.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TarefaController {

    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("tarefas", repository.findAll());
        return "index";
    }

    @PostMapping("/adicionar")
    public String adicionar(@RequestParam String descricao) {
        repository.save(new Tarefa(descricao));
        return "redirect:/";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/concluir/{id}")
    public String concluir(@PathVariable Long id) {
        Tarefa tarefa = repository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setConcluida(!tarefa.isConcluida());
            repository.save(tarefa);
        }
        return "redirect:/";
    }
}