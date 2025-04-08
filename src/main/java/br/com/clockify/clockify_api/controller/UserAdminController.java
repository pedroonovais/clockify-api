package br.com.clockify.clockify_api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.clockify.clockify_api.repository.UserAdminRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users-admin")
public class UserAdminController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserAdminRepository repository;

    @GetMapping
    public List<UserAdmin> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserAdmin create(@RequestBody @Valid UserAdmin userAdmin) {
        log.info("Cadastrando um novo usuário: " + userAdmin.getName());
        return repository.save(userAdmin);
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
        repository.delete(getUserAdmin(id));
    }

    @PutMapping("{id}")
    public UserAdmin update(@PathVariable Long id, @RequestBody UserAdmin userAdmin) {
        log.info("Atualizando userAdmin " + id + " " + userAdmin);	

        getUserAdmin(id);
        userAdmin.setId(id);
        return repository.save(userAdmin);
    }
 
    public UserAdmin getUserAdmin(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserAdmin " + id + " não encontrado")
                );
    }

}
