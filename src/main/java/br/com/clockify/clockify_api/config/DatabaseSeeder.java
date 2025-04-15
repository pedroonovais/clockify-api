package br.com.clockify.clockify_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.clockify.clockify_api.model.Admin;
import br.com.clockify.clockify_api.model.Company;
import br.com.clockify.clockify_api.repository.AdminRepository;
import br.com.clockify.clockify_api.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    @PostConstruct
    public void init(){

        var admins = List.of(
            Admin.builder()
                .name("Pedro Novais")
                .email("pedro.novais@b2c.srv.br")
                .cpf("290.582.070-56")
                .phone("11991615000")
                .password("pedro123")
                .active(true)
                .build(),
            Admin.builder()
                .name("Rodrigo Rios")
                .email("rodrigo.rios@gmail.com.br")
                .cpf("053.831.500-80")
                .phone("11991615001")
                .password("rodrigo123")
                .active(true)
                .build()
        );

        adminRepository.saveAll(admins);

        var companies = List.of(
            Company.builder()
                .name("B2C")
                .cnpj("12.345.678/0001-95")
                .address("Av. Brasil, 1000")
                .email("b2c@b2c.srv.br")
                .active(true)
                .build(),
            Company.builder()
                .name("FIAP")
                .cnpj("50.219.813/0001-45")
                .address("Av. Brasil, 2000")
                .email("fiap@fiap.srv.br")
                .active(true)
                .build(),
            Company.builder()
                .name("TechNova")
                .cnpj("38.780.278/0001-00")
                .address("Rua das Inovações, 123")
                .email("contato@technova.com")
                .active(true)
                .build(),
            Company.builder()
                .name("InovaCorp")
                .cnpj("94.261.957/0001-35")
                .address("Av. Paulista, 1500")
                .email("inova@corp.com")
                .active(true)
                .build(),
            Company.builder()
                .name("AlphaTech")
                .cnpj("90.216.066/0001-06")
                .address("Rua Alfa, 321")
                .email("suporte@alphatech.com")
                .active(true)
                .build(),
            Company.builder()
                .name("Beta Solutions")
                .cnpj("25.999.066/0001-68")
                .address("Av. Soluções, 456")
                .email("contato@betasolutions.com")
                .active(true)
                .build(),
            Company.builder()
                .name("NextGen TI")
                .cnpj("56.515.415/0001-52")
                .address("Rua do Futuro, 789")
                .email("nextgen@ti.com")
                .active(true)
                .build()
            );

        companyRepository.saveAll(companies);
    }
}
