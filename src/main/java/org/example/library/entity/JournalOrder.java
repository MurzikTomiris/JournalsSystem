package org.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JournalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isPaid;
    private Boolean isActive;
    private Boolean isBlacklisted;
    private BigDecimal totalAmount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "journal_id", referencedColumnName = "id")
    private Journal journal;

}
