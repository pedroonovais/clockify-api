package br.com.clockify.clockify_api.controller;

import java.util.List;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
@Tag(name = "Admin")
public class AdminController {
    
    @Autowired
    private AdminRepository repository;

    @GetMapping
    @Operation(
        summary = "Listar todos os Admins",
        description = "Retorna todos os Admins cadastrados"
    )
    public List<Admin> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Criar um novo Admin",
        description = "Cria e retorna um novo Admin com os dados informados",
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Admin create(@RequestBody @Valid Admin userAdmin) {
        log.info("Cadastrando um novo admin: " + userAdmin.getName());
        return repository.save(userAdmin);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Buscar um Admin por ID",
        description = "Retorna os dados de um Admin específico, com base no ID informado"
    )
    public Admin get(@PathVariable Long id) {
        log.info("Buscando Admin: " + id);
        return getUserAdmin(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Desativar um Admin",
        description = "Realiza a desativação lógica de um Admin com base no ID informado"
    )
    public void destroy(@PathVariable Long id) {
        Admin userAdmin = getUserAdmin(id);
        userAdmin.setActive(false);
        repository.save(userAdmin);        
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar um Admin",
        description = "Atualiza os dados de um Admin com base no ID informado"
    )
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
