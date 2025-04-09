package br.com.clockify.clockify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clockify.clockify_api.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
