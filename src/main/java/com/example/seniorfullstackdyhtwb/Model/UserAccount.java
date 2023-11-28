package com.example.seniorfullstackdyhtwb.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalCredits;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private List<GameSession> gameSessions = new ArrayList<>();

}
