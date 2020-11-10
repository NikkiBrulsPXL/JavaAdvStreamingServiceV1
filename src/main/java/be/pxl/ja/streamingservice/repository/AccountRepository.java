package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import be.pxl.ja.streamingservice.model.Account;

import java.util.HashMap;

public class AccountRepository {
    private HashMap<String, Account> accounts;

    public AccountRepository(){
        accounts = new HashMap<>();
    }

    public void addAccount(Account account){
        if(!accounts.containsKey(account.getEmail())) {
            accounts.put(account.getEmail(), account);
        }
        else {
            throw new DuplicateEmailException("Email already exists");
        }
    }

    public Account findAccount(String email){
        if(accounts.containsKey(email)){
            return accounts.get(email);
        }
        return null;
    }
}
