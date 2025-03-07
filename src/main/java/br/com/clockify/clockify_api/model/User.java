package br.com.clockify.clockify_api.model;

public class User {
    private Long id;
    private String name;
    private String email;
    private Long cpf;
    private Long phone;
    private String password;
    private boolean active;

    public User(Long id, String name, String email, Long cpf, Long phone, String password, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
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

    public Long getCpf() {
        return cpf;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
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
