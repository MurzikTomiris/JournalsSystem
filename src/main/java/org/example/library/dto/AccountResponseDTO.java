package org.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library.entity.Account;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;

    // Конструктор, который принимает объект Account
    public AccountResponseDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.roles = account.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
