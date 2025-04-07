package br.com.clockify.clockify_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class UserAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "campo idCompany obrigatório")
    private Long idCompany;

    @NotBlank(message = "campo name obrigatório")
    @Size(min = 2, message = "deve ter pelo menos 2 caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "deve começar com maiúscula")
    private String name;

    @NotBlank(message = "campo email obrigatório")
    @Email(message = "email inválido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email inválido")
    private String email;

    @NotBlank(message = "campo cpf obrigatório")
    @Size(min = 11, max = 11, message = "deve ter 11 dígitos")
    @Pattern(regexp = "^[0-9]{11}$", message = "cpf inválido")
    private String cpf;

    @NotNull(message = "campo phone obrigatório")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "número de telefone inválido")
    private String phone;

    @NotBlank(message = "campo password obrigatório")
    @Size(min = 8, message = "deve ter pelo menos 8 caracteres")
    private String password;

    @NotNull(message = "campo active obrigatório")
    private Boolean active;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
