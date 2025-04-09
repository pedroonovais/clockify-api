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

import br.com.clockify.clockify_api.model.Admin;
import br.com.clockify.clockify_api.repository.AdminRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private AdminRepository repository;

    @GetMapping
    public List<Admin> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Admin create(@RequestBody @Valid Admin userAdmin) {
        log.info("Cadastrando um novo admin: " + userAdmin.getName());
        return repository.save(userAdmin);
    }

    @GetMapping("{id}")
    public Admin get(@PathVariable Long id) {
        log.info("Buscando Admin: " + id);
        return getUserAdmin(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando Admin " + id);
        repository.delete(getUserAdmin(id));
    }

    @PutMapping("{id}")
    public Admin update(@PathVariable Long id, @RequestBody Admin userAdmin) {
        log.info("Atualizando Admin " + id + " " + userAdmin);	

        getUserAdmin(id);
        userAdmin.setId(id);
        return repository.save(userAdmin);
    }
 
    public Admin getUserAdmin(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserAdmin " + id + " não encontrado")
                );
    }

}
