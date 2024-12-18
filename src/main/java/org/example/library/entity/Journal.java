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
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int year;
    private String issueNumber;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private JournalOrder journalOrder;

}
