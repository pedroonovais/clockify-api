package br.com.clockify.clockify_api.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo name obrigatório")
    @Size(min = 2, message = "deve ter pelo menos 2 caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "deve começar com maiúscula")
    private String name;

    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "campo cpf obrigatório")
    @CPF
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "campo phone obrigatório")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "número de telefone inválido")
    private String phone;

    @NotBlank(message = "campo password obrigatório")
    @Size(min = 8, message = "deve ter pelo menos 8 caracteres")
    private String password;

    private UserRole role;

    @NotNull(message = "campo active obrigatório")
    private Boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

}
