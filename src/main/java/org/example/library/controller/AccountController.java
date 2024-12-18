package org.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.example.library.dto.AccountRegistrationDTO;
import org.example.library.dto.AccountResponseDTO;
import org.example.library.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Регистрация клиента
    @PostMapping("/register-client")
    public AccountResponseDTO registerClient(@RequestBody AccountRegistrationDTO dto) {
        return accountService.registerClient(dto);
    }

    // Регистрация администратора
    @PostMapping("/register-admin")
    public AccountResponseDTO registerAdmin(@RequestBody AccountRegistrationDTO dto) {
        return accountService.registerAdmin(dto);
    }

    // Вход
    @PostMapping("/login")
    public AccountResponseDTO login(@RequestParam String email, @RequestParam String password) {
        return accountService.login(email, password);
    }
}
