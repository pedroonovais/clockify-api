package br.com.clockify.clockify_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.web.PageableDefault;
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

import br.com.clockify.clockify_api.model.Company;
import br.com.clockify.clockify_api.model.CompanyFilter;
import br.com.clockify.clockify_api.repository.CompanyRepository;
import br.com.clockify.clockify_api.specification.CompanySpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/company")
@Slf4j
@Tag(name = "Company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping
    @Cacheable("companies")
    @Operation(
        summary = "Listar todas as Empresas",
        description = "Retorna todas as Empresas cadastradas"
    )
    public Page<Company> index(CompanyFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        return repository.findAll(CompanySpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Criar uma nova Empresa",
        description = "Cria e retorna uma nova Empresa com os dados informados",
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Company create(@RequestBody @Valid Company company) {
        log.info("Cadastrando nova empresa: {}", company.getName());
        return repository.save(company);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Buscar uma Empresa por ID",
        description = "Retorna os dados de uma Empresa específica, com base no ID informado"
    )
    public Company get(@PathVariable Long id) {
        log.info("Buscando empresa: {}", id);
        return getCompanyById(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar uma Empresa",
        description = "Atualiza os dados de uma Empresa com base no ID informado"
    )
    public Company update(@PathVariable Long id, @RequestBody @Valid Company company) {
        log.info("Atualizando empresa {}: {}", id, company);
        getCompanyById(id);
        company.setId(id);
        return repository.save(company);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Desativar uma Empresa",
        description = "Realiza a desativação lógica de uma Empresa com base no ID informado"
    )
    public void destroy(@PathVariable Long id) {
        Company company = getCompanyById(id);
        company.setActive(false);
        repository.save(company);
    }

    private Company getCompanyById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa com ID " + id + " não encontrada")
                );
    }
}
