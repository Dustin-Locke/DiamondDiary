package locke.dustin.diamonddiary.entity;

import jakarta.persistence.*;
import locke.dustin.diamonddiary.util.GeoLocation;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false )
    private Team team;

    @ManyToOne
    private Team opponent;

    @ManyToOne
    private GeoLocation location;

    private LocalDate date;

}
