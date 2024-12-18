package org.example.library.controller;

import lombok.AllArgsConstructor;
import org.example.library.dto.JournalRequestDTO;
import org.example.library.dto.JournalResponseDTO;
import org.example.library.service.JournalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/journals")
public class JournalController {
    private final JournalService journalService;

    // Создание нового журнала
    @PostMapping("/create")
    public ResponseEntity<JournalResponseDTO> createJournal(@RequestBody JournalRequestDTO journalRequestDTO) {
        return ResponseEntity.ok(journalService.createJournal(journalRequestDTO));
    }

    // Получение списка всех журналов
    @GetMapping("/all")
    public ResponseEntity<List<JournalResponseDTO>> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }
}
