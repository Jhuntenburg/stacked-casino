package com.example.seniorfullstackdyhtwb.Services;


import com.example.seniorfullstackdyhtwb.Model.UserAccount;
import com.example.seniorfullstackdyhtwb.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Optional<UserAccount> getUserAccountById(Long userId) {
        return userAccountRepository.findById(userId);

    }
    public void addCreditsToUser(Long userId, int credits) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(userId);

        optionalUserAccount.ifPresent(userAccount -> {

            userAccount.setTotalCredits(userAccount.getTotalCredits() + credits);


            userAccountRepository.save(userAccount);
        });
    }


}
