package org.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.library.entity.JournalOrder;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {
    private Long orderId;
    private Long accountId;
    private Long journalId;
    private Boolean isPaid;
    private Boolean isActive;
    private BigDecimal totalAmount;

    // Конструктор, принимающий Order
    public OrderResponseDTO(JournalOrder journalOrder) {
        this.orderId = journalOrder.getId();
        this.accountId = journalOrder.getAccount().getId();
        this.journalId = journalOrder.getJournal().getId();
        this.isPaid = journalOrder.getIsPaid();
        this.isActive = journalOrder.getIsActive();
        this.totalAmount = journalOrder.getTotalAmount();
    }
}
