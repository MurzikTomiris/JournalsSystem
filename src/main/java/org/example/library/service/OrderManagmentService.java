package org.example.library.service;

import lombok.AllArgsConstructor;
import org.example.library.dto.OrderRequestDTO;
import org.example.library.dto.OrderResponseDTO;
import org.example.library.entity.Account;
import org.example.library.entity.Journal;
import org.example.library.entity.JournalOrder;
import org.example.library.repository.AccountRepository;
import org.example.library.repository.JournalRepository;
import org.example.library.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderManagmentService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final JournalRepository journalRepository;

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponseDTO::new) // Используем конструктор DTO
                .collect(Collectors.toList());
    }

    //создание заказа

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Account account = accountRepository.findById(orderRequestDTO.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        Journal journal = journalRepository.findById(orderRequestDTO.getJournalId())
                .orElseThrow(() -> new IllegalArgumentException("Journal not found"));

        JournalOrder journalOrder = new JournalOrder();
        journalOrder.setAccount(account);
        journalOrder.setJournal(journal);
        journalOrder.setTotalAmount(journal.getPrice());
        journalOrder.setIsPaid(false);
        journalOrder.setIsActive(true);

        JournalOrder savedJournalOrder = orderRepository.save(journalOrder);
        return new OrderResponseDTO(savedJournalOrder);
    }


    //клиент оплачивает заказ

    @Transactional
    public OrderResponseDTO payOrder(Long orderId) {
        JournalOrder journalOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (!journalOrder.getIsActive()) {
            throw new IllegalStateException("Order is not active");
        }

        if (journalOrder.getIsPaid()) {
            throw new IllegalStateException("Order is already paid");
        }

        journalOrder.setIsPaid(true);
        return new OrderResponseDTO(orderRepository.save(journalOrder));
    }

    //админ делает заказ не активным

    @Transactional
    public OrderResponseDTO deactivateOrder(Long orderId) {
        JournalOrder journalOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (journalOrder.getIsPaid()) {
            throw new IllegalStateException("Cannot deactivate a paid order");
        }

        journalOrder.setIsActive(false);
        return new OrderResponseDTO(orderRepository.save(journalOrder));
    }

}
