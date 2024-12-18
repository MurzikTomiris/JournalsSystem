package org.example.library.service;

import lombok.AllArgsConstructor;
import org.example.library.dto.JournalRequestDTO;
import org.example.library.dto.JournalResponseDTO;
import org.example.library.entity.Journal;
import org.example.library.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JournalService {
    private final JournalRepository journalRepository;

    // Метод создания нового журнала
    public JournalResponseDTO createJournal(JournalRequestDTO journalRequestDTO) {
        Journal journal = new Journal();
        journal.setTitle(journalRequestDTO.getTitle());
        journal.setYear(journalRequestDTO.getYear());
        journal.setIssueNumber(journalRequestDTO.getIssueNumber());
        journal.setPrice(journalRequestDTO.getPrice());

        Journal savedJournal = journalRepository.save(journal);
        return new JournalResponseDTO(savedJournal);
    }

    // Метод для просмотра всех журналов
    public List<JournalResponseDTO> getAllJournals() {
        return journalRepository.findAll().stream()
                .map(JournalResponseDTO::new)
                .collect(Collectors.toList());
    }
}
