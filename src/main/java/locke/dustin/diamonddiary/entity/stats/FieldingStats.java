package locke.dustin.diamonddiary.entity.stats;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fielding_stats" )
public class FieldingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @PositiveOrZero(message = "Total chances must be a positive integer." )
    @Column(name = "tc" )
    private int totalChances;

    @PositiveOrZero(message = "Assists must be a positive integer." )
    @Column(name = "a" )
    private int assists;

    @PositiveOrZero(message = "Put outs must be a positive integer." )
    @Column(name = "po" )
    private int putOuts;

    @PositiveOrZero(message = "Errors must be a positive integer." )
    @Column(name = "e" )
    private int errors;

    @PositiveOrZero(message = "Double plays must be a positive integer." )
    @Column(name = "dp" )
    private int doublePlays;

    @PositiveOrZero(message = "Triple plays must be a positive integer." )
    @Column(name = "tp" )
    private int triplePlays;
}
