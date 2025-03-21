package br.com.clockify.clockify_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.clockify.clockify_api.model.UserAdmin;

@RestController
@RequestMapping("/users-admin")
public class UserAdminController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<UserAdmin> repository = new ArrayList<>();

    @GetMapping
    public List<UserAdmin> index() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<UserAdmin> create(@RequestBody UserAdmin user) {
        log.info("Cadastrando um novo usuário: " + user.getName());
        repository.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("{id}")
    public UserAdmin get(@PathVariable Long id) {
        log.info("Buscando User Admin: " + id);
        return getUserAdmin(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando userAdmin " + id);
        repository.remove(getUserAdmin(id));
    }

    @PutMapping("{id}")
    public UserAdmin update(@PathVariable Long id, @RequestBody UserAdmin userAdmin) {
        log.info("Atualizando userAdmin " + id + " " + userAdmin);	

        repository.remove(getUserAdmin(id));
        userAdmin.setId(id);
        repository.add(userAdmin);

        return userAdmin;
    }
 
    // //Verifica se o usuário já existe
    // public boolean exists(Long cpf) {
    //     return repository.stream()
    //             .anyMatch(c -> c.getCpf().equals(cpf));
    // }

    public UserAdmin getUserAdmin(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserAdmin " + id + " não encontrado")
                );
    }




}
