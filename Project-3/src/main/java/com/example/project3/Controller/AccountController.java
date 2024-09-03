package com.example.project3.Controller;

import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/acc")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get-all-for-admin")
    public ResponseEntity getAllForAdmin(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getAccountsForAdmin(user.getId()));
    }

    @PostMapping("/add-new-account")
    public ResponseEntity addNewAccount(@AuthenticationPrincipal User user,Account account) {
        accountService.addNewAccount(account,user.getId());
        return ResponseEntity.status(200).body("account created successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal User user) {
        accountService.deleteAccountById(user.getId());
        return ResponseEntity.status(200).body("account deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user,Account account) {
        accountService.updateAccount(account,user.getId());
        return ResponseEntity.status(200).body("account updated successfully");
    }

    @PutMapping("/active-account")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user) {
        accountService.activeAccount(user.getId());
        return ResponseEntity.status(200).body("account activated successfully");
    }

    @PutMapping("/transfer-to/{transToId}/{amount}")
    public ResponseEntity transferTo(@AuthenticationPrincipal User user,@PathVariable Integer transToId,@PathVariable double amount) {
        accountService.transfer(user.getId(),transToId,amount);
        return ResponseEntity.status(200).body("account transfered successfully");
    }

    @PutMapping("/block-account/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user,@PathVariable Integer accountId) {
        accountService.blockBankAccount(accountId);
        return ResponseEntity.status(200).body("account blocked successfully");
    }

    @PutMapping("/deposit/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user,@RequestBody double amount) {
        accountService.deposit(user.getId(),amount);
        return ResponseEntity.status(200).body("account deposited successfully");
    }
    @PutMapping("/Withdraw/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,@PathVariable double amount) {
        accountService.Withdraw(user.getId(),amount);
        return ResponseEntity.status(200).body("account withdrawn successfully");
    }

    @GetMapping("/get/cus/account")
    public ResponseEntity getCusAccount(@AuthenticationPrincipal User user) {
        accountService.findOneAccount(user.getId());
        return ResponseEntity.status(200).body("account found successfully");
    }
}
