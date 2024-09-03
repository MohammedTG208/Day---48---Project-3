package com.example.project3.Service;

import com.example.project3.API.APIException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository CustomerRepository;

    public List<Account> getAccountsForAdmin(Integer userId) {
        User user = authRepository.findUserById(userId);
        if (user == null) {
            throw new APIException("User not found");
        }else {
            if (user.getRole().equals("ADMIN")) {
                return accountRepository.findAll();
            }else {
                throw new APIException("User is not admin");
            }
        }
    }

    public void addNewAccount(Account account ,Integer customerId) {
        Customer customer = CustomerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new APIException("customer not found");
        }else {
            account.setCustomer(customer);
            accountRepository.save(account);
        }
    }

    public void activeAccount(Integer customerId) {
        Account account = accountRepository.findAccountById(customerId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.isActive()){
                throw new APIException("account is already active");
            }else {
                account.setActive(true);
            }
        }
    }

    public void transfer(Integer customerId, Integer transToId, double amount) {
        Account account = accountRepository.findAccountById(customerId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.getCustomer().getId()==customerId && account.isActive()){
               Account transToAccount = accountRepository.findAccountById(transToId);
               if (transToAccount.isActive()) {
                   if (account.getBalance()<amount){
                       throw new APIException("transfer failed");
                   }else {
                       account.setBalance(account.getBalance() - amount);
                       transToAccount.setBalance(transToAccount.getBalance() + amount);
                       accountRepository.save(account);
                       accountRepository.save(transToAccount);
                   }
               }else {
                   throw new APIException("the account you want transfer to is not active");
               }
            }else {
               throw new APIException("account not active");
            }
        }
    }

    public void blockBankAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.isActive()){
                account.setActive(false);
                accountRepository.save(account);
            }else {
                throw new APIException("account is not active");
            }
        }
    }

    public void deposit(Integer accountId, double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.getCustomer().getId()==accountId && account.isActive()){
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
            }else {
                throw new APIException("account not active");
            }
        }
    }

    public void Withdraw(Integer accountId, double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.getCustomer().getId()==accountId && account.isActive()){
                if (account.getBalance()<amount){
                    throw new APIException("withdraw failed");
                }else {

                    account.setBalance(account.getBalance() - amount);
                    accountRepository.save(account);
                }
            }else {
                throw new APIException("account not active");
            }
        }
    }

    public Account findOneAccount(Integer accountId) {
        Account account= accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            if (account.isActive()) {
                return account;
            } else {
                throw new APIException("account is not active to display");
            }
        }
    }

    public void deleteAccountById(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new APIException("account not found");
        }else {
            accountRepository.delete(account);
        }
    }

    public void updateAccount(Account account,Integer customerId) {
        Customer customer = CustomerRepository.findCustomerById(customerId);
        Account oldAccount = accountRepository.findAccountById(account.getId());
        if (customer == null) {
            throw new APIException("customer not found");
        }else if (oldAccount.isActive()){
            oldAccount.setBalance(account.getBalance());
            oldAccount.setAccountNumber(account.getAccountNumber());
            oldAccount.setCustomer(customer);
            accountRepository.save(oldAccount);
        }else {
            throw new APIException("Account not active");
        }
    }
}
