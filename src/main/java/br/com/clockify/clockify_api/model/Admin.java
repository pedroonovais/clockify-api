package br.com.clockify.clockify_api.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo name obrigatório")
    @Size(min = 2, message = "deve ter pelo menos 2 caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "deve começar com maiúscula")
    private String name;

    @NotBlank(message = "campo email obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "campo cpf obrigatório")
    @CPF
    private String cpf;

    @NotNull(message = "campo phone obrigatório")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "número de telefone inválido")
    private String phone;

    @NotBlank(message = "campo password obrigatório")
    @Size(min = 8, message = "deve ter pelo menos 8 caracteres")
    private String password;

    @NotNull(message = "campo active obrigatório")
    private Boolean active;

}
