package com.example.seniorfullstackdyhtwb.Repositories;

import com.example.seniorfullstackdyhtwb.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
