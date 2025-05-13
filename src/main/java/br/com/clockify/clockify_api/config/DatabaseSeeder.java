package br.com.clockify.clockify_api.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.clockify.clockify_api.model.User;
import br.com.clockify.clockify_api.model.UserRole;
import br.com.clockify.clockify_api.model.Company;
import br.com.clockify.clockify_api.repository.UserRepository;
import br.com.clockify.clockify_api.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostConstruct
        public void init() {

                var pedro = User.builder()
                                .name("Pedro Novais")
                                .email("pedro.novais@b2c.srv.br")
                                .cpf("290.582.070-56")
                                .phone("11991615000")
                                .password(passwordEncoder.encode("pedro123"))
                                .role(UserRole.ADMIN)
                                .active(true)
                                .build();

                var rodrigo = User.builder()
                                .name("Rodrigo Rios")
                                .email("rodrigo.rios@gmail.com.br")
                                .cpf("053.831.500-80")
                                .phone("11991615001")
                                .password(passwordEncoder.encode("rodrigo123"))
                                .role(UserRole.USER)
                                .active(true)
                                .build();

                userRepository.saveAll(List.of(pedro, rodrigo));

                var companies = List.of(
                                Company.builder().name("B2C").cnpj("57.029.594/0001-80").address("Av. Brasil, 1000")
                                                .email("b2c@b2c.srv.br").active(true)
                                                .createdAt(LocalDate.of(2018, 3, 15)).build(),
                                Company.builder().name("FIAP").cnpj("26.426.560/0001-04").address("Av. Brasil, 2000")
                                                .email("fiap@fiap.srv.br").active(true)
                                                .createdAt(LocalDate.of(2015, 7, 22)).build(),
                                Company.builder().name("TechNova").cnpj("10.492.760/0001-17")
                                                .address("Rua das Inovações, 123")
                                                .email("contato@technova.com").active(true)
                                                .createdAt(LocalDate.of(2019, 11, 5)).build(),
                                Company.builder().name("InovaCorp").cnpj("73.614.391/0001-08")
                                                .address("Av. Paulista, 1500")
                                                .email("inova@corp.com").active(true)
                                                .createdAt(LocalDate.of(2017, 5, 30)).build(),
                                Company.builder().name("AlphaTech").cnpj("94.994.500/0001-30").address("Rua Alfa, 321")
                                                .email("suporte@alphatech.com").active(true)
                                                .createdAt(LocalDate.of(2020, 1, 10)).build(),
                                Company.builder().name("Beta Solutions").cnpj("19.763.087/0001-87")
                                                .address("Av. Soluções, 456")
                                                .email("contato@betasolutions.com").active(true)
                                                .createdAt(LocalDate.of(2016, 9, 18)).build(),
                                Company.builder().name("NextGen TI").cnpj("07.213.740/0001-55")
                                                .address("Rua do Futuro, 789")
                                                .email("nextgen@ti.com").active(true)
                                                .createdAt(LocalDate.of(2019, 4, 3)).build(),
                                Company.builder().name("SmartSys").cnpj("56.391.304/0001-81")
                                                .address("Rua Inteligente, 890")
                                                .email("suporte@smartsys.com").active(true)
                                                .createdAt(LocalDate.of(2018, 8, 27)).build(),
                                Company.builder().name("CloudWave").cnpj("36.711.872/0001-05")
                                                .address("Av. das Nuvens, 321")
                                                .email("contato@cloudwave.io").active(true)
                                                .createdAt(LocalDate.of(2021, 2, 14)).build(),
                                Company.builder().name("GreenData").cnpj("63.344.217/0001-39")
                                                .address("Av. Sustentável, 654")
                                                .email("info@greendata.com.br").active(true)
                                                .createdAt(LocalDate.of(2017, 12, 1)).build(),
                                Company.builder().name("DataBridge").cnpj("47.550.930/0001-82")
                                                .address("Rua da Conexão, 777")
                                                .email("contato@databridge.com").active(true)
                                                .createdAt(LocalDate.of(2019, 6, 25)).build(),
                                Company.builder().name("VisionX").cnpj("92.416.039/0001-02").address("Av. Visão, 101")
                                                .email("hello@visionx.ai").active(true)
                                                .createdAt(LocalDate.of(2020, 10, 8)).build(),
                                Company.builder().name("CyberLink").cnpj("61.140.246/0001-53")
                                                .address("Rua Digital, 303")
                                                .email("contato@cyberlink.tech").active(true)
                                                .createdAt(LocalDate.of(2018, 4, 19)).build(),
                                Company.builder().name("PixelWare").cnpj("66.155.308/0001-14")
                                                .address("Rua dos Pixels, 1122")
                                                .email("support@pixelware.com").active(true)
                                                .createdAt(LocalDate.of(2016, 11, 30)).build(),
                                Company.builder().name("InfoMatrix").cnpj("19.234.443/0001-75")
                                                .address("Av. da Informação, 909")
                                                .email("matriz@infomatrix.net").active(true)
                                                .createdAt(LocalDate.of(2015, 8, 12)).build(),
                                Company.builder().name("NeuroSoft").cnpj("65.316.066/0001-30")
                                                .address("Rua Neural, 100")
                                                .email("contato@neurosoft.ai").active(true)
                                                .createdAt(LocalDate.of(2021, 3, 22)).build(),
                                Company.builder().name("BrightCode").cnpj("48.768.562/0001-06")
                                                .address("Av. Codificação, 455")
                                                .email("dev@brightcode.dev").active(true)
                                                .createdAt(LocalDate.of(2019, 9, 7)).build(),
                                Company.builder().name("DeepLogic").cnpj("03.287.035/0001-41")
                                                .address("Rua Lógica, 888")
                                                .email("contact@deeplogic.com").active(true)
                                                .createdAt(LocalDate.of(2017, 7, 17)).build(),
                                Company.builder().name("QuantumX").cnpj("00.265.168/0001-29")
                                                .address("Av. Quântica, 2020")
                                                .email("quantum@xcorp.tech").active(true)
                                                .createdAt(LocalDate.of(2020, 5, 28)).build(),
                                Company.builder().name("VirtuaSys").cnpj("60.521.105/0001-18")
                                                .address("Rua Virtual, 313")
                                                .email("hello@virtuasys.com").active(true)
                                                .createdAt(LocalDate.of(2018, 10, 11)).build());

                companyRepository.saveAll(companies);
        }
}
