package com.example.seniorfullstackdyhtwb.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int credits;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
     UserAccount userAccount;




}
