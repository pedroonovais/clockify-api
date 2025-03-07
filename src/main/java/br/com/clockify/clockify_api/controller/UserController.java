package br.com.clockify.clockify_api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.clockify.clockify_api.model.User;

@RestController
public class UserController {

    private List<User> repository = new ArrayList<>();

    //Listar todos os usuários 
    @GetMapping("/users")
    public List<User> index() {
        return repository;
    }

    //Cadastrar um usuário
    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        repository.add(user);
        System.out.println("Cadastrando um novo usuário: " + user.getName());
        return ResponseEntity.status(201).body(user);
    }

    // A fazer - Buscar um usuário pelo ID
    // Usar como referência o metodo abaixo apresentado em aula e adaptar para o nosso contexto de User
    // @GetMapping("/categories/{id}")
    // public ResponseEntity<Category> get(@PathVariable Long id) {
    //     System.out.println("Buscando Categoria" + id);
    //     var category = repository.stream()
    //             .filter(c -> c.getId().equals(id))
    //             .findFirst();

    //     if (category.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     return ResponseEntity.ok(category.get());
    // }
    
}
