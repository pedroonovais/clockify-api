package br.com.clockify.clockify_api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.clockify.clockify_api.model.UserAdmin;

@RestController
public class UserAdminController {

    private List<UserAdmin> repository = new ArrayList<>();

    //Listar todos os usuários 
    @GetMapping("/users-admin")
    public List<UserAdmin> index() {
        return repository;
    }

    //Cadastrar um usuário
    @PostMapping("/users-admin")
    public ResponseEntity<UserAdmin> create(@RequestBody UserAdmin user) {
        if (exists(user.getCpf()))
            return ResponseEntity.badRequest().build();
    
        repository.add(user);
        System.out.println("Cadastrando um novo usuário: " + user.getName());
        return ResponseEntity.status(201).body(user);
    }

    //Buscar um usuário pelo ID
    
    @GetMapping("/users-admin/{id}")
    public ResponseEntity<UserAdmin> get(@PathVariable Long id) {
        System.out.println("Buscando User Admin" + id);
        var userAdmin = repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (userAdmin.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userAdmin.get());
    }
 
    //Verificar se o usuário já existe
    public boolean exists(Long cpf) {
        return repository.stream()
                .anyMatch(c -> c.getCpf().equals(cpf));
    }




}
