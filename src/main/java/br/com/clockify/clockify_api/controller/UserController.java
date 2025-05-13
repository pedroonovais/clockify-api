package br.com.clockify.clockify_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import br.com.clockify.clockify_api.model.User;
import br.com.clockify.clockify_api.model.UserRole;
import br.com.clockify.clockify_api.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "User")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @Operation(
        summary = "Listar todos os Users",
        description = "Retorna todos os Users cadastrados"
    )
    public List<User> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Criar um novo User",
        description = "Cria e retorna um novo User com os dados informados",
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public User create(@RequestBody @Valid User user) {
        log.info("Cadastrando um novo user: " + user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return repository.save(user);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Buscar um User por ID",
        description = "Retorna os dados de um User específico, com base no ID informado"
    )
    public User get(@PathVariable Long id) {
        log.info("Buscando User: " + id);
        return getUser(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Desativar um User",
        description = "Realiza a desativação lógica de um User com base no ID informado"
    )
    public void destroy(@PathVariable Long id) {
        User user = getUser(id);
        user.setActive(false);
        repository.save(user);        
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar um User",
        description = "Atualiza os dados de um User com base no ID informado"
    )
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("Atualizando User " + id + " " + user);	

        getUser(id);
        user.setId(id);
        return repository.save(user);
    }
 
    public User getUser(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " não encontrado")
                );
    }
}
