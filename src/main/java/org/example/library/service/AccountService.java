package org.example.library.service;

import lombok.AllArgsConstructor;
import org.example.library.dto.AccountRegistrationDTO;
import org.example.library.dto.AccountResponseDTO;
import org.example.library.entity.Account;
import org.example.library.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountService {

    //регается клиент

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResponseDTO registerClient(AccountRegistrationDTO accountRegistrationDTO) {
        Account account = new Account();

        account.setName(accountRegistrationDTO.getName());
        account.setEmail(accountRegistrationDTO.getEmail());
        account.addRole("ROLE_USER");

        account.setPassword(passwordEncoder.encode(accountRegistrationDTO.getPassword()));

        accountRepository.save(account);
        return new AccountResponseDTO(account);
    }

    public AccountResponseDTO registerAdmin(AccountRegistrationDTO accountRegistrationDTO) {
        Account account = new Account();

        account.setName(accountRegistrationDTO.getName());
        account.setEmail(accountRegistrationDTO.getEmail());
        account.addRole("ROLE_ADMIN");

        account.setPassword(passwordEncoder.encode(accountRegistrationDTO.getPassword()));

        accountRepository.save(account);
        return new AccountResponseDTO(account);
    }


    //логин общий для обоих
    public AccountResponseDTO login(String email, String password) {
        // Находим пользователя по email
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        // Проверяем пароль
        if (!passwordEncoder.matches(password, account.get().getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Возвращаем данные о пользователе в виде DTO
        return new AccountResponseDTO(account.orElse(null));
    }

}
