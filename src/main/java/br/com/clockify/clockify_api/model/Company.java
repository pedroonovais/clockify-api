package br.com.clockify.clockify_api.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da empresa é obrigatório")
    @Size(max = 100, message = "O nome da empresa deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ
    private String cnpj;

    @Size(max = 150, message = "O endereço deve ter no máximo 150 caracteres")
    private String address;

    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Email(message = "email inválido")
    private String email;

    @PastOrPresent(message = "não pode ser no futuro")
    private LocalDate createdAt;

    @NotNull(message = "campo active obrigatório")
    private Boolean active;
}
