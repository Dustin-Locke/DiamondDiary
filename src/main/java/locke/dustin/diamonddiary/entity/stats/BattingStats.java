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
@Table(name = "batting_stats" )
public class BattingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @PositiveOrZero(message = "Plate appearances must be a positive integer." )
    @Column(name = "pa" )
    private int plateAppearances;

    @PositiveOrZero(message = "At bats must be a positive integer." )
    @Column(name = "ab" )
    private int atBats;

    @PositiveOrZero(message = "Hits must be a positive integer." )
    @Column(name = "h" )
    private int hits;

    @PositiveOrZero(message = "Singles must be a positive integer." )
    @Column(name = "1b" )
    private int singleBaseHits;

    @PositiveOrZero(message = "Doubles must be a positive integer." )
    @Column(name = "2b" )
    private int doubleBaseHits;

    @PositiveOrZero(message = "Triples must be a positive integer." )
    @Column(name = "3b" )
    private int tripleBaseHits;

    @PositiveOrZero(message = "Home runs must be a positive integer." )
    @Column(name = "hr" )
    private int homeRuns;

    @PositiveOrZero(message = "RBIs must be a positive integer." )
    @Column(name = "rbi" )
    private int runnersBattedIn;

    @PositiveOrZero(message = "Walks must be a positive integer." )
    @Column(name = "bb" )
    private int baseOnBalls;

    @PositiveOrZero(message = "Strikeouts must be a positive integer." )
    @Column(name = "so" )
    private int strikeouts;

    @PositiveOrZero(message = "Strikeouts looking must be a positive integer." )
    @Column(name = "kl" )
    private int strikeoutsLooking;

    @PositiveOrZero(message = "Hits by pitch must be a positive integer." )
    @Column(name = "hbp" )
    private int hitByPitch;

    @PositiveOrZero(message = "Sacrifices must be a positive integer." )
    @Column(name = "sac" )
    private int sacrificeHitsAndBunts;

    @PositiveOrZero(message = "Reaches on error must be a positive integer." )
    @Column(name = "roe" )
    private int reachesOnError;

    @PositiveOrZero(message = "Fielders choice must be a positive integer." )
    @Column(name = "fc" )
    private int hitIntoFieldersChoice;

    @PositiveOrZero(message = "Stolen bases must be a positive integer." )
    @Column(name = "sb" )
    private int stolenBases;

    @PositiveOrZero(message = "Caught stealing must be a positive integer." )
    @Column(name = "cs" )
    private int caughtStealing;

    @PositiveOrZero(message = "Pick offs must be a positive integer." )
    @Column(name = "pik" )
    private int pickedOff;

}
