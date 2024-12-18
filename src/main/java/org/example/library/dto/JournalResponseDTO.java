package org.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library.entity.Journal;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JournalResponseDTO {
    private Long id;
    private String title;
    private int year;
    private String issueNumber;
    private BigDecimal price;

    // Конструктор на основе сущности Journal
    public JournalResponseDTO(Journal journal) {
        this.id = journal.getId();
        this.title = journal.getTitle();
        this.year = journal.getYear();
        this.issueNumber = journal.getIssueNumber();
        this.price = journal.getPrice();
    }
}
