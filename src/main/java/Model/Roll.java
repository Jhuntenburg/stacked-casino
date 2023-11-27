package Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GameSession gameSession;

    private String result; // The result of the roll (cherry, lemon, etc.)

    private Date timestamp; // Timestamp of when the roll occurred

}
