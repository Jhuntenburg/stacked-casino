package com.example.seniorfullstackdyhtwb.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GameSession gameSession;

    private String result; // The result of the roll (cherry, lemon, etc.)

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp; // Timestamp of when the roll occurred
}


