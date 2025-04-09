package br.com.clockify.clockify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clockify.clockify_api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
