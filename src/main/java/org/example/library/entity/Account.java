package org.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JournalOrder> journalOrderList = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();


    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public void addRole(String role) {
        this.roles.add(role);
    }

    public void removeRole(String role) {
        this.roles.remove(role);
    }

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
    }
}
